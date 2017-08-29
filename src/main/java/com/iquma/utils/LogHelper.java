package com.iquma.utils;

import com.iquma.exception.NoSuchTopicException;
import com.iquma.pojo.*;
import com.iquma.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.iquma.utils.ENUMS.PRESTIGE_RID_4;

@Component
@Aspect
public class LogHelper {

    @Autowired
    private SuclogService suclogService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserHelper userHelper;
    
    //用户登录后记录日志
    @AfterReturning(value = "execution(* com.iquma.controller.UserController.loginValidator(..))")
    public void afterLogin(JoinPoint joinPoint){
        User u = (User)joinPoint.getArgs()[0];
        HttpServletRequest request = (HttpServletRequest)joinPoint.getArgs()[1];
        suclogService.insert(new Suclog(u.getId(),new Date(),getIp(request)));
    }

    //获取真实ip
    private static String getIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("Proxy-Client-IP");
        if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("WL-Proxy-Client-IP");
        if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_CLIENT_IP");
        if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getRemoteAddr();
        return "0:0:0:0:0:0:0:1".equals(ip)? "127.0.0.1" : ip ;
    }

    //用户提问、发表经验、上传代码后记录日志
    @AfterReturning(value = "execution(* com.iquma.controller.UserController.ask(..)) || execution(* com.iquma.controller.UserController.write(..)) || execution(* com.iquma.controller.UserController.upload(..)) || execution(* com.iquma.controller.UserController.teach(..))", returning = "result")
    public  void afterAddTopic(JoinPoint joinPoint, Object result){
        if(Boolean.valueOf(String.valueOf(result))){//当方法返回结果是TRUE时
            //获取用户操作信息
            Topic record = (Topic)joinPoint.getArgs()[0];
            String topicType =  CASTS.translateAddTopic(joinPoint.getSignature().getName());
            String uid = UserHelper.getCurrentUserId();
            String opid = String.valueOf(record.getId());
            Byte pid = permissionService.selectByPermission(topicType + ":create").getId();
            Date time = record.getAddTime();
            //将用户操作信息存入记录
            operationService.insert(new Operation(uid,opid,pid,time));
            userHelper.updateUserPrestige(topicType,UserHelper.getCurrentUserId()); //更新用户威望
            userHelper.updateUserRid();//更新用户角色等级
        }
    }

    //有权限的用户编辑/关闭/删除/收藏/赞同/反对主贴后通知该主贴作者并记录日志
    @AfterReturning(value = "execution(* com.iquma.controller.TopicController.update(..)) || execution(* com.iquma.controller.TopicController.block(..)) || execution(* com.iquma.controller.TopicController.delete(..)) || execution(* com.iquma.controller.TopicController.favorite(..)) || execution(* com.iquma.controller.TopicController.like(..)) || execution(* com.iquma.controller.TopicController.hate(..))", returning = "result")
    public  void afterTopic(JoinPoint joinPoint, Object result){
        if(Boolean.valueOf(String.valueOf(result))){//当方法返回结果是TRUE时
            //获取用户操作信息
            Topic record = (Topic)joinPoint.getArgs()[0];
            String topicType = record.getSec();
            String uid = UserHelper.getCurrentUserId();
            String opid = String.valueOf(record.getId());
            String methodName = joinPoint.getSignature().getName();
            System.out.println("记录日志时收到的有效信息是-------topicType:" + topicType + "---uid:" + uid + "-----opid:" + opid + "---methodName:" + methodName);
            Byte pid = permissionService.selectByPermission(topicType + ":" + methodName).getId();
            Date time = new Date();
            //将用户操作信息存入记录
            operationService.insert(new Operation(uid,opid,pid,time));
            //通知相关用户，主贴被收藏/赞同/反对除外，用户操作自己的主贴除外
            String ntfuid = record.getAid();
            if(!uid.equals(ntfuid) && ! "favorite".equals(methodName) && ! "like".equals(methodName) && ! "hate".equals(methodName)){
                notificationService.insert(new Notification(ntfuid,"你的帖子<a>" + record.getTitle() + "</a>被" + UserHelper.getCurrentUserName() + CASTS.castTopic(methodName),time,Boolean.TRUE));
            }
            userHelper.updateUserPrestige(methodName,ntfuid); //更新用户威望
            //更新用户角色等级
            userHelper.updateUserRid();
        }
    }

    //用户发表评论后记录日志并通知相关用户并更新主贴信息
    @AfterReturning(value = "execution(* com.iquma.controller.ReplyController.insert(..))")
    public void afterInsertReply(JoinPoint joinPoint) throws NoSuchTopicException {
        Reply reply = (Reply)joinPoint.getArgs()[0];//获取用户操作信息
        String uid = UserHelper.getCurrentUserId();
        String opid = String.valueOf(reply.getTid());
        Byte pid = permissionService.selectByPermission("reply:create").getId();
        Date time = reply.getAddTime();
        //将用户操作信息存入记录
        operationService.insert(new Operation(uid,opid,pid,time));
        //更新主贴信息
        topicService.increaseReply(reply.getTid());
        //通知相关用户
        String ntfuid = topicService.selectById(reply.getTid()).getAid();
        String ntfcontent = "你的帖子" + reply.getTid() + "有新回复";
        notificationService.insert(new Notification(ntfuid,ntfcontent,time,Boolean.TRUE));
        userHelper.updateUserPrestige("reply",UserHelper.getCurrentUserId()); //更新用户威望
        userHelper.updateUserRid();//更新用户角色等级
    }

    //有权限的用户删除/关闭/采纳/赞同/反对评论后记录日志
    @AfterReturning(value = "execution(* com.iquma.controller.ReplyController.delete(..)) || execution(* com.iquma.controller.ReplyController.block(..)) || execution(* com.iquma.controller.ReplyController.adopt(..)) || execution(* com.iquma.controller.ReplyController.like(..)) || execution(* com.iquma.controller.ReplyController.hate(..)) ", returning = "result")
    public void afterReply(JoinPoint joinPoint, Object result){
        if(Boolean.valueOf(String.valueOf(result))){//当方法返回结果是TRUE时
            //获取用户操作信息
            Reply record = (Reply)joinPoint.getArgs()[0];
            String uid = UserHelper.getCurrentUserId();
            String opid = String.valueOf(record.getId());
            String methodName = joinPoint.getSignature().getName();
            Byte pid = permissionService.selectByPermission("reply:" + methodName).getId();
            Date time = new Date();
            //将用户操作信息存入记录
            operationService.insert(new Operation(uid,opid,pid,time));
            //如果删除评论时统一主贴信息
            if("delete".equals(methodName)) topicService.reduceReply(record.getTid());
            //如果采纳评论时统一主贴信息
            if("adopt".equals(methodName)) topicService.adopt(record.getTid());
            //通知相关用户,用户评论自己的主贴除外，评论被赞同/反对除外
            String ntfuid = record.getUid();
            if(!uid.equals(ntfuid) && ! "like".equals(methodName) && ! "hate".equals(methodName)){
//                String ntfcontent = "你对<a>" + record.getTitle() + "</a>的回复被" + UserHelper.getCurrentUserName() + CASTS.castReply(methodName);
                String ntfcontent = "";//TODO:此处title需要处理
                notificationService.insert(new Notification(ntfuid,ntfcontent,time,Boolean.TRUE));
            }
            userHelper.updateUserPrestige(methodName,ntfuid); //更新用户威望
            //更新用户角色等级
            userHelper.updateUserRid();
        }
    }

}
