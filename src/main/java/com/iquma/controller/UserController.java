package com.iquma.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.iquma.pojo.User;
import com.iquma.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


//    //前往用户个人主页
    @RequestMapping("/home/{uid}")
    public String toHome(@PathVariable("uid") String uid, Model model) throws Exception {
        User user = this.userService.getUserById(uid);
//        String uname = user.getName();
//        String validatorNameUTF8 = new String(uname.getBytes(), "UTF-8");
//        String validatorNameGBK = new String(uname.getBytes(), "GBK");
//        if (uname.equals(validatorNameUTF8)) System.out.println("姓名的编码格式为utf8");
//        else if (uname.equals(validatorNameGBK)) System.out.println("姓名的编码格式为gbk");
        model.addAttribute("user", user);
        return "user/home";
    }

    //前往用户设置页面
    @RequestMapping("/settings")
    public String toSettings(@RequestParam("uid") String uid, Model model) {
        model.addAttribute("uid", uid);
        return "user/settings";
    }

    //前往用户设置-个人资料页面
    @RequestMapping("/settings/profile")
    public String toProfile(@RequestParam("uid") String uid, Model model) {
        model.addAttribute(this.userService.getUserById(uid));
        return "user/settings_profile";
    }

    //个人资料验证
    @RequestMapping("/settings/profile/validator")
    public String profileValidator(User user) {
        User record = new User(user.getId(), user.getName());
        if (this.userService.updateUser(record))
            return "success";
        else return "error";
    }

    //前往用户设置-邮箱及密码页面。
    @RequestMapping("/settings/account")
    public String toAccount(@RequestParam("uid") String uid, Model model) {
        model.addAttribute(this.userService.getUserById(uid));
        return "user/settings_account";
    }

    //邮箱及密码验证
    @RequestMapping("/settings/account/validator")
    public String accountValidator(User record) {
        if (this.userService.updateUser(record))
            return "success";
        else return "error";
    }

    //前往用户设置-重置密码页面
    @RequestMapping("/settings/forgot")
    public String toForgot(@RequestParam("uid") String uid, Model model) {
        model.addAttribute("uid", uid);
        return "user/settings_forgot";
    }

    //重置密码验证
    @RequestMapping("/settings/forgot/validator")
    public String forgotValidator(User record) {
        if (this.userService.validatorEmail(record.getId(), record.getEmail()) && this.userService.updateUser(record))
            return "success";
        else return "error";
    }

    //前往用户通知页面，未完成
    @RequestMapping("/notifications")
    public String toNotifications(@RequestParam("uid") String uid, Model model) {
        model.addAttribute("uid", uid);
        return "user/notifications";
    }


}
