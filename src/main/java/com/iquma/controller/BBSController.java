package com.iquma.controller;

import com.iquma.pojo.Tag;
import com.iquma.pojo.Topic;
import com.iquma.service.TagService;
import com.iquma.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("")
public class BBSController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private TagService tagService;
    private static int HIGH_RATE_COUNT = 8;


    //前往教程列表页面--按页数
    @RequestMapping(value = "tutorials/{page}", method = RequestMethod.GET)
    public String toTutorialsByPage(@PathVariable int page, Topic topic, Model model) {
        topic.setSid(Byte.valueOf("1"));
        model.addAttribute("topics", topicService.selectsByConditionAndPage(page, topic));
        return "tutorials/tutorials_new";
    }

    //前往教程列表页面--按页数和类型
    @RequestMapping(value = "tutorials/{type}/{page}", method = RequestMethod.GET)
    public String toTutorialsByType(@PathVariable String type, @PathVariable int page, Topic topic, Model model) throws IllegalArgumentException {
        if ("hottest".equals(type)) topic.setRateCount(HIGH_RATE_COUNT);//按评分加载主贴
        else if ("unanswered".equals(type)) topic.setReplyCount(0);//按回答数加载主贴
        toTutorialsByPage(page,topic,model);
        return "tutorials/tutorials_" + type;
    }

    //前往教程列表页面--按页数和类型和分类
    @RequestMapping(value = "tutorials/{tname}/{type}/{page}", method = RequestMethod.GET)
    public String toTutorialsByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model) throws IllegalArgumentException {
        ta.setName(tname);
        topic.setTid(tagService.selectByCondition(ta).getId());
        toTutorialsByType(type, page, topic, model);
        return "tutorials/tutorials_" + type;
    }

    //前往提问列表页面
    @RequestMapping(value = "discusses/{title}", method = RequestMethod.GET)
    public String toDiscusses(@PathVariable String title, Tag ta, Topic topic, Model model) throws IllegalArgumentException {
        //首先根据传递的tag找出其对应的id，将其注入到topic中
        ta.setName(title);
        topic.setTid(tagService.selectByCondition(ta).getId());
        topic.setSid(Byte.parseByte("2"));
        topic.setTitle(null);
        model.addAttribute("topic", topic);
        return "discusses/discusses";
    }

    //前往经验列表页面
    @RequestMapping(value = "articles/{tid}", method = RequestMethod.GET)
    public String toArticles(@PathVariable Byte tid, Topic topic, Model model) throws IllegalArgumentException {
        topic.setSid(Byte.parseByte("3"));
        topic.setTid(tid);
        List articles = topicService.selectsByCondition(topic);
        if (articles.size() == 0) {
            return "status/emptyQuery";
        } else {
            model.addAttribute("articles", articles);
            return "articles/articles";
        }
    }

    //前往代码列表页面
    @RequestMapping(value = "codes/{tid}", method = RequestMethod.GET)
    public String toCodes(@PathVariable Byte tid, Topic topic, Model model) throws IllegalArgumentException {
        topic.setSid(Byte.parseByte("4"));
        topic.setTid(tid);
        List codes = topicService.selectsByCondition(topic);
        if (codes.size() == 0) {
            return "status/emptyQuery";
        } else {
            model.addAttribute("codes", codes);
            return "codes/codes";
        }
    }

    //前往提问页面
    @RequestMapping(value = "ask", method = RequestMethod.GET)
    public String toAsk() {
        return "ask/ask";
    }

    //提问验证页面
    @RequestMapping(value = "ask", method = RequestMethod.PUT)
    public
    @ResponseBody
    String askValidator(Topic record) {
        record.parseDefaultDiscuss();
        if (topicService.insert(record)) return "suc";
        else return "err";
    }


    //前往分享经验页面
    @RequestMapping(value = "write", method = RequestMethod.GET)
    public String toWrite() {
        return "ask/write";
    }

    //分享经验验证页面
    @RequestMapping(value = "write", method = RequestMethod.PUT)
    public
    @ResponseBody
    String writeValidator(Topic record) {
        record.parseDefaultArticle();
        if (topicService.insert(record)) return "suc";
        else return "err";
    }


    //前往分享代码页面
    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String toUpload() {
        return "ask/upload";
    }

    //分享代码验证
    @RequestMapping(value = "upload", method = RequestMethod.PUT)
    public
    @ResponseBody
    String uploadValidator(Topic record) {
        record.parseDefaultCode();
        if (topicService.insert(record)) return "suc";
        else return "err";
    }

}

