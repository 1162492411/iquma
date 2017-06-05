package com.iquma.utils;

import com.iquma.pojo.User;
import com.iquma.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户工具类
 * Created by Mo on 2017/6/3.
 */
@Component
public class UserHelper {
    @Autowired
    UserService userService;

    //获取当前登录用户的id
    public static String getCurrentUserId(){
        return String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid"));
    }

    //获取目前登录用户的昵称
    public static String getCurrentUserName(){
        return String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("username"));
    }

    //更新用户角色等级
    public void updateUserRid(){
        String uid = UserHelper.getCurrentUserId();
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

    //更新用户威望
    public void updateUserPrestige(String methodName,String uid){
        int score = 0;
        switch (methodName){
            case "delete" : score = ENUMS.PRESTIGE_BE_DELETED;break;
            case "block" : score = ENUMS.PRESTIGE_BE_BLOCKED;break;
            case "adopt" : score = ENUMS.PRESTIGE_BE_ADOPTED;break;
            case "like" : score = ENUMS.PRESTIGE_BE_LIKED;break;
            case "hate" : score = ENUMS.PRESTIGE_BE_HATED;break;
            case "discuss" : score = ENUMS.PRESTIGE_INSERT_DISUCSS;break;
            case "article" : score = ENUMS.PRESTIGE_INSERT_ARTICLE;break;
            case "code" : score = ENUMS.PRESTIGE_INSERT_CODE;break;
            case "reply" : score = ENUMS.PRESTIGE_INSERT_REPLY;break;
        }
        if(uid != null) userService.updatePrestige(uid,score);
    }

}
