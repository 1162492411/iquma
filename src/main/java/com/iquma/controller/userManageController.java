package com.iquma.controller;

import com.iquma.pojo.User;
import com.iquma.pojo.Block;
import com.iquma.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import javax.annotation.Resource;


@Controller
@RequestMapping("admin/users")
public class userManageController {

    @Resource
    private UserService userService;
    private String result;

    //默认为显示所有用户页面
    @RequestMapping(method = RequestMethod.GET)
    public String toShowUsers(@RequestParam("uid") String uid, Model model) {
        model.addAttribute("uid", uid);
        model.addAttribute("userMap", this.userService.getAllUsers());
        return "admin/users/users";
    }

    //前往添加用户页面
    @RequestMapping("add")
    public void add(){  }

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
    public void toBlock(@RequestParam("uid") String uid, Model model) {
        model.addAttribute("uid", uid);
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
    @RequestMapping("update/{uid}")
    public String toUpdate(@PathVariable("uid")String uid, Model model) {
        model.addAttribute(this.userService.getUserById(uid));
        return "admin/users/update";
    }

    //更新账户验证
    @RequestMapping(method = RequestMethod.PUT, value = "update")
    public String updateValidator(User record, Model model) {
        System.out.println("传输的账户信息为"+ record);
        if (this.userService.updateUser(record)) {
            result = "成功更新账户" + record.getId();
        } else {
            result = "未能更新账户" + record.getId();
        }
        model.addAttribute("result", result);
        return "admin/status/actionResult";
    }


    //删除用户
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public String deleteUser(@PathVariable("id") String id) {
        if (this.userService.delete(id)) {
            return "suc";
        } else {
            return "err";
        }
    }

}
