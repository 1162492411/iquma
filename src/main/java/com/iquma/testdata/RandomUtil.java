package com.iquma.testdata;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 随机数据类
 * Created by Mo on 2017/7/29.
 */
public class RandomUtil {

    /**
     * 获取指定长度随机简体中文
     * @param len 指定长度
     * @return 生成的随机简体中文
     */
    public static String generateRandomJianHan(Random random,int len)
    {
        String ret="";
        for(int i=0;i<len;i++){
            String str = null;
            int hightPos, lowPos; // 定义高低位
            hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try
            {
                str = new String(b, "GBK"); //转成中文
            }
            catch (UnsupportedEncodingException ex)
            {
                ex.printStackTrace();
            }
            ret+=str;
        }
        return ret;
    }

    /**
     * 生成随机时间列表
     * @param random Random
     * @param capacity 生成的总数据量
     * @return 生成的随机时间列表
     */
    public static List<Date> generateRandomDates(Random random, int capacity){
        Calendar calendar = Calendar.getInstance();
        List<Date> dates = new ArrayList<>(capacity);
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        int currentYear = now.get(Calendar.YEAR);
        int currentMonth = now.get(Calendar.MONTH);
        for (int i = 0; i < capacity; i++) {
            int year = 2010 + random.nextInt(8);
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,random.nextInt(year >= currentYear ? currentMonth : 12));
            calendar.set(Calendar.DAY_OF_MONTH,random.nextInt(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)));
            calendar.set(Calendar.HOUR,random.nextInt(24));
            calendar.set(Calendar.MINUTE,random.nextInt(60));
            calendar.set(Calendar.SECOND,random.nextInt(60));
            dates.add(calendar.getTime());
        }
        return dates;
    }

    /**
     * 生成随机IP列表
     * @param random Random
     * @param capacity 生成的总数据量
     * @return 生成的随机IP列表
     */
    public static List<String> generateRandomIPs(Random random,int capacity){
        List<String> ips = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++)
            ips.add(random.nextInt(255) + "." + random.nextInt(255) + "." +random.nextInt(255) + "." +random.nextInt(255));
        return ips;
    }

}
