package com.iquma.controller;

import com.iquma.pojo.*;
import com.iquma.service.*;
import com.iquma.utils.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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
    @Autowired
    private SectionService sectionService;
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
    public String loginValidator(User user, HttpServletRequest request,Notification condition) throws AuthenticationException {
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
    public String toHome(@PathVariable String uid, Model model, Topic topic) {
        User user = this.userService.selectById(uid);
        if(user == null) throw new UnknownAccountException();
        else{
            model.addAttribute("user", user);
            topic.setAid(uid);
            topic.setSid(Byte.valueOf("1"));
            model.addAttribute("topics",getSimpleTopics(topicService.selectsByCondition(topic)));
            return "user/home";
        }
    }

    //获取简短的主贴集合
    public List<Topic> getSimpleTopics(List<Topic> topics){
        for (Topic topic: topics) {
            topic.setContent(topic.getContent().substring(0,topic.getContent().length()>=300?300:topic.getContent().length()));
        }
        return topics;
    }

    //前往用户相关主贴列表
    private String toList(String uid,String section,Topic topic,Model model){
        User user = this.userService.selectById(uid);
        if(user == null) throw new UnknownAccountException();
        else{
            model.addAttribute("user", user);
            topic.setAid(uid);
            topic.setSid(sectionService.selectByName(section).getId());
            model.addAttribute("topicType",section);
            model.addAttribute("topics",getSimpleTopics(topicService.selectsByCondition(topic)));
            return "user/lists";
        }
    }

    //前往用户的教程列表
    @RequestMapping(value = "{uid}/tutorials", method = RequestMethod.GET)
    public String toTutorials(@PathVariable String uid, Model model, Topic topic) {
        return toList(uid,"tutorial",topic,model);
    }

    //前往用户的提问列表
    @RequestMapping(value = "{uid}/discuss", method = RequestMethod.GET)
    public String toAsks(@PathVariable String uid, Model model, Topic topic) {
        return toList(uid,"discuss",topic,model);
    }

    //前往用户的回答列表
    @RequestMapping(value = "{uid}/answers", method = RequestMethod.GET)
    public String toAnswers(@PathVariable String uid, Model model,Reply condition) {
        User user = this.userService.selectById(uid);
        if(user == null) throw new UnknownAccountException();
        else{
            model.addAttribute("user", user);
            condition.setUid(uid);
            model.addAttribute("replies",replyService.selectByCondition(condition));
            return "user/answers";
        }
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
            model.addAttribute("collections",favoriteService.selectsByCondition(favorite));
            return "user/collections";
        }
    }


    //前往个人资料页面
    @RequestMapping(value = "{uid}/profile", method = RequestMethod.GET)
    public String toProfile(Model model) {
        User user = this.userService.selectById(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        if(user == null) {return "status/emptyQuery";}
        else{
            model.addAttribute("user", user);
            return "user/profile";
        }
    }

    //个人资料验证
    @RequestMapping(value = "{uid}/profile", method = RequestMethod.PUT)
    public @ResponseBody String profileValidator(User record) {
        if (this.userService.update(record))
            return "suc";
        else return"err";
    }

    //前往邮箱及密码页面
    @RequestMapping(value = "{uid}/account", method = RequestMethod.GET)
    public String toAccount( Model model) {
        User user = this.userService.selectById(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        model.addAttribute("user", user);
        return "user/account";
    }

    //邮箱及密码验证
    @RequestMapping(value = "{uid}/account", method = RequestMethod.PUT)
    public @ResponseBody Boolean accountValidator(@RequestBody String pass,User record) {
        record.setId(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        record.setPass(pass);
        passwordHelper.encryptPassword(record);
        if (this.userService.update(record))
            return Boolean.TRUE;
        else return Boolean.FALSE;
    }

    //前往重置密码页面
    @RequestMapping(value = "forgot", method = RequestMethod.GET)
    public String toForgot() {
        return "user/forgot";
    }

    //重置密码验证
    @RequestMapping(value = "forgot", method = RequestMethod.POST)
    public String forgotValidator(User record) {
        if (this.userService.validatorEmail(record.getId(), record.getEmail()) && this.userService.update(record))
            return "suc";
        else return "err";
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
        model.addAttribute("ntfs",notificationService.selectsByCondition(condition));
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



}