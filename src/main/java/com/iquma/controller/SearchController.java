package com.iquma.controller;

import com.iquma.pojo.Topic;
import com.iquma.service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


import javax.annotation.Resource;

/**
 * Created by Mo on 2016/10/1.
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Resource
    private TopicService topicService;

    //前往搜索页面
    @RequestMapping()
    public String toSearch(){
        return "search/topic";
    }

    //前往搜索话题页面
    @RequestMapping("topic")
    public String toSearchTopic(){
        return "search/searchTopic";
    }


    //前往搜索话题结果页面
    @RequestMapping("showTopic")
    public String toShowTopic(Topic topic, Model model){
        model.addAttribute("topic",this.topicService.selectTopicByCondition(topic));
        return "search/topic";
    }
}
