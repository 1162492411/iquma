package com.iquma.utils;

import com.iquma.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 评分计算类
 * Created by Mo on 2017/3/19.
 */
@Component
public class BinomialUtil {

    @Autowired
    private UserService userService;
    private static final double Z = 0.84;//默认置信水平，单侧置信区间80%

    //默认各级账户对应加权值
    public int getWeight(Byte r){
        switch(r){
            case 1 : case 2 :case 3 : return 8;
            case 4 : return 6;
            case 5 : return 4;
            case 6 : return 3;
            case 7 : return 2;
            case 8 : return 1;
            default : return 0;
        }
    }

    //计算加权赞同分
    public Double getLikeCount(Double u){
        return u + getWeight(userService.selectSimpleUser(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid"))).getRid());
    }

    //计算加权反对分
    public Double getHateCount(Double u){
        return getLikeCount(u);
    }


    //计算最终评分
    public Byte getRateCount(Double u,Double v){
        double n = u + v;//计算加权赞同数与加权反对数之和
        double p = u / n;//计算加权赞同数在总数中的比例
        //计算score(威尔逊置信区间下限)
        if (n == 0 || u == 0) return 0;//若赞同为0或者总和为0快速返回0
        double score = ( p + Math.pow(Z, 2) / ( 2 * n) + Z / (2 * n) * Math.sqrt(4 * n * p * (1 - p) + Math.pow(Z, 2) ) ) / ( 1 + Math.pow(Z,2) / n );
        return (byte)(score * 10) ;
    }




}
