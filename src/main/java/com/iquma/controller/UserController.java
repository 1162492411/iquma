package com.iquma.controller;

import com.iquma.pojo.*;
import com.iquma.service.*;
import com.iquma.utils.CASTS;
import com.iquma.utils.ENUMS;
import com.iquma.utils.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private PasswordHelper passwordHelper;
    public String result;

    //前往登录页面
    @RequiresGuest
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    //验证前台登陆
    //TODO :该方法可以改造为返回json信息，登录失败时显示回传的失败提示信息，登录成功时跳转到登陆前页面(自定义shiro拦截器)
    @RequiresGuest
    @RequestMapping(value = "loginValidator")
    public String loginValidator(User user, HttpServletRequest request,Notification condition) throws ShiroException {
        System.out.println("验证前台登录" + user.getId() + "..." + user.getPass());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getId(),user.getPass());
        subject.login(token);//会跳到自定义的realm中
        user = userService.selectById(user.getId());//成功验证后获取用户数据
        Session session = subject.getSession();//获取session
        session.setAttribute("userid",user.getId());//将用户数据绑定到session中
        session.setAttribute("username",user.getName());
        session.setAttribute("useravatar",user.getAvatar());
        condition.setId(null);
        condition.setUid(user.getId());//查询用户是否存在未读通知
        condition.setisnew(Boolean.TRUE);
        int ntfCount = notificationService.selectsByCondition(condition).size();
        if(ntfCount> 0)//用户存在未读通知时绑定未读通知数量到session中
            session.setAttribute("ntfCount", ntfCount);
        return "index";
    }


    //前往用户个人主页
    @RequestMapping(value = "{uid}/home", method = RequestMethod.GET)
    public String toHome(@PathVariable String uid, Model model, Reply condition) {
        return toAnswers(uid,model,condition);
    }

    //获取简短的主贴集合
    public List<Topic> getSimpleTopics(List<Topic> topics){
        for (Topic topic: topics) {
            String temp = topic.getContent().substring(0,topic.getContent().length() >= 300?300:topic.getContent().length());//首先截取前300个字符
            if(temp.contains("<pre>") && ! temp.contains("</pre>"))
                temp = temp + "</code></pre>";//若未结束代码则结束代码
            topic.setContent(temp);
        }
        return topics;
    }

    /**
     * 前往用户相关主贴列表--通用
     * @param aid 待查看用户的id
     * @param section 待查看的版块--tutorials/discusses/articles/codes
     */
    private String toList(String aid,String section,Topic topic,Model model){
        User user = userService.selectById(aid);
        if(null == user) throw new UnknownAccountException();//若用户不存在则抛出异常
        topic.setAid(aid);
        topic.setSec(section);
        System.out.println("topic条件参数是" + topic);
        List topics = topicService.selectsByCondition(topic);
        if(topics.size() == 0) model.addAttribute("emptyResult",Boolean.TRUE);//查询结果为空时绑定信息
        else model.addAttribute("topics",CASTS.translateToSB(getSimpleTopics(topics)));//查询结果非空时绑定结果
        model.addAttribute("user",user);
        return "user/lists";
    }

    //前往用户的教程列表
    @RequestMapping(value = "{uid}/tutorials", method = RequestMethod.GET)
    public String toTutorials(@PathVariable String uid, Model model, Topic topic) {
        return toList(uid,"tutorial",topic,model);
    }

    //前往用户的提问列表
    @RequestMapping(value = "{uid}/questions", method = RequestMethod.GET)
    public String toAsks(@PathVariable String uid, Model model, Topic topic) {
        return toList(uid,"question",topic,model);
    }

    //前往用户的回答列表
    @RequestMapping(value = "{uid}/answers", method = RequestMethod.GET)
    public String toAnswers(@PathVariable String uid, Model model,Reply condition) {
        User user = userService.selectById(uid);
        if(null == user) throw new UnknownAccountException();//若用户不存在则抛出异常
        model.addAttribute("user",user);
        List results = replyService.selectByCondition(condition);
        if(results.size() == 0) model.addAttribute("emptyResult",Boolean.TRUE);
        else model.addAttribute("replies",CASTS.translateToSB(results));
        return "user/answers";
    }

    //前往用户的文章列表
    @RequestMapping(value = "{uid}/articles", method = RequestMethod.GET)
    public String toArticles(@PathVariable String uid, Model model, Topic topic) {
        return toList(uid,"article",topic,model);
    }

    //前往用户的代码列表
    @RequestMapping(value = "{uid}/codes", method = RequestMethod.GET)
    public String toCodes(@PathVariable String uid, Model model, Topic topic) {
        return toList(uid,"code",topic,model);
    }

    //前往用户的收藏列表
    @RequestMapping(value = "{uid}/collections", method = RequestMethod.GET)
    public String toCollections(@PathVariable String uid, Favorite favorite, Model model) {
        User user = this.userService.selectById(uid);
        if(user == null) throw new UnknownAccountException();
        else{
            model.addAttribute("user", user);
            List results = favoriteService.selectsByCondition(favorite);
            if(results.size() == 0) model.addAttribute("emptyResult",Boolean.TRUE);
            else model.addAttribute("collections",CASTS.translateToSB(results));
            return "user/collections";
        }
    }


    //前往个人资料页面
    @RequestMapping(value = "{uid}/profile", method = RequestMethod.GET)
    public String toProfile(Model model) {
        User user = this.userService.selectById(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));//为保证安全从session中取值
        if(user == null) throw new UnknownAccountException();
        model.addAttribute("user", user);
        return "user/profile";
    }

    //个人资料验证
    @RequestMapping(value = "{uid}/profile", method = RequestMethod.PUT)
    public @ResponseBody Boolean profileValidator(@RequestBody User record) {
        System.out.println("修改用户资料时传入参数" + record);
        if (this.userService.update(record)){//成功修改用户资料后修改session中的useravatar的值(bannar.jsp访问该值)
            SecurityUtils.getSubject().getSession().setAttribute("useravatar",record.getAvatar());
            return Boolean.TRUE;
        }
        else return Boolean.FALSE;
    }

    //前往修改密码页面
    @RequestMapping(value = "{uid}/account", method = RequestMethod.GET)
    public String toAccount( Model model) {
        User user = this.userService.selectById(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        model.addAttribute("user", user);
        return "user/account";
    }

    //修改密码验证
    @RequestMapping(value = "{uid}/account", method = RequestMethod.PUT)
    public @ResponseBody Boolean accountValidator(@RequestBody String pass,User record) {
        record.setId(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        record.setPass(pass);
        passwordHelper.encryptPassword(record);
        if (this.userService.update(record))
            return Boolean.TRUE;
        else return Boolean.FALSE;
    }

    //退出登录
    @RequestMapping(value = "{uid}/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        Session session = SecurityUtils.getSubject().getSession();
        session.removeAttribute("userid");
        session.removeAttribute("username");
        session.removeAttribute("useravatar");
        return "index";
    }

    //前往用户通知页面
    @RequestMapping(value = "{uid}/ntfs", method = RequestMethod.GET)
    public String toNotifications(Notification condition, Model model) {
        condition.setUid(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        model.addAttribute("ntfs",CASTS.translateToSB(notificationService.selectsByCondition(condition)));
        return "user/notifications";
    }

    //将通知标记为已读
    @RequestMapping(value = "{uid}/ntfs/{id}" , method = RequestMethod.PUT)
    public @ResponseBody Boolean readNotifications(Notification record){
        Boolean result = this.notificationService.read(record);
        if(result){
            Session session = SecurityUtils.getSubject().getSession();
            int ntfCount = Integer.parseInt(session.getAttribute("ntfCount").toString());
            ntfCount -= 1;
            session.setAttribute("ntfCount",ntfCount);
        }
        return result;
    }

    //前往提问页面
    //TODO: 待更新前台路径(提问、分享经验、上传代码)
    @RequestMapping(value = "ask", method = RequestMethod.GET)
    public String toAsk() {
        return "editor/ask";
    }

    //提问验证页面
    @RequestMapping(value = "ask", method = RequestMethod.PUT)
    public @ResponseBody Boolean ask(@RequestBody Topic record) {
        record.parseDefaultDiscuss();
        return topicService.insert(record);
    }


    //前往分享经验页面
    @RequestMapping(value = "write", method = RequestMethod.GET)
    public String toWrite() {
        return "editor/write";
    }

    //分享经验验证页面
    @RequestMapping(value = "write", method = RequestMethod.PUT)
    public @ResponseBody Boolean write(@RequestBody  Topic record) {
        record.parseDefaultArticle();
        return topicService.insert(record);
    }


    //前往分享代码页面
    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String toUpload() {
        return "editor/upload";
    }

    //分享代码验证
    @RequestMapping(value = "upload", method = RequestMethod.PUT)
    public @ResponseBody Boolean upload(@RequestBody Topic record) {
        record.parseDefaultCode();
        return topicService.insert(record);
    }

    //封禁账户
    @RequiresPermissions("suser:block")
    @RequestMapping(value = "{uid}/block", method = RequestMethod.POST)
    public @ResponseBody boolean blockUser(@PathVariable String uid){
        if(hasEnoughRole(uid)) return userService.changeStatus(uid);//仅当角色符合条件时进行操作
        else return false;
    }

    //删除账户
    @RequiresPermissions("suser:delete")
    @RequestMapping(value = "{uid}/delete", method = RequestMethod.POST)
    public @ResponseBody boolean deleteUser(@PathVariable String uid){
        if(hasEnoughRole(uid)) return userService.delete(uid);//仅当角色符合条件时进行操作
        else return false;
    }

    //前往添加用户页面
    @RequestMapping(value = {"addStudent","addTeacher"}, method = RequestMethod.GET)
    public String toAddUser(){
        return "user/add";
    }

    //添加学生用户验证
    @RequiresPermissions("suser:create")
    @RequestMapping(value = "addStudent", method = RequestMethod.POST)
    public @ResponseBody boolean addStudent(@RequestBody User user){
        user.setRid(Byte.parseByte(String.valueOf(ENUMS.ROLE_BRONZE)));//设置待添加账户的角色为青铜账户
        user.parseDefaultAddUser();
        return userService.insert(user);
    }

    //添加教师用户验证
    @RequiresPermissions("muser:create")
    @RequestMapping(value = "addTeacher", method = RequestMethod.POST)
    public @ResponseBody boolean addTeacher(@RequestBody User user){
        user.setRid(Byte.parseByte(String.valueOf(ENUMS.ROLE_TEACHER)));//设置待添加账户的角色为教师账户
        user.parseDefaultAddUser();
        return userService.insert(user);
    }


    //当前登录用户是否角色等级高于待操作用户
    private boolean hasEnoughRole(String uid){
        User currentUser = userService.selectById(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));//取出当前登录账户
        User user = userService.selectById(uid); //查询待操作的账户
        //判断二者角色等级:仅当当前登录账户是教师级以上账户且当前账户角色高于待操作账户时允许对账户进行操作
        return currentUser.getRid() <= ENUMS.ROLE_TEACHER && currentUser.getRid() < user.getRid();
    }
}
