package com.iquma.utils;

import com.iquma.pojo.*;
import com.iquma.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    private UserService userService;

    //获取目前登录用户的id
    private String initUserId(){
        return String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid"));
    }

    //获取目前登录用户的昵称
    private String initUserName(){
        return String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("username"));
    }

    //用户登录后记录日志
    @AfterReturning(value = "execution(* com.iquma.controller.UserController.loginValidator(..))")
    public void afterLogin(JoinPoint joinPoint){
        User u = (User)joinPoint.getArgs()[0];
        HttpServletRequest request = (HttpServletRequest)joinPoint.getArgs()[1];
        suclogService.insert(new Suclog(u.getId(),new java.util.Date(),getIp(request)));
    }

    //获取真实ip--待测试
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

    //将用户发表时的方法转换为主贴名
    private String translateAddTopic(String condition){
        if ("ask".equals(condition)) return "discuss";
        else if ("write".equals(condition)) return "article";
        else return "code";
    }

    //用户提问、发表经验、上传代码后记录日志
    @AfterReturning(value = "execution(* com.iquma.controller.BBSController.ask(..)) || execution(* com.iquma.controller.BBSController.write(..)) || execution(* com.iquma.controller.BBSController.upload(..)) ", returning = "result")
    public  void afterAddTopic(JoinPoint joinPoint, Object result){
        if(Boolean.valueOf(String.valueOf(result))){//当方法返回结果是TRUE时
            //获取用户操作信息
            Topic record = (Topic)joinPoint.getArgs()[0];
            String topicType =  translateAddTopic(joinPoint.getSignature().getName());
            String uid = initUserId();
            String opid = String.valueOf(record.getId());
            Byte pid = permissionService.selectByPermission(topicType + ":create").getId();
            Date time = record.getaddTime();
            //将用户操作信息存入记录
            operationService.insert(new Operation(uid,opid,pid,time));
            //更新用户威望
            if("discuss".equals(topicType))
                userService.updatePrestige(initUserId(), ENUMS.PRESTIGE_INSERT_DISUCSS);
            else if("article".equals(topicType))
                userService.updatePrestige(initUserId(),ENUMS.PRESTIGE_INSERT_ARTICLE);
            else if("code".equals(topicType))
                userService.updatePrestige(initUserId(),ENUMS.PRESTIGE_INSERT_CODE);
            //更新用户角色等级
            updateUserRid();
        }
    }



    //有权限的用户编辑/关闭/删除/收藏/赞同/反对主贴后通知该主贴作者并记录日志
    @AfterReturning(value = "execution(* com.iquma.controller.TopicController.update(..)) || execution(* com.iquma.controller.TopicController.block(..)) || execution(* com.iquma.controller.TopicController.delete(..)) || execution(* com.iquma.controller.TopicController.favorite(..)) || execution(* com.iquma.controller.TopicController.like(..)) || execution(* com.iquma.controller.TopicController.hate(..))", returning = "result")
    public  void afterTopic(JoinPoint joinPoint, Object result){
        if(Boolean.valueOf(String.valueOf(result))){//当方法返回结果是TRUE时
            //获取用户操作信息
            Topic record = (Topic)joinPoint.getArgs()[0];
            String topicType = record.getSection();
            String uid = initUserId();
            String opid = String.valueOf(record.getId());
            String methodName = joinPoint.getSignature().getName();
            Byte pid = permissionService.selectByPermission(topicType + ":" + methodName).getId();
            Date time = new Date();
            //将用户操作信息存入记录
            operationService.insert(new Operation(uid,opid,pid,time));
            //通知相关用户，主贴被收藏/赞同/反对除外，用户操作自己的主贴除外
            String ntfuid = record.getAid();
            if(!uid.equals(ntfuid) && ! "favorite".equals(methodName) && ! "like".equals(methodName) && ! "hate".equals(methodName)){
                String ntfcontent = "你的帖子<a>" + record.getTitle() + "</a>被" + initUserName() + CASTS.castTopic(methodName);
                notificationService.insert(new Notification(ntfuid,ntfcontent,time,Boolean.TRUE));
            }
            //更新用户威望
            switch (methodName){
                case "delete" :
                    userService.updatePrestige(ntfuid,ENUMS.PRESTIGE_BE_DELETED);break;
                case "block" :
                    userService.updatePrestige(ntfuid,ENUMS.PRESTIGE_BE_BLOCKED);break;
                case "like" :
                    userService.updatePrestige(ntfuid,ENUMS.PRESTIGE_BE_LIKED);break;
                case "hate" :
                    userService.updatePrestige(ntfuid,ENUMS.PRESTIGE_BE_HATED);break;
            }
            //更新用户角色等级
            updateUserRid();
        }
    }

    //用户发表评论后记录日志并通知相关用户并更新主贴信息
    @AfterReturning(value = "execution(* com.iquma.controller.ReplyController.insert(..))")
    public void afterInsertReply(JoinPoint joinPoint){
        Reply reply = (Reply)joinPoint.getArgs()[0];//获取用户操作信息
        String uid = initUserId();
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
        //更新用户威望
        userService.updatePrestige(initUserId(),ENUMS.PRESTIGE_INSERT_REPLY);
        //更新用户角色等级
        updateUserRid();
    }




    //有权限的用户删除/关闭/采纳/赞同/反对评论后记录日志
    @AfterReturning(value = "execution(* com.iquma.controller.ReplyController.delete(..)) || execution(* com.iquma.controller.ReplyController.block(..)) || execution(* com.iquma.controller.ReplyController.adopt(..)) || execution(* com.iquma.controller.ReplyController.like(..)) || execution(* com.iquma.controller.ReplyController.hate(..)) ", returning = "result")
    public void afterReply(JoinPoint joinPoint, Object result){
        if(Boolean.valueOf(String.valueOf(result))){//当方法返回结果是TRUE时
            //获取用户操作信息
            Reply record = (Reply)joinPoint.getArgs()[0];
            String uid = initUserId();
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
                String ntfcontent = "你对<a>" + record.getTitle() + "</a>的回复被" + initUserName() + CASTS.castReply(methodName);
                notificationService.insert(new Notification(ntfuid,ntfcontent,time,Boolean.TRUE));
            }
            //更新用户威望
            switch (methodName){
                case "delete" :
                    userService.updatePrestige(ntfuid,ENUMS.PRESTIGE_BE_DELETED);break;
                case "block" :
                    userService.updatePrestige(ntfuid,ENUMS.PRESTIGE_BE_BLOCKED);break;
                case "adopt" :
                    userService.updatePrestige(ntfuid,ENUMS.PRESTIGE_BE_ADOPTED);break;
                case "like" :
                    userService.updatePrestige(ntfuid,ENUMS.PRESTIGE_BE_LIKED);break;
                case "hate" :
                    userService.updatePrestige(ntfuid,ENUMS.PRESTIGE_BE_HATED);break;
            }
            //更新用户角色等级
            updateUserRid();
        }
    }


    //更新用户角色等级
    private void updateUserRid(){
        String uid = initUserId();
        User user = userService.selectById(uid);
        int prestige = user.getPrestige();
        if(user.getRid() >= 4){//当账户是学生账户时，根据威望更新用户角色等级
            if(ENUMS.PRESTIGE_RID_4 <= prestige)
                userService.updateRid(uid,Byte.parseByte("4"));
            else if(ENUMS.PRESTIGE_RID_5 <= prestige)
                userService.updateRid(uid,Byte.parseByte("5"));
            else if(ENUMS.PRESTIGE_RID_6 <= prestige)
                userService.updateRid(uid,Byte.parseByte("6"));
            else if(ENUMS.PRESTIGE_RID_7 <= prestige)
                userService.updateRid(uid,Byte.parseByte("7"));
            else
                userService.updateRid(uid,Byte.parseByte("8"));
        }
    }
}
