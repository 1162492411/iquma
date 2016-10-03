package com.iquma.controller;

import com.iquma.pojo.User;
import com.iquma.pojo.Block;
import com.iquma.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import javax.annotation.Resource;


@Controller
@RequestMapping("admin/users")
public class UserManageController {

    @Resource
    private UserService userService;
    private String result;

    //默认为显示所有用户页面
    @RequestMapping()
    public String toShowUsers(@RequestParam("uid") String uid, Model model) {
        model.addAttribute("uid", uid);
        model.addAttribute("userMap", this.userService.getAllUsers());
        return "admin/users/showUsers";
    }

    //前往添加用户页面
    @RequestMapping("/add")
    public String toAddUser() {
        return "admin/users/addUser";
    }

    //添加用户验证
    @RequestMapping("/add/validator")
    public String addUserValidator(User user, Model model) {
        user.setPass(user.getId().substring(user.getId().length() - 6, user.getId().length()));
        if (this.userService.insert(user))
            result = new String("成功添加用户" + user.getId());
        else
            result = new String("未能成功添加用户" + user.getId());
        model.addAttribute("result", result);
        return "admin/status/actionResult";
    }

    //前往封禁用户页面
    @RequestMapping("/block")
    public String toBlock(@RequestParam("uid") String uid, Model model) {
        model.addAttribute("uid", uid);
        return "admin/users/blockUser";
    }

    //封禁用户验证
    @RequestMapping("block/validator")
    public String blockValidator(Block block, Model model) {
        if (this.userService.blockUser(block.getUid())) {
            result = "成功封禁账户" + block.getUid();
        } else {
            result = "未能封禁账户" + block.getUid();
        }
        model.addAttribute("result", result);
        return "admin/status/actionResult";
    }

    //前往更新账户页面
    @RequestMapping("update")
    public String toUpdate(@RequestParam("uid") String uid, Model model) {
        model.addAttribute(this.userService.getUserById(uid));
        return "admin/users/updateUser";
    }

    //更新账户验证
    @RequestMapping("update/validator")
    public String updateValidator(User user, Model model) {
        if (this.userService.updateUser(user)) {
            result = "成功更新账户" + user.getId();
        } else {
            result = "未能更新账户" + user.getId();
        }
        model.addAttribute("result", result);
        return "admin/status/actionResult";
    }
}
