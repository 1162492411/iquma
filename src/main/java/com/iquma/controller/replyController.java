package com.iquma.controller;

import com.iquma.pojo.Reply;
import com.iquma.service.ReplyService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Mo on 2017/1/27.
 */
@Controller
@RequestMapping("reply")
public class replyController {

    @Autowired
    private ReplyService replyService;

    //发表评论
    @RequiresUser
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Boolean insert(@RequestBody Reply record){
        System.out.println("发表评论时收到参数" + record);
        return replyService.insert(record);
    }

    //删除评论
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Boolean delete(@RequestBody Integer id){
        return replyService.deleteById(id);
    }

    //关闭评论
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Boolean changeStatus(@RequestBody Integer id){
        return replyService.changeStatus(id);
    }

    //采纳评论
    @RequestMapping(value = "adopt", method = RequestMethod.POST)
    public @ResponseBody Boolean adopt(@RequestBody Integer id){
        return replyService.adopt(id);
    }

}
