package com.iquma.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.iquma.utils.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.iquma.pojo.User;
import com.iquma.service.UserService;


@Controller
@RequestMapping("/user")
public class userController {

    @Resource
    private UserService userService;
    @Autowired
    PasswordHelper passwordHelper;
    public String result;

    //前往登录页面
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    //验证前台登陆
    @RequestMapping(value = "logindo")
    public String loginValidator(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getId(),user.getPass());
        try{
            subject.login(token);//会跳到我们自定义的realm中
            Session session = subject.getSession();
            session.setAttribute("userid",user.getId());
            return "index";
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("控制器遇到了异常了！！！");
            return "login";
        }
    }


    //前往用户个人主页
    @RequestMapping(value = "{uid}/home", method = RequestMethod.GET)
    public String toHome(@PathVariable("uid") String uid, Model model) {
        User user = this.userService.selectById(uid);
        model.addAttribute("user", user);
        return "user/home";
    }


    //前往个人资料页面
    @RequestMapping(value = "{uid}/profile", method = RequestMethod.GET)
    public String toProfile(@PathVariable("uid") String uid, Model model) {
        model.addAttribute(this.userService.selectById(uid));
        return "user/profile";
    }

    //个人资料验证
    @RequestMapping(value = "{uid}/profile", method = RequestMethod.PUT)
    public String profileValidator(User record, Model model) {
        if (this.userService.update(record))
            result = "成功修改个人资料";
        else result  = "未能修改个人资料";
        model.addAttribute("result",result);
        return "status/actionResult";
    }

    //前往邮箱及密码页面
    @RequestMapping(value = "{uid}/account", method = RequestMethod.GET)
    public String toAccount(@PathVariable("uid") String uid, Model model) {
        model.addAttribute(this.userService.selectById(uid));
        return "user/account";
    }

    //邮箱及密码验证
    @RequestMapping(value = "{uid}/account", method = RequestMethod.PUT)
    public String accountValidator(User record,Model model) {
        if (this.userService.update(record))
            result = "成功修改邮箱密码";
        else result  = "未能修改邮箱密码";
        model.addAttribute("result",result);
        return "status/actionResult";
    }

    //前往重置密码页面
    @RequestMapping(value = "forgot", method = RequestMethod.GET)
    public String toForgot() {
        return "user/forgot";
    }

    //重置密码验证
    @RequestMapping(value = "forgot", method = RequestMethod.POST)
    public String forgotValidator(User record, Model model) {
        if (this.userService.validatorEmail(record.getId(), record.getEmail()) && this.userService.update(record))
            result = "成功重置密码";
        else result  = "未能重置密码";
        model.addAttribute("result",result);
        return "status/actionResult";
    }

    //退出登录
    @RequestMapping(value = "{uid}/logout")
    public String logout(Model model){
        SecurityUtils.getSubject().logout();
        SecurityUtils.getSubject().getSession().removeAttribute("userid");
        model.addAttribute("result","您已经退出");
        return "status/actionResult";
    }

    //前往用户通知页面，未完成
    @RequestMapping(value = "{uid}/ntfs", method = RequestMethod.GET)
    public String toNotifications(@PathVariable("uid") String uid, Model model) {
        model.addAttribute("uid", uid);
        return "user/notifications";
    }


}
