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
@RequestMapping("article")
public class articleController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private ReplyService replyService;
    String result;

    //显示经验
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String toArticle(@PathVariable String id, Model model) {
        model.addAttribute("article",topicService.selectTopicById(Integer.parseInt(id)));
        model.addAttribute("replies",replyService.selectReplyByTid(Integer.parseInt(id)));
        return "articles/article";
    }

    //删除经验
    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteArticle(@PathVariable("id") String id) {
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

    //前往经验更新页面
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String toUpdateArticle(@PathVariable String id, Model model){
        model.addAttribute("article",topicService.selectTopicById(Integer.parseInt(id)));
        return "articles/update";
    }

    //更新经验验证
    @RequestMapping(value = "{id}/update", method = RequestMethod.PUT)
    public String updateValidator(Topic record, Model model) {
        if (topicService.updateTopic(record)) {
            result = "成功更新经验" + record.getId();
        } else {
            result = "未能更新经验" + record.getId();
        }
        model.addAttribute("result", result);
        return "status/actionResult";
    }

}
