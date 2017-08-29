package com.iquma.test.environment;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.ejb.TransactionManagement;

/**
 * 测试环境类
 * Created by Mo on 2017/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/man/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = {"classpath:applicationContext.xml","classpath:spring-shiro.xml"}),
        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml"),
})
@Transactional
public class BaseTestEnvironment {

    @Autowired
    private WebApplicationContext wac;
    public MockMvc mockMvc;

    @Before
    public void settings(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

}
