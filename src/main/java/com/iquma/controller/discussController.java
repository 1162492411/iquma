package com.iquma.controller;

import com.iquma.pojo.Reply;
import com.iquma.pojo.Topic;
import com.iquma.service.ReplyService;
import com.iquma.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //显示提问
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String toDiscuss(@PathVariable String id, Reply condition, Model model) {
        condition.setTid(Integer.parseInt(id));
        Topic discuss = topicService.selectById(Integer.parseInt(id));
        if(discuss == null) return "status/emptyQuery";
        else{
            model.addAttribute("discuss",discuss);
            model.addAttribute("replies",replyService.selectByCondition(condition));
            return "discusses/discuss";
        }
    }

    //删除提问
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteDiscuss(@PathVariable("id") String id) {
        if (topicService.deleteById(Integer.parseInt(id))) {
            return "suc";
        } else {
            return "err";
        }
    }

    //发表评论
    @RequestMapping(value = "{id}/reply", method = RequestMethod.POST)
    public @ResponseBody String addReply(Reply record){
        record.parseDefaultReply();
        if(replyService.insert(record)) return "suc";
        else return "err";
    }

    //前往提问更新页面
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String toUpdateDiscuss(@PathVariable String id, Model model){
        model.addAttribute("discuss",topicService.selectById(Integer.parseInt(id)));
        return "discusses/update";
    }

    //更新提问验证
    @RequestMapping(value = "{id}/update", method = RequestMethod.PUT)
    public @ResponseBody String updateValidator(Topic record) {
        if (topicService.update(record)) return "suc";
        else return "err";
    }


}
