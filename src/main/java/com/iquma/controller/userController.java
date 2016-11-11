package com.iquma.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.iquma.pojo.User;
import com.iquma.service.UserService;

import java.util.ArrayList;
import java.util.LinkedList;


@Controller
@RequestMapping("/user")
public class userController {

    @Resource
    private UserService userService;
    String result;

    //前往登录页面
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    //验证前台登陆
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginValidator(User user, HttpServletRequest request, Model model) {
        if (this.userService.validatorUserPass(user.getId(),user.getPass())) {
            HttpSession session = request.getSession();
            session.setAttribute("userid",user.getId());
            result = "登录成功";
        } else {
            result = "登录失败";
        }
        model.addAttribute("result",result);
        return "status/actionResult";
    }

    //前往用户个人主页
    @RequestMapping(value = "{uid}/home", method = RequestMethod.GET)
    public String toHome(@PathVariable("uid") String uid, Model model) {
        User user = this.userService.getUserById(uid);
        model.addAttribute("user", user);
        return "user/home";
    }


    //前往个人资料页面
    @RequestMapping(value = "{uid}/profile", method = RequestMethod.GET)
    public String toProfile(@PathVariable("uid") String uid, Model model) {
        model.addAttribute(this.userService.getUserById(uid));
        return "user/profile";
    }

    //个人资料验证
    @RequestMapping(value = "{uid}/profile", method = RequestMethod.PUT)
    public String profileValidator(User record, Model model) {
        if (this.userService.updateUser(record))
            result = "成功修改个人资料";
        else result  = "未能修改个人资料";
        model.addAttribute("result",result);
        return "status/actionResult";
    }

    //前往邮箱及密码页面
    @RequestMapping(value = "{uid}/account", method = RequestMethod.GET)
    public String toAccount(@PathVariable("uid") String uid, Model model) {
        model.addAttribute(this.userService.getUserById(uid));
        return "user/account";
    }

    //邮箱及密码验证
    @RequestMapping(value = "{uid}/account", method = RequestMethod.PUT)
    public String accountValidator(User record,Model model) {
        if (this.userService.updateUser(record))
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
        if (this.userService.validatorEmail(record.getId(), record.getEmail()) && this.userService.updateUser(record))
            result = "成功重置密码";
        else result  = "未能重置密码";
        model.addAttribute("result",result);
        return "status/actionResult";
    }

    //退出登录
    @RequestMapping(value = "{uid}/logout")
    public String logout(HttpServletRequest request, Model model){
        HttpSession session =request.getSession();
        session.removeAttribute("userid");
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
