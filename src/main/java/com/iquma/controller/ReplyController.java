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
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    //发表评论
    @RequiresUser
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Boolean insert(@RequestBody Reply record){
        return replyService.insert(record);
    }

    //删除评论
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Boolean delete(@RequestBody Reply record){
        return replyService.deleteById(record.getId());
    }

    //关闭评论
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Boolean block(@RequestBody Reply record){
        return replyService.changeStatus(record.getId());
    }

    //采纳评论
    @RequestMapping(value = "adopt", method = RequestMethod.POST)
    public @ResponseBody Boolean adopt(@RequestBody Reply record){
        return replyService.adopt(record.getId());
    }

}
