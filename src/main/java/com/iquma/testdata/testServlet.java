package com.iquma.testdata;

import com.iquma.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 测试数据类,用于在服务器启动时将测试数据写入数据库
 * Created by Mo on 2017/6/13.
 */
public class testServlet extends HttpServlet {

    /**
     * 生成mybatis的SqSsessionFactory
     * @return SqlSessionFactory
     */
    private SqlSessionFactory getTestSqlSessionFactory(){
        //读取配置
        System.out.println("开始读取mybatis配置");
        String resource = "test-mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        Random random = new Random();
        SqlSessionFactory sqlSessionFactory = getTestSqlSessionFactory();
        //生成公共数据
        List<User> users = GenerateTestDataUtil.generateUsers(60);
        List<Date> dates = RandomUtil.generateRandomDates(random,2000);
        //插入测试数据
//        batchInsert(sqlSessionFactory,"com.iquma.testdata.TestMapper.insertUser",users);//插入学生账户
//        batchInsert(sqlSessionFactory,"com.iquma.testdata.TestMapper.insertSucLog",GenerateTestDataUtil.generateSuclogs(users,dates,800));//插入账户登录日志
//        for (int i = 1; i <= 15; i++)
//            batchInsert(sqlSessionFactory,"com.iquma.testdata.TestMapper.insertAttachment",GenerateTestDataUtil.generateAttachments(users,dates,104857600,1_0000));//插入附件
//        for (int i = 1; i <= 20; i++)
//        batchInsert(sqlSessionFactory,"com.iquma.testdata.TestMapper.insertTopic",GenerateTestDataUtil.generateTopics(users,dates,15_0000, 1_0000));//插入主贴
//        for (int i = 1; i <= 40; i++)
//            batchInsert(sqlSessionFactory,"com.iquma.testdata.TestMapper.insertReply",GenerateTestDataUtil.generateReplies(users,dates,25_0000,1_0000));//插入主贴回复
//        batchInsert(sqlSessionFactory,"com.iquma.testdata.TestMapper.insertFavorite",GenerateTestDataUtil.generateFavorites(users,dates,20_0000,68000));//插入账户收藏
//        batchInsert(sqlSessionFactory,"com.iquma.testdata.TestMapper.updateTopicReplyCount",GenerateTestDataUtil.generateTopicsForReplyCount(25_0000));//统一主贴表的回复总数字段
    }

    /**
     * 批量插入
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
