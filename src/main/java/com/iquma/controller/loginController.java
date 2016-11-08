package com.iquma.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.iquma.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class loginController {

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
        System.out.println("登录验证时获取了信息:" + id + "  " + pass);
        if (this.userService.validatorUserPass(id, pass)) {
            model.addAttribute("uid", id);
            System.out.println("成功验证");
            HttpSession session = request.getSession();
            session.setAttribute("userid",id);
            return "success";
        } else {
            System.out.println("验证失败");
            return "error";
        }
    }

    //后台登录页面
    @RequestMapping("/admin")
    public String toAdminLogin() {
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
