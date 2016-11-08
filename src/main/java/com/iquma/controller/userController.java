package com.iquma.controller;

import javax.annotation.Resource;
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


    //前往用户个人主页
    @RequestMapping(method = RequestMethod.GET, value = "/home/{uid}")
    public String toHome(@PathVariable("uid") String uid, Model model) {
        User user = this.userService.getUserById(uid);
        model.addAttribute("user", user);
        return "user/home";
    }


    //前往个人资料页面
    @RequestMapping(method = RequestMethod.GET, value = "profile/{uid}")
    public String toProfile(@PathVariable("uid") String uid, Model model) {
        model.addAttribute(this.userService.getUserById(uid));
        return "user/profile";
    }

    //个人资料验证
    @RequestMapping(method = RequestMethod.PUT, value = "profile/{uid}")
    public String profileValidator(User record) {
        if (this.userService.updateUser(record))
            return "success";
        else return "error";
    }

    //前往邮箱及密码页面。
    @RequestMapping(method = RequestMethod.GET, value = "account/{uid}")
    public String toAccount(@PathVariable("uid") String uid, Model model) {
        model.addAttribute(this.userService.getUserById(uid));
        return "user/account";
    }

    //邮箱及密码验证
    @RequestMapping(method = RequestMethod.PUT, value = "account/{uid}")
    public String accountValidator(User record) {
        if (this.userService.updateUser(record))
            return "success";
        else return "error";
    }

    //前往重置密码页面
    @RequestMapping(method = RequestMethod.GET, value = "forgot/{uid}")
    public String toForgot(@PathVariable("uid") String uid, Model model) {
        model.addAttribute("uid", uid);
        return "user/forgot";
    }

    //重置密码验证
    @RequestMapping(method = RequestMethod.PUT, value = "forgot")
    public String forgotValidator(User record) {
        if (this.userService.validatorEmail(record.getId(), record.getEmail()) && this.userService.updateUser(record))
            return "success";
        else return "error";
    }

    //前往用户通知页面，未完成
    @RequestMapping(method = RequestMethod.GET, value = "/notifications/{uid}")
    public String toNotifications(@PathVariable("uid") String uid, Model model) {
        model.addAttribute("uid", uid);
        return "user/notifications";
    }


}
