package com.iquma.utils;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * Created by Mo on 2017/4/2.
 */
public class CASTS {


    //根据主贴控制器的方法名转换为汉字
    public static String castTopic(String condition){
        if(condition.equals("update")) return "修改";
        else if (condition.equals("block")) return "关闭";
        else if (condition.equals("delete")) return "删除";
        else if (condition.equals("favorite")) return "收藏";
        else return "操作";
    }

    //根据回复控制器的方法名转换为汉字
    public static String castReply(String condition){
        if(condition.equals("delete")) return "删除";
        else if (condition.equals("block")) return "折叠";
        else if (condition.equals("adopt")) return "采纳";
        else return "操作";
    }

    //将用户发表时的方法转换为主贴名
    public static String translateAddTopic(String condition){
        switch (condition){
            case "ask" : return ENUMS.SEC_QUESTION;
            case "write" : return ENUMS.SEC_ARTICLE;
            case "teach" : return ENUMS.SEC_TUTORIAL;
            case "upload" : return ENUMS.SEC_CODE;
            default : return ENUMS.SEC_QUESTION;
        }
    }


}
