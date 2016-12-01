package com.iquma.controller;

import com.iquma.pojo.Topic;
import com.iquma.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * Created by Mo on 2016/10/31.
 */
@Controller
@RequestMapping("")
public class bbsController {

    @Autowired
    private TopicService topicService;


    //前往教程列表页面
    @RequestMapping(value = "tutorials/{tid}", method = RequestMethod.GET)
    public String toTutorials(@PathVariable Byte tid, Topic topic, Model model) throws IllegalArgumentException{
        topic.setSid(Byte.parseByte("1"));
        topic.setTid(tid);
        List tutorials =  topicService.selectByCondition(topic);
        if(tutorials.size() == 0){
            return "status/emptyQuery";
        }
        else{
            model.addAttribute("tutorials",tutorials);
            return "tutorials/tutorials";
        }
    }

    //前往提问列表页面
    @RequestMapping(value = "discusses/{tid}", method = RequestMethod.GET)
    public String toDiscusses(@PathVariable Byte tid, Topic topic, Model model)throws IllegalArgumentException{
        topic.setSid(Byte.parseByte("2"));
        topic.setTid(tid);
        List discusses = topicService.selectByCondition(topic);
        if(discusses.size() == 0){
            return "status/emptyQuery";
        }
        else{
            model.addAttribute("discusses",discusses);
            return "discusses/discusses";
        }
    }

    //前往经验列表页面
    @RequestMapping(value = "articles/{tid}", method = RequestMethod.GET)
    public String toArticles(@PathVariable Byte tid, Topic topic, Model model)throws IllegalArgumentException{
        topic.setSid(Byte.parseByte("3"));
        topic.setTid(tid);
        List articles = topicService.selectByCondition(topic);
        if(articles.size() == 0){
            return "status/emptyQuery";
        }
        else{
            model.addAttribute("articles",articles);
            return "articles/articles";
        }
    }

    //前往代码列表页面
    @RequestMapping(value = "codes/{tid}", method = RequestMethod.GET)
    public String toCodes(@PathVariable Byte tid, Topic topic, Model model)throws IllegalArgumentException{
        topic.setSid(Byte.parseByte("4"));
        topic.setTid(tid);
        List codes = topicService.selectByCondition(topic);
        if(codes.size() == 0){
            return "status/emptyQuery";
        }
        else{
            model.addAttribute("codes",codes);
            return "codes/codes";
        }
    }

    //前往提问页面
    @RequestMapping(value = "ask", method = RequestMethod.GET)
    public String toAsk(){
        return "ask/ask";
    }

    //提问验证页面
    @RequestMapping(value = "ask", method = RequestMethod.PUT)
    public @ResponseBody String askValidator(Topic record){
        record.parseDefaultDiscuss();
        if(topicService.insert(record)) return "suc";
        else return "err";
    }


    //前往分享经验页面
    @RequestMapping(value = "write", method = RequestMethod.GET)
    public String toWrite(){
        return "ask/write";
    }

    //分享经验验证页面
    @RequestMapping(value = "write", method = RequestMethod.PUT)
    public @ResponseBody String writeValidator(Topic record){
        record.parseDefaultArticle();
        if(topicService.insert(record)) return "suc";
        else return "err";
    }


    //前往分享代码页面
    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String toUpload(){
        return "ask/upload";
    }

    //分享代码验证
    @RequestMapping(value = "upload", method = RequestMethod.PUT)
    public  @ResponseBody String uploadValidator(Topic record){
        record.parseDefaultCode();
        if(topicService.insert(record)) return "suc";
        else return "err";
    }


}
