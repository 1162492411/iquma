package com.iquma.test.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.junit.Assert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 账户登录工具类
 */
public class LoginUtils {

    /**
     * 模拟超级管理员账户登录
     */
    public static void loginAsSuperAdmin(MockMvc mockMvc) throws Exception {
        String userid = ENUMS.TEST_SUPERADMIN_USERID;
        login(mockMvc, userid, userid.substring(userid.length() - 6, userid.length()));
    }

    /**
     * 模拟管理员账户登录
     */
    public static void loginAsAdmin(MockMvc mockMvc) throws Exception {
        String userid = ENUMS.TEST_ADMIN_USERID;
        login(mockMvc, userid, userid.substring(userid.length() - 6, userid.length()));
    }

    /**
     * 模拟教师账户登录
     */
    public static void loginAsTeacher(MockMvc mockMvc) throws Exception {
        String userid = ENUMS.TEST_TEACHER_USERID;
        login(mockMvc, userid, userid.substring(userid.length() - 6, userid.length()));
    }

    /**
     * 模拟钻石账户登录
     */
    public static void loginAsDIAMOND(MockMvc mockMvc) throws Exception {
        String userid = ENUMS.TEST_DIAMOND_USERID;
        login(mockMvc, userid, userid.substring(userid.length() - 6, userid.length()));
    }

    /**
     * 模拟白金账户登录
     */
    public static void loginAsPLATNUM(MockMvc mockMvc) throws Exception {
        String userid = ENUMS.TEST_PLATNUM_USERID;
        login(mockMvc, userid, userid.substring(userid.length() - 6, userid.length()));
    }

    /**
     * 模拟黄金账户登录
     */
    public static void loginAsGOLD(MockMvc mockMvc) throws Exception {
        String userid = ENUMS.TEST_GOLD_USERID;
        login(mockMvc, userid, userid.substring(userid.length() - 6, userid.length()));
    }

    /**
     * 模拟白银账户登录
     */
    public static void loginAsSILVER(MockMvc mockMvc) throws Exception {
        String userid = ENUMS.TEST_SILVER_USERID;
        login(mockMvc, userid, userid.substring(userid.length() - 6, userid.length()));
    }

    /**
     * 模拟青铜账户登录
     */
    public static void loginAsBRONZE(MockMvc mockMvc) throws Exception {
        String userid = ENUMS.TEST_BRONZE_USERID;
        login(mockMvc, userid, userid.substring(userid.length() - 6, userid.length()));
    }

    /**
     * 快速登录
     * 该方法用于默认密码的账户，即密码为账户的后六位
     */
    public static void loginFast(MockMvc mockMvc,String userid) throws Exception {
        login(mockMvc, userid, userid.substring(userid.length() - 6, userid.length()));
    }

    public static void login(MockMvc mockMvc, String id, String password) throws Exception {
        Session session = SecurityUtils.getSubject().getSession();
        Assert.assertNull(session.getAttribute("userid"));
        Assert.assertNull(session.getAttribute("username"));
        Assert.assertNull(session.getAttribute("useravatar"));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/loginValidator?id=" + id + "&pass=" + password))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertNotNull(session.getAttribute("userid"));
        Assert.assertNotNull(session.getAttribute("username"));
        Assert.assertNotNull(session.getAttribute("useravatar"));
    }

}
