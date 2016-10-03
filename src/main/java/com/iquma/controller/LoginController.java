package com.iquma.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.iquma.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    //跳转到前台登录页面
    @RequestMapping()
    public String toLogin() {
        return "login";
    }

    //验证前台登陆
    @RequestMapping("/validator")
    public String loginValidator(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String pass = request.getParameter("password");
        if (this.userService.validatorUserPass(id, pass)) {
            model.addAttribute("uid", id);
            return "success";
        } else
            return "error";
    }

    //后台登录页面
    @RequestMapping("/admin")
    public String toAdminlogin() {
        return "adminlogin";
    }

    //验证后台登录
    @RequestMapping("/admin/validator")
    public String adminLoginValidator(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String pass = request.getParameter("password");
        if (this.userService.validatorUserPass(id, pass) && this.userService.isAdmin(id))
            return "/admin/status/loginSuccess";
        else return "admin/status/loginError";
    }
}
