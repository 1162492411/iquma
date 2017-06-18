package com.iquma.utils;

import com.iquma.pojo.Topic;
import com.iquma.service.TagService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 临时用类
 * Created by Mo on 2017/6/13.
 */
public class tempHelper extends HttpServlet {

    /**
     * 获取指定长度随机简体中文
     * @param len int
     * @return String
     */
    public static String getRandomJianHan(int len)
    {
        String ret="";
        for(int i=0;i<len;i++){
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
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


    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("启动了自定义的tempHelper");
        Random random = new Random();
        String[] sections = {"tutorial","question","articles","code"};
        String[] users = {"14010101","14010102","14010103","14010104","14010105","14010106","14010107","14010108"};
        Byte[] tags = {1,2,3,4,21,22,23,24,25,26,27,28,29,30,31,32,33};//tags 17个数，2~18
        List<Topic> topics = new ArrayList<>(120_0000);
        for (int i = 0; i < 120_0000; i++) {
            Date time = new Date(System.currentTimeMillis());
            Topic topic = new Topic();
            topic.setTitle("批量添加的主贴" + (i + 480_1000));
            topic.setSec(sections[random.nextInt(4)]);
            topic.setTid(tags[random.nextInt(16) + 1]);
            topic.setAid(users[random.nextInt(8)]);
            topic.setaddTime(time);
            topic.setReTime(time);
            topic.setLikeCount(Double.valueOf("1"));
            topic.setHateCount(Double.valueOf("0"));
            topic.setRateCount(Byte.valueOf("1"));
            topic.setReplyCount(0);
            topic.setIsBlock(false);
            topic.setHasBest(0);
            topic.setViewCount(random.nextInt(1200));
            topic.setContent("这是测试主贴x的内容，随机产生的数据是" + getRandomJianHan(random.nextInt(21) +10));
            topics.add(topic);
        }

        //读取配置
        System.out.println("开始读取mybatis配置");
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        System.out.println("读取完成mybatis配置");
        batchInsert(sqlSessionFactory,"com.iquma.mapping.TopicMapper.insert",topics);

    }

    /**
     * 批量插入
     *
     * @param sqlSessionFactory
     * @param statement 在Mapper文件中定义的namespce加上Mapper中定义的标识符
     * @param objList 要入库的数据列表
     */
    public static void batchInsert(SqlSessionFactory sqlSessionFactory, String statement, List<?> objList) {
        long start = System.currentTimeMillis();
        System.out.println("进入了批量插入方法，起始时间是" + start);
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            for (Object obj : objList) {
                session.insert(statement, obj);
            }
            session.flushStatements();
            session.commit();
            session.clearCache();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("完成了批量插入，耗时" + (end - start));
    }



}
