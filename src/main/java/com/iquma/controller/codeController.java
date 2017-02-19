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
    //显示提问
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String toCode(@PathVariable String id, Reply condition, Model model) {
        condition.setTid(Integer.parseInt(id));
        Topic code = topicService.selectById(Integer.parseInt(id));
        if(code == null) return "status/emptyQuery";
        else{
            model.addAttribute("code",code);
            model.addAttribute("replies",replyService.selectByCondition(condition));
            return "codes/code";
        }
    }

    //删除提问
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteCode(@PathVariable("id") String id) {
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
    public String toUpdateCode(@PathVariable String id, Model model){
        model.addAttribute("code",topicService.selectById(Integer.parseInt(id)));
        return "codes/update";
    }

    //更新提问验证
    @RequestMapping(value = "{id}/update", method = RequestMethod.PUT)
    public @ResponseBody String updateValidator(Topic record) {
        if (topicService.update(record)) return "suc";
        else return "err";
    }
}
