package com.iquma.test.controller;

import com.iquma.pojo.User;
import com.iquma.test.environment.BaseTestEnvironment;
import com.iquma.test.utils.LoginUtils;
import com.iquma.utils.GsonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
import org.springframework.ui.ModelMap;
import com.iquma.test.utils.ENUMS;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 集成测试UserController类
 * Created by Mo on 2017/8/23.
 */
public class UserControllerTest extends BaseTestEnvironment{

    //验证页数-- 页数合理
    public void validatePage(ModelMap modelMap){
        int currentPage = Integer.parseInt(String.valueOf(modelMap.get("currentPage")));
        int totalPage = Integer.parseInt(String.valueOf(modelMap.get("totalPage")));
        Assert.assertTrue(currentPage > 0);
        Assert.assertTrue(currentPage <= totalPage);
    }

    //验证页数--currentPage == totalPage == 0
    public void validatePageEmpty(ModelMap modelMap){
        int currentPage = Integer.parseInt(String.valueOf(modelMap.get("currentPage")));
        int totalPage = Integer.parseInt(String.valueOf(modelMap.get("totalPage")));
        Assert.assertEquals(currentPage,0);
        Assert.assertEquals(totalPage,0);
    }

    //验证为空
    public void validateJavaEmpty(Object ...obj){
        for (Object o : obj)
            Assert.assertNull(o);
    }

    //验证为json空
    public void validateJsonEmpty(Object ...obj){
        for(Object o : obj)
            Assert.assertEquals(o,"[]");
    }

    //验证非空
    public void validateJavaNotEmpty(Object ...obj){
        for (Object o : obj)
            Assert.assertNotNull(o);
    }

    //验证为json非空
    public void validateJsonNotEmpty(Object ...obj){
        for(Object o : obj)
            Assert.assertNotEquals(o,"[]");
    }

    //测试前往登录页面
    @Test
    public void toLogin_01() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/login"))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //测试登录账户--正常账户
    @Test
    public void loginValidator_01() throws Exception {
        Session session = SecurityUtils.getSubject().getSession();
        Assert.assertNull(session.getAttribute("userid"));
        Assert.assertNull(session.getAttribute("username"));
        Assert.assertNull(session.getAttribute("useravatar"));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/loginValidator?id=541413140001&pass=140001"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertNotNull(session.getAttribute("userid"));
        Assert.assertNotNull(session.getAttribute("username"));
        Assert.assertNotNull(session.getAttribute("useravatar"));
    }

    //测试登录账户--重复登录
    @Test
    public void loginValidator_02() throws Exception {
        Session session = SecurityUtils.getSubject().getSession();
        Assert.assertNull(session.getAttribute("userid"));
        Assert.assertNull(session.getAttribute("username"));
        Assert.assertNull(session.getAttribute("useravatar"));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/loginValidator?id=541413140001&pass=140001"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Object userid,username,useravatar;
        Assert.assertNotNull(userid = session.getAttribute("userid"));
        Assert.assertNotNull(username = session.getAttribute("username"));
        Assert.assertNotNull(useravatar = session.getAttribute("useravatar"));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/loginValidator?id=541413140001&pass=140001"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertEquals(session.getAttribute("userid"),userid);
        Assert.assertEquals(session.getAttribute("username"),username);
        Assert.assertEquals(session.getAttribute("useravatar"),useravatar);
    }

    //测试登录账户--不存在的账户
    @Test
    public void loginValidator_03() throws Exception {
        Session session = SecurityUtils.getSubject().getSession();
        Assert.assertNull(session.getAttribute("userid"));
        Assert.assertNull(session.getAttribute("username"));
        Assert.assertNull(session.getAttribute("useravatar"));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/loginValidator?id=123456&pass=123456"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("status/error"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertNull(session.getAttribute("userid"));
        Assert.assertNull(session.getAttribute("username"));
        Assert.assertNull(session.getAttribute("useravatar"));
    }

    //测试登录账户--被封禁账户
    @Test
    public void loginValidator_04() throws Exception {
        Session session = SecurityUtils.getSubject().getSession();
        Assert.assertNull(session.getAttribute("userid"));
        Assert.assertNull(session.getAttribute("username"));
        Assert.assertNull(session.getAttribute("useravatar"));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/loginValidator?id=541401030101&pass=030101"))
                .andExpect(MockMvcResultMatchers.view().name("status/error"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertNull(session.getAttribute("userid"));
        Assert.assertNull(session.getAttribute("username"));
        Assert.assertNull(session.getAttribute("useravatar"));
    }

    //测试登录账户--账户名错误
    @Test
    public void loginValidator_05() throws Exception {
        Session session = SecurityUtils.getSubject().getSession();
        Assert.assertNull(session.getAttribute("userid"));
        Assert.assertNull(session.getAttribute("username"));
        Assert.assertNull(session.getAttribute("useravatar"));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/loginValidator?id=null&pass=123456"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("status/error"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertNull(session.getAttribute("userid"));
        Assert.assertNull(session.getAttribute("username"));
        Assert.assertNull(session.getAttribute("useravatar"));
    }

    //测试登录账户--正常账户、密码错误
    @Test
    public void loginValidator_06() throws Exception {
        Session session = SecurityUtils.getSubject().getSession();
        Assert.assertNull(session.getAttribute("userid"));
        Assert.assertNull(session.getAttribute("username"));
        Assert.assertNull(session.getAttribute("useravatar"));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/loginValidator?id=541413140001&pass=14000143"))
                .andExpect(MockMvcResultMatchers.view().name("status/error"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertNull(session.getAttribute("userid"));
        Assert.assertNull(session.getAttribute("username"));
        Assert.assertNull(session.getAttribute("useravatar"));
    }

    //测试前往用户主页--正常账户、存在数据
    @Test
    public void toHome_01() throws Exception{
        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/home","541413150201"))
                .andExpect(MockMvcResultMatchers.view().name("user/answers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        ModelMap model = result.getModelAndView().getModelMap();
        validateJavaNotEmpty(model.get("user"),model.get("replies"));
        validatePage(model);
    }

    //测试前往用户主页--不存在的账户
    @Test
    public void toHome_02() throws Exception{
         mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/home",ENUMS.TEST_DOESNOTEXIST_USERID))
                .andExpect(MockMvcResultMatchers.view().name("status/error"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("user"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("replies"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("totalPage"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("currentPage"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //测试前往用户主页--正常账户--不存在数据
    @Test
    public void toHome_03() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/home",ENUMS.TEST_SUPERADMIN_USERID))
                .andExpect(MockMvcResultMatchers.view().name("user/answers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        ModelMap modelMap = result.getModelAndView().getModelMap();
        validateJavaNotEmpty(modelMap.get("user"));
        validateJsonEmpty(modelMap.get("replies"));
        validatePageEmpty(modelMap);
    }

    //测试前往用户教程列表--正常账户、数据存在
    @Test
    public void toTutorials_01() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/tutorials",ENUMS.TEST_SUPERADMIN_USERID))
                .andExpect(MockMvcResultMatchers.view().name("user/lists"))
                .andExpect(MockMvcResultMatchers.model().attribute("sec",ENUMS.SEC_TUTORIAL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        ModelMap modelMap = result.getModelAndView().getModelMap();
        Assert.assertNotNull(modelMap.get("user"));
        Assert.assertNotNull(modelMap.get("topics"));
        validatePage(modelMap);
    }

    //测试前往用户教程列表--正常账户、不存在数据
    @Test
    public void toTutorials_02() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/tutorials",ENUMS.TEST_SUPERADMIN_USERID))
                .andExpect(MockMvcResultMatchers.view().name("user/lists"))
                .andExpect(MockMvcResultMatchers.model().attribute("sec",ENUMS.SEC_TUTORIAL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertNotNull(result.getModelAndView().getModel().get("user"));
        validatePageEmpty(result.getModelAndView().getModelMap());
    }

    //测试前往用户教程列表--不存在账户
    @Test
    public void toTutorials_03() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/tutorials",ENUMS.TEST_DOESNOTEXIST_USERID))
                .andExpect(MockMvcResultMatchers.view().name("status/error"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //测试前往用户教程/提问/经验/代码--账户存在、存在数据
    public void toList(String sec, Object userid, Object page) throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/" + sec + "s/{page}",userid,page))
                .andExpect(MockMvcResultMatchers.view().name("user/lists"))
                .andExpect(MockMvcResultMatchers.model().attribute("sec",sec))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        ModelMap modelMap = result.getModelAndView().getModelMap();
        Assert.assertNotNull(modelMap.get("user"));
        Assert.assertNotNull(modelMap.get("topics"));
        validatePage(modelMap);
    }

    //测试前往用户教程/提问/经验/代码--账户存在、无数据
    public void toListEmpty(String sec, Object userid, Object page) throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/" + sec + "s/{page}",userid,page))
                .andExpect(MockMvcResultMatchers.view().name("user/lists"))
                .andExpect(MockMvcResultMatchers.model().attribute("sec",sec))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        ModelMap modelMap = result.getModelAndView().getModelMap();
        Assert.assertNotNull(modelMap.get("user"));
        Assert.assertEquals(modelMap.get("topics"),"[]");
        validatePageEmpty(modelMap);
    }

    //测试前往用户教程/提问/经验/代码--异常
    public void toListException(String sec, Object userid, Object page) throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/" + sec + "/{page}",userid,page))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //测试前往分页的用户教程列表--合理账户、存在数据、页数为1
    @Test
    public void toTutorialsByPage_01() throws Exception{
        toList(ENUMS.SEC_TUTORIAL,"541413150203",1);
    }

    //测试前往分页的用户教程列表--合理账户、存在数据、页数为负
    @Test
    public void toTutorialsByPage_02() throws Exception{
        toList(ENUMS.SEC_TUTORIAL,"541413150203",-200);
    }

    //测试前往分页的用户教程列表--合理账户、存在数据、页数为0
    @Test
    public void toTutorialsByPage_03() throws Exception{
        toList(ENUMS.SEC_TUTORIAL,"541413150203",-200);
    }

    //测试前往分页的用户教程列表--合理账户、存在数据、页数为总页数
    @Test
    public void toTutorialsByPage_04() throws Exception{
        toList(ENUMS.SEC_TUTORIAL,"541413150203",17);
    }

    //测试前往分页的用户教程列表--合理账户、存在数据、页数大于总页数但在int范围
    @Test
    public void toTutorialsByPage_05() throws Exception{
        toList(ENUMS.SEC_TUTORIAL,"541413150203",1_0000_0000);
    }

    //测试前往分页的用户教程列表--合理账户、存在数据、页数大于总页数且超出int范围
    @Test
    public void toTutorialsByPage_06() throws Exception{
        toListException(ENUMS.SEC_TUTORIAL,"541413150203",1000_0000_0000L);
    }

    //测试前往分页的用户教程列表--合理账户、存在数据、页数为null
    @Test
    public void toTutorialsByPage_07() throws Exception{
        toListException(ENUMS.SEC_TUTORIAL,"541413150203",null);
    }

    //测试前往分页的用户教程列表--合理账户、存在数据、页数为"null"
    @Test
    public void toTutorialsByPage_08() throws Exception{
        toListException(ENUMS.SEC_TUTORIAL,"541413150203","null");
    }

    //测试前往分页的用户教程列表--合理账户、存在数据、页数为随机字符串
    @Test
    public void toTutorialsByPage_09() throws Exception{
        toListException(ENUMS.SEC_TUTORIAL,"541413150203","dwf");
    }

    //测试前往分页的用户教程列表--合理账户、不存在数据、页数为1
    @Test
    public void toTutorialsByPage_10() throws Exception{
        toListEmpty(ENUMS.SEC_TUTORIAL,ENUMS.TEST_SUPERADMIN_USERID,1);
    }

    //测试前往分页的用户教程列表--合理账户、不存在数据、页数为负
    @Test
    public void toTutorialsByPage_11() throws Exception{
        toListEmpty(ENUMS.SEC_TUTORIAL,ENUMS.TEST_SUPERADMIN_USERID,-200);
    }

    //测试前往分页的用户教程列表--合理账户、不存在数据、页数为0
    @Test
    public void toTutorialsByPage_12() throws Exception{
        toListEmpty(ENUMS.SEC_TUTORIAL,ENUMS.TEST_SUPERADMIN_USERID,0);
    }

    //测试前往分页的用户教程列表--合理账户、不存在数据、页数大于总页数但在int范围
    @Test
    public void toTutorialsByPage_13() throws Exception{
        toListEmpty(ENUMS.SEC_TUTORIAL,ENUMS.TEST_SUPERADMIN_USERID,1_0000_0000);
    }

    //测试前往分页的用户教程列表--合理账户、不存在数据、页数大于总页数且超出int范围
    @Test
    public void toTutorialsByPage_14() throws Exception{
        toListException(ENUMS.SEC_TUTORIAL,ENUMS.TEST_SUPERADMIN_USERID,1000_0000_0000L);
    }

    //测试前往分页的用户教程列表--账户为null
    @Test
    public void toTutorialsByPage_15() throws Exception{
        toListException(ENUMS.SEC_TUTORIAL,null,1);
    }

    //测试前往分页的用户教程列表--账户为"null"
    @Test
    public void toTutorialsByPage_16() throws Exception{
        toListException(ENUMS.SEC_TUTORIAL,"null",1);
    }

    //测试前往分页的用户教程列表--账户为随机字符串
    @Test
    public void toTutorialsByPage_17() throws Exception{
        toListException(ENUMS.SEC_TUTORIAL,"dsdsdsf",1);
    }

    //由于前往分页的用户问题/经验/代码列表与教程列表类似，因此仅选择教程列表测试

    //测试前往用户收藏页面--正常账户、有数据
    @Test
    public void toCollections_01() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/collections","541413150260"))
                .andExpect(MockMvcResultMatchers.view().name("user/collections"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        ModelMap modelMap = result.getModelAndView().getModelMap();
        validateJavaNotEmpty(modelMap.get("user"),modelMap.get("collections"));
        validatePage(modelMap);
    }

    //测试前往用户收藏页面--正常账户、无数据
    @Test
    public void toCollections_02() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/collections",ENUMS.TEST_SUPERADMIN_USERID))
                .andExpect(MockMvcResultMatchers.view().name("user/collections"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        ModelMap modelMap = result.getModelAndView().getModelMap();
        validateJavaNotEmpty(modelMap.get("user"));
        validateJsonEmpty(modelMap.get("collections"));
        validatePageEmpty(modelMap);
    }

    //测试前往用户收藏页面--不存在用户
    @Test
    public void toCollections_03() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/collections",ENUMS.TEST_DOESNOTEXIST_USERID))
                .andExpect(MockMvcResultMatchers.view().name("status/error"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //测试前往分页的用户收藏页面--正常账户、有数据、页数合理
    @Test
    public void toCollectionsByPage_01() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/collections/{page}","541413150260",1))
                .andExpect(MockMvcResultMatchers.view().name("user/collections"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        ModelMap modelMap = result.getModelAndView().getModelMap();
        validateJavaNotEmpty(modelMap.get("user"),modelMap.get("collections"));
        validatePage(modelMap);
    }

    //账户参数及页数参数其余情况在toTutorialsByPage已进行测试

    //测试前往用户个人资料页面--正常账户、正常登录、同一账户
    @Test
    public void toProfile_01() throws Exception {
        LoginUtils.loginAsSuperAdmin(mockMvc);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/profile",ENUMS.TEST_SUPERADMIN_USERID))
                .andExpect(MockMvcResultMatchers.view().name("user/profile"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertEquals(((User)result.getModelAndView().getModelMap().get("user")).getId(),ENUMS.TEST_SUPERADMIN_USERID);
    }

    //测试前往用户个人资料页面--正常账户、正常登录、不同账户
    @Test
    public void toProfile_02() throws Exception {
        LoginUtils.loginAsSuperAdmin(mockMvc);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/profile",ENUMS.TEST_SUPERADMIN_USERID))
                .andExpect(MockMvcResultMatchers.view().name("user/profile"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertNotEquals(((User)result.getModelAndView().getModelMap().get("user")).getId(),ENUMS.TEST_ADMIN_USERID);
    }

    //测试前往用户个人资料页面--正常账户、不登陆
    @Test
    public void toProfile_03() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{uid}/profile",ENUMS.TEST_SUPERADMIN_USERID))
                .andExpect(MockMvcResultMatchers.view().name("status/error"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //测试修改个人资料--正常账户、同一账户
    @Test
    public void profileValidator_01() throws Exception{
        LoginUtils.loginAsSILVER(mockMvc);
        User user = new User();
        user.setId(ENUMS.TEST_SILVER_USERID);
        user.setName("");
        user.setDescription("");
        user.setEmail("");
        String data = GsonUtil.toJson(user);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/user/{diu}/profile",ENUMS.TEST_SILVER_USERID).contentType(MediaType.APPLICATION_JSON).content(data).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertEquals(result.getResponse().getContentAsString(),"true");
    }

    //TODO:该处实际未测出是否修改了自己的账户
    //测试修改个人资料--正常账户、不同账户
    @Test
    public void profileValidator_02() throws Exception{
        LoginUtils.loginAsSuperAdmin(mockMvc);
        User user = new User();
        user.setId(ENUMS.TEST_SILVER_USERID);
        user.setName("");
        user.setDescription("");
        user.setEmail("");
        String data = GsonUtil.toJson(user);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/user/{diu}/profile",ENUMS.TEST_SILVER_USERID).contentType(MediaType.APPLICATION_JSON).content(data).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertEquals(result.getResponse().getContentAsString(),"true");
    }

    //测试前往修改密码页面--正常账户、同一账户
    @Test
    public void toAccount_01() throws Exception{
        LoginUtils.loginAsSILVER(mockMvc);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{diu}/account",ENUMS.TEST_SILVER_USERID))
                .andExpect(MockMvcResultMatchers.view().name("user/account"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        ModelMap modelMap = result.getModelAndView().getModelMap();
        Assert.assertEquals(((User)modelMap.get("user")).getId(),ENUMS.TEST_SILVER_USERID);
    }




}
//TODO:编写链式POJO生成器