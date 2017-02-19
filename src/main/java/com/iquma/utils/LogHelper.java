package com.iquma.utils;

import com.iquma.pojo.*;
import com.iquma.service.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

    //用户登录后记录日志
    @AfterReturning(value = "execution(* com.iquma.controller.userController.loginValidator(..))")
    public void afterLogin(JoinPoint joinPoint){
        User u = (User)joinPoint.getArgs()[0];
        System.out.println("获取到的参数为" + u);
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


    //用户发表评论后记录日志
    @AfterReturning(value = "execution(* com.iquma.controller.replyController.insert(..))")
    public void afterInsertReply(JoinPoint joinPoint){
        Reply reply = (Reply)joinPoint.getArgs()[0];//获取用户操作信息
        String uid = reply.getUid();
        Date time = reply.getAddTime();
        Byte pid = permissionService.selectByCondition(new Permission("reply:create")).getId();
        String opid = String.valueOf(reply.getTid());
        //将用户操作信息存入记录
        operationService.insert(new Operation(uid,opid,pid,time));
        //通知相关用户
        String ntfuid = topicService.selectByCondition(new Topic(reply.getTid())).getAid();
        String ntfcontent = "你的帖子" + reply.getTid() + "有新回复";
        notificationService.insert(new Notification(ntfuid,ntfcontent,time,Boolean.TRUE));
    }


}
