package com.iquma.controller;

import com.iquma.pojo.User;
import com.iquma.pojo.Block;
import com.iquma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import javax.annotation.Resource;
import javax.ws.rs.Path;


@Controller
@RequestMapping("admin/user")
public class userManageController {

    @Autowired
    private UserService userService;
    private String result;

    //默认为显示所有用户页面
    @RequestMapping(method = RequestMethod.GET)
    public String toShowUsers(@RequestParam("uid") String uid, Model model) {
        model.addAttribute("uid", uid);
        model.addAttribute("users", this.userService.selectAll());
        return "admin/user/users";
    }

    //前往添加用户页面
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String toAddUser(){
        return "admin/user/add";
    }

    //前往用户页面
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String toUser(@PathVariable String id, Model model){
        model.addAttribute("user",userService.selectById(id));
        return "admin/user/user";
    }

    //添加用户验证
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(User user, Model model) {
        user.parseDefaultAddUser();
        if (this.userService.insert(user)) result = new String("成功添加用户" + user.getId());
        else result = new String("未能成功添加用户" + user.getId());
        model.addAttribute("result", result);
        return "admin/status/actionResult";
    }

    //前往更新用户页面
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String toupdate(@PathVariable String id, Model model){
        model.addAttribute("user",userService.selectById(id));
        return "admin/user/update";
    }

    //更新账户验证
    @RequestMapping(value = "{id}/update", method = RequestMethod.PUT)
    public String update(User record, Model model) {
        if (this.userService.update(record)) result = "成功更新账户" + record.getId();
        else result = "未能更新账户" + record.getId();
        model.addAttribute("result", result);
        return "admin/status/actionResult";
    }

    //封禁用户验证
    @RequestMapping(value = "{uid}", method = RequestMethod.POST)
    public String block(Block block, Model model) {
        if (this.userService.changeStatus(block.getUid())) result = "成功封禁账户" + block.getUid();
        else result = "未能封禁账户" + block.getUid();
        model.addAttribute("result", result);
        return "admin/status/actionResult";
    }


    //删除用户验证
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteUser(@PathVariable("id") String id) {
        if (this.userService.delete(id)) {
            return "suc";
        } else {
            return "err";
        }
    }

}
