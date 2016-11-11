package com.iquma.controller;

import com.iquma.pojo.CommentList;
import com.iquma.pojo.Reply;
import com.iquma.pojo.Topic;
import com.iquma.service.CommentService;
import com.iquma.service.ReplyService;
import com.iquma.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Mo on 2016/10/3.
 */
@Controller
@RequestMapping("discuss")
public class discussController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private ReplyService replyService;
    String result;

    //显示提问
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String toDiscuss(@PathVariable String id, Model model) {
        model.addAttribute("discuss",topicService.selectTopicById(Integer.parseInt(id)));
        model.addAttribute("replies",replyService.selectReplyByTid(Integer.parseInt(id)));
        return "discusses/discuss";
    }

    //删除提问
    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteDiscuss(@PathVariable("id") String id) {
        if (topicService.deleteTopicById(Integer.parseInt(id))) {
            return "suc";
        } else {
            return "err";
        }
    }

    //发表评论
    @RequestMapping(value = "{id}/reply", method = RequestMethod.POST)
    public String addReply(Reply record, Model model){
        record.parseDefaultReply();
        if(replyService.insert(record)) result = "成功评论";
        else result = "未能成功评论";
        model.addAttribute("result",result);
        return "status/actionResult";
    }

    //前往提问更新页面
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String toUpdateDiscuss(@PathVariable String id, Model model){
        model.addAttribute("discuss",topicService.selectTopicById(Integer.parseInt(id)));
        return "discusses/update";
    }

    //更新提问验证
    @RequestMapping(value = "{id}/update", method = RequestMethod.PUT)
    public String updateValidator(Topic record, Model model) {
        if (topicService.updateTopic(record)) {
            result = "成功更新提问" + record.getId();
        } else {
            result = "未能更新提问" + record.getId();
        }
        model.addAttribute("result", result);
        return "status/actionResult";
    }


}
