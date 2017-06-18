package com.iquma.controller;

import com.iquma.pojo.Reply;
import com.iquma.service.ReplyService;
import com.iquma.utils.BinomialUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Mo on 2017/1/27.
 */
@Controller
@RequestMapping("reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private BinomialUtil binomialUtil;

    //发表回复
    @RequiresUser
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Boolean insert(@RequestBody Reply record){
        System.out.println("回复控制器接收到参数:" + record);
        return replyService.insert(record);
    }

    //删除回复
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Boolean delete(@RequestBody Reply record){
        return replyService.deleteById(record.getId());
    }

    //关闭回复
    @RequestMapping(value = "block", method = RequestMethod.POST)
    public @ResponseBody Boolean block(@RequestBody Reply record){
        return replyService.changeStatus(record.getId());
    }

    //采纳回复
    @RequestMapping(value = "adopt", method = RequestMethod.POST)
    public @ResponseBody Boolean adopt(@RequestBody Reply record){
        return replyService.adopt(record.getId());
    }

    //赞同回复
    @RequiresPermissions("reply:like")
    @RequestMapping( value = "like" , method = RequestMethod.POST)
    public @ResponseBody Boolean like(@RequestBody Reply record){
        record = replyService.selectById(record.getId());
        double likeCount = binomialUtil.getLikeCount(record.getLikeCount());
        double hateCount = record.getHateCount();
        return replyService.rate(new Reply(record.getId(),likeCount,hateCount,binomialUtil.getRateCount(likeCount,hateCount)));
    }

    //反对回复
    @RequestMapping( value = "hate" , method = RequestMethod.POST)
    public @ResponseBody Boolean hate(@RequestBody Reply record){
        if(SecurityUtils.getSubject().isPermitted("reply:hate:" + record.getTid())){
            record = replyService.selectById(record.getId());
            double likeCount = record.getLikeCount();
            double hateCount = binomialUtil.getHateCount(record.getHateCount());
            return replyService.rate(new Reply(record.getId(),likeCount,hateCount,binomialUtil.getRateCount(likeCount,hateCount)));
        }
        else throw new ShiroException();
    }
}
