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
@RequestMapping("code")
public class codeController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private ReplyService replyService;
    String result;

    //显示代码
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String toCode(@PathVariable String id, Reply condition, Model model) {
        condition.setTid(Integer.parseInt(id));
        model.addAttribute("code",topicService.selectById(Integer.parseInt(id)));
        model.addAttribute("replies",replyService.selectByCondition(condition));
        return "codes/code";
    }

    //删除代码
    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteCode(@PathVariable("id") String id) {
        if (topicService.deleteById(Integer.parseInt(id))) {
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

    //前往代码更新页面
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String toUpdateCode(@PathVariable String id, Model model){
        model.addAttribute("code",topicService.selectById(Integer.parseInt(id)));
        return "codes/update";
    }

    //更新代码验证
    @RequestMapping(value = "{id}/update", method = RequestMethod.PUT)
    public String updateValidator(Topic record, Model model) {
        if (topicService.update(record)) {
            result = "成功更新代码" + record.getId();
        } else {
            result = "未能更新代码" + record.getId();
        }
        model.addAttribute("result", result);
        return "status/actionResult";
    }

}
