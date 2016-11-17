package com.iquma.controller;

import com.iquma.pojo.Reply;
import com.iquma.pojo.Topic;
import com.iquma.service.ReplyService;
import com.iquma.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Mo on 2016/10/31.
 */
@Controller
@RequestMapping("admin/topic")
public class topicManageController {

    @Autowired
    private TopicService topicService;
    @Autowired
    ReplyService replyService;
    String result;

    //默认为显示所有主贴页面
    @RequestMapping(method = RequestMethod.GET)
    public String toShowTopics(Model model){
        model.addAttribute("topics",topicService.selectAll());
        return "admin/topic/topics";
    }

    //前往显示主贴页面
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String toTopic(@PathVariable String id, Reply condition,Model model){
        condition.setTid(Integer.parseInt(id));
        model.addAttribute("topic",topicService.selectById(Integer.parseInt(id)));
        model.addAttribute("replies",replyService.selectByCondition(condition));
        return "admin/topic/topic";
    }

    //前往更新主贴页面
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String toUpdate(@PathVariable String id, Model model){
        model.addAttribute("topic",topicService.selectById(Integer.parseInt(id)));
        return "admin/topic/update";
    }

    //更新主贴验证
    @RequestMapping(value = "{id}/update", method = RequestMethod.PUT)
    public String updateTopic(Topic record, Model model) {
        if(topicService.update(record)) result = "成功更新主贴";
        else result = "未能更新主贴";
        model.addAttribute("result",result);
        return "admin/status/actionResult";
    }

    //关闭主贴验证
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public String blockTopic(Topic record, Model model) {
        if(topicService.changeStatus(record.getId())) result = "成功关闭主贴";
        else result = "未能关闭主贴";
        model.addAttribute("result",result);
        return "admin/status/actionResult";
    }

    //删除主贴验证
    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteTopic(Topic record){
        if(topicService.deleteById(record.getId())) return "suc";
        else return "err";
    }

    //关闭评论验证
    @ResponseBody
    @RequestMapping(value = "{tid}/reply/{id}", method = RequestMethod.POST)
    public String blockReply(@PathVariable Integer id){
        if(replyService.changeStatus(id)) return "suc";
        else return "err";
    }

    //删除评论验证
    @ResponseBody
    @RequestMapping(value = "{tid}/reply/{id}", method = RequestMethod.DELETE)
    public String deleteReply(@PathVariable Integer id){
        if(replyService.deleteById(id)) return "suc";
        else return "err";
    }



}
