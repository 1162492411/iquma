package com.iquma.controller;

import com.iquma.pojo.Topic;
import com.iquma.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mo on 2016/10/31.
 */
@Controller
@RequestMapping("")
public class bbsController {

    @Autowired
    private TopicService topicService;
    String result;

    //前往教程列表页面
    @RequestMapping(value = "tutorials/{tid}", method = RequestMethod.GET)
    public String toTutorials(@PathVariable String tid, Topic topic, Model model){
        topic.setSid(Byte.parseByte("1"));
        topic.setTid(Byte.parseByte(tid));
        model.addAttribute("tutorials",topicService.selectByCondition(topic));
        return "tutorials/tutorials";
    }

    //前往提问列表页面
    @RequestMapping(value = "discusses/{tid}", method = RequestMethod.GET)
    public String toDiscusses(@PathVariable String tid, Topic topic, Model model){
        topic.setSid(Byte.parseByte("2"));
        topic.setTid(Byte.parseByte(tid));
        model.addAttribute("discusses",topicService.selectByCondition(topic));
        return "discusses/discusses";
    }

    //前往经验列表页面
    @RequestMapping(value = "articles/{tid}", method = RequestMethod.GET)
    public String toArticles(@PathVariable String tid, Topic topic, Model model){
        topic.setSid(Byte.parseByte("3"));
        topic.setTid(Byte.parseByte(tid));
        model.addAttribute("articles",topicService.selectByCondition(topic));
        return "articles/articles";
    }

    //前往代码列表页面
    @RequestMapping(value = "codes/{tid}", method = RequestMethod.GET)
    public String toCodes(@PathVariable String tid, Topic topic, Model model){
        topic.setSid(Byte.parseByte("4"));
        topic.setTid(Byte.parseByte(tid));
        model.addAttribute("codes",topicService.selectByCondition(topic));
        return "codes/codes";
    }

    //前往提问页面
    @RequestMapping(value = "ask", method = RequestMethod.GET)
    public String toAsk(){
        return "ask/ask";
    }

    //提问验证页面
    @RequestMapping(value = "ask", method = RequestMethod.PUT)
    public String askValidator(Topic record, Model model){
        record.parseDefaultDiscuss();
        if(topicService.insert(record)) result = "成功发表提问";
        else result = "未能成功发表提问";
        model.addAttribute("result", result);
        return "status/actionResult";
    }


    //前往分享经验页面
    @RequestMapping(value = "write", method = RequestMethod.GET)
    public String toWrite(){
        return "ask/write";
    }

    //分享经验验证页面
    @RequestMapping(value = "write", method = RequestMethod.PUT)
    public String writeValidator(Topic record, Model model){
        record.parseDefaultArticle();
        if(topicService.insert(record)) result = "成功分享经验";
        else result = "未能成功分享经验";
        model.addAttribute("result", result);
        return "status/actionResult";
    }


    //前往分享代码页面
    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String toUpload(){
        return "ask/upload";
    }

    //分享代码验证
    @RequestMapping(value = "upload", method = RequestMethod.PUT)
    public String uploadValidator(Topic record, Model model){
        record.parseDefaultCode();
        if(topicService.insert(record)) result = "成功分享代码";
        else result = "未能成功分享代码";
        model.addAttribute("result", result);
        return "status/actionResult";
    }


}
