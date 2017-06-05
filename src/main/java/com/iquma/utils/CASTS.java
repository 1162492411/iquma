package com.iquma.utils;


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

    //将查询到的主贴列表转化为字符串
    public static StringBuffer translateToSB(List list){
        StringBuffer stringBuffer = new StringBuffer();
        if(list.size() > 0){
            stringBuffer.append("[");
            for (int i = 0; i < list.size() - 1; i++)
                stringBuffer.append(list.get(i) + ",");
            stringBuffer.append(list.get(list.size() - 1) + "]");
        }
        else {System.out.println("----即将返回空结果"); return new StringBuffer("\" \"");}
        System.out.println("-------转换后的结果是" + stringBuffer);
        return stringBuffer;
    }

    //将用户发表时的方法转换为主贴名
    public static String translateAddTopic(String condition){
        if ("ask".equals(condition)) return "discuss";
        else if ("write".equals(condition)) return "article";
        else return "code";
    }

}
