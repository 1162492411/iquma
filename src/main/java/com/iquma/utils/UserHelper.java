package com.iquma.utils;

import com.iquma.pojo.Topic;
import com.iquma.pojo.User;
import com.iquma.service.TopicService;
import com.iquma.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户工具类
 * Created by Mo on 2017/6/3.
 */
@Component
public class UserHelper {
    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;

    //获取当前登录用户的id
    public static String getCurrentUserId(){
        Object result = SecurityUtils.getSubject().getSession().getAttribute("userid");
        return null == result ? null : String.valueOf(result);
    }

    //获取目前登录用户的昵称
    public static String getCurrentUserName(){
        return String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("username"));
    }

    //更新用户角色等级
    public void updateUserRid(){
        String uid = getCurrentUserId();
        User user = userService.selectSimpleUser(uid);
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

    //更新用户威望
    public void updateUserPrestige(String methodName,String uid){
        int score = 0;
        switch (methodName){
            case "delete" : score = ENUMS.PRESTIGE_BE_DELETED;break;
            case "block" : score = ENUMS.PRESTIGE_BE_BLOCKED;break;
            case "adopt" : score = ENUMS.PRESTIGE_BE_ADOPTED;break;
            case "like" : score = ENUMS.PRESTIGE_BE_LIKED;break;
            case "hate" : score = ENUMS.PRESTIGE_BE_HATED;break;
            case "question" : score = ENUMS.PRESTIGE_INSERT_DISUCSS;break;
            case "article" : score = ENUMS.PRESTIGE_INSERT_ARTICLE;break;
            case "code" : score = ENUMS.PRESTIGE_INSERT_CODE;break;
            case "reply" : score = ENUMS.PRESTIGE_INSERT_REPLY;break;
        }
        if(uid != null) userService.updatePrestige(uid,score);
    }

    //当前登录用户是否角色等级高于待操作用户
    public boolean hasEnoughRole(String uid){
        User currentUser = userService.selectSimpleUser(getCurrentUserId());//取出当前登录账户
        User user = userService.selectSimpleUser(uid); //查询待操作的账户
        //判断二者角色等级:仅当当前登录账户是教师级以上账户且当前账户角色高于待操作账户时允许对账户进行操作
        return currentUser.getRid() <= ENUMS.ROLE_TEACHER && currentUser.getRid() < user.getRid();
    }

    //判断用户是否拥有指定权限
    public boolean hasPermission(String permission,String uid){
        if(uid.equals(getCurrentUserId())){
            System.out.println("登录用户正在操作自己的资源");
            return true;
        }
        else{
            SecurityUtils.getSubject().checkPermission(permission);
            System.out.println("登录用户拥有足够权限");
            return true;
        }
    }

    //获取用户所有主贴并封装权限集合
    public List<String> getUserStringPermissions(String id){
        List<Topic> topics = topicService.selectsRevelant(id);
        List<String> permissions = new ArrayList<>(topics.size() * 8);
        for (Topic topic: topics) {
            String sec = topic.getSec();
            Integer tid = topic.getId();
            permissions.add(sec + ":delete:" + tid);
            permissions.add(sec + ":block:" + tid);
            permissions.add(sec + ":update:" + tid);
            permissions.add(sec + ":hate:" + tid);
            permissions.add("reply:delete:" + tid);
            permissions.add("reply:block:" + tid);
            permissions.add("reply:adopt:" + tid);
            permissions.add("reply:hate:" + tid);
        }
        return permissions;
    }


}
