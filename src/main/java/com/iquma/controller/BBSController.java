package com.iquma.controller;

import com.iquma.pojo.Tag;
import com.iquma.pojo.Topic;
import com.iquma.service.TagService;
import com.iquma.service.TopicService;
import com.iquma.utils.ENUMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("")
public class BBSController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private TagService tagService;
    private static final byte HIGH_RATE_COUNT = 8;



    //前往教程列表页面--按页数
    @RequestMapping(value = "tutorials/{page}", method = RequestMethod.GET)
    public String toTutorialsByPage(@PathVariable int page,Tag tag, Topic topic, Model model) {
        return toTopicsByCondition(null,null,page,tag,topic,model, ENUMS.SECTION_TUTORIAL);
    }

    //前往教程列表页面--按页数或类型
    @RequestMapping(value = "tutorials/{type}/{page}", method = RequestMethod.GET)
    public String toTutorialsByType(@PathVariable String type, @PathVariable int page,Tag tag, Topic topic, Model model) throws IllegalArgumentException {
        //如果是按类型获取主贴
        if("hottest".equals(type) || "unanswered".equals(type) || "new".equals(type))
            return toTopicsByCondition(null,type,page,tag,topic,model,ENUMS.SECTION_TUTORIAL);
        else//如果是按标签获取主贴
            return toTopicsByCondition(type,null,page,tag,topic,model,ENUMS.SECTION_TUTORIAL);
    }

    //前往教程列表页面--按页数和类型和分类
    @RequestMapping(value = "tutorials/{tname}/{type}/{page}", method = RequestMethod.GET)
    public String toTutorialsByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model) throws IllegalArgumentException {
        return toTopicsByCondition(tname,type,page,ta,topic,model,ENUMS.SECTION_TUTORIAL);
    }

    //前往提问列表页面--按页数
    @RequestMapping(value = "discusses/{page}", method = RequestMethod.GET)
    public String toDiscussesByPage(@PathVariable int page,Tag tag, Topic topic, Model model) {
        return toTopicsByCondition(null,null,page,tag,topic,model,ENUMS.SECTION_DISCUSS);
    }

    //前往提问列表页面--按页数和类型
    @RequestMapping(value = "discusses/{type}/{page}", method = RequestMethod.GET)
    public String toDiscussesByType(@PathVariable String type, @PathVariable int page,Tag tag, Topic topic, Model model) throws IllegalArgumentException {
        //如果是按类型获取主贴
        if("hottest".equals(type) || "unanswered".equals(type) || "new".equals(type))
            return toTopicsByCondition(null,type,page,tag,topic,model,ENUMS.SECTION_DISCUSS);
        else//如果是按标签获取主贴
            return toTopicsByCondition(type,null,page,tag,topic,model,ENUMS.SECTION_DISCUSS);
    }

    //前往提问列表页面--按页数和类型和分类
    @RequestMapping(value = "discusses/{tname}/{type}/{page}", method = RequestMethod.GET)
    public String toDiscussesByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model) throws IllegalArgumentException {
        return toTopicsByCondition(tname,type,page,ta,topic,model,ENUMS.SECTION_DISCUSS);
    }

    //前往经验列表页面--按页数
    @RequestMapping(value = "articles/{page}", method = RequestMethod.GET)
    public String toArticlesByPage(@PathVariable int page,Tag tag, Topic topic, Model model) {
        return toTopicsByCondition(null,null,page,tag,topic,model,ENUMS.SECTION_ARTICLE);
    }

    //前往经验列表页面--按页数和类型
    @RequestMapping(value = "articles/{type}/{page}", method = RequestMethod.GET)
    public String toArticlesByType(@PathVariable String type, @PathVariable int page,Tag tag, Topic topic, Model model) throws IllegalArgumentException {
        //如果是按类型获取主贴
        if("hottest".equals(type) || "unanswered".equals(type) || "new".equals(type))
            return toTopicsByCondition(null,type,page,tag,topic,model,ENUMS.SECTION_ARTICLE);
        else//如果是按标签获取主贴
            return toTopicsByCondition(type,null,page,tag,topic,model,ENUMS.SECTION_ARTICLE);
    }

    //前往经验列表页面--按页数和类型和分类
    @RequestMapping(value = "articles/{tname}/{type}/{page}", method = RequestMethod.GET)
    public String toArticlesByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model) throws IllegalArgumentException {
        return toTopicsByCondition(tname,type,page,ta,topic,model,ENUMS.SECTION_ARTICLE);
    }

    //前往代码列表页面--按页数
    @RequestMapping(value = "codes/{page}", method = RequestMethod.GET)
    public String toCodesByPage(@PathVariable int page,Tag tag, Topic topic, Model model) {
        return toTopicsByCondition(null,null,page,tag,topic,model,ENUMS.SECTION_CODE);
    }

    //前往代码列表页面--按页数和类型
    @RequestMapping(value = "codes/{type}/{page}", method = RequestMethod.GET)
    public String toCodesByType(@PathVariable String type, @PathVariable int page,Tag tag, Topic topic, Model model) throws IllegalArgumentException {
        //如果是按类型获取主贴
        if("hottest".equals(type) || "unanswered".equals(type) || "new".equals(type))
            return toTopicsByCondition(null,type,page,tag,topic,model,ENUMS.SECTION_CODE);
        else//如果是按标签获取主贴
            return toTopicsByCondition(type,null,page,tag,topic,model,ENUMS.SECTION_CODE);
    }

    //前往代码列表页面--按页数和类型和分类
    @RequestMapping(value = "codes/{tname}/{type}/{page}", method = RequestMethod.GET)
    public String toCodesByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model) throws IllegalArgumentException {
        return toTopicsByCondition(tname,type,page,ta,topic,model,ENUMS.SECTION_CODE);
    }
    

    //前往主贴列表页面--按页数和类型和分类--通用
    private String toTopicsByCondition(String tname, String type,Integer page, Tag ta, Topic topic, Model model,String section){
        if(null != tname) {
            ta.setName(tname);//设置主贴类别
            topic.setTid(tagService.selectByCondition(ta).getId());
        }
        if ("hottest".equals(type)) topic.setRateCount(HIGH_RATE_COUNT);//按评分加载主贴
        else if ("unanswered".equals(type)) topic.setReplyCount(0);//按回答数加载主贴
        topic.setSection(section);//设置版块
        List results = topicService.selectsByConditionAndPage(page, topic);//获取查询结果
        if(results.size() == 0) model.addAttribute("searchEmpty",Boolean.TRUE);//查询结果为空时返回空查询页面
        else model.addAttribute("topics", results );//查询结果存在时将结果绑定到model中
        model.addAttribute("type", type);//将查询的类型绑定到model中
        model.addAttribute("totalPage",getTotalPageByTopic(topic));//将符合条件的主贴的总页数绑定到model中
        model.addAttribute("currentPage",page);//将当前页码绑定到model中
        return "topics/topics";
    }


    //获取符合条件的主贴的总页数
    private int getTotalPageByTopic(Topic condition){
        int total = topicService.selectsByCondition(condition).size();
        return total % 10 == 0 ? total / 10 : total / 10 + 1;
    }


    //前往提问页面
    @RequestMapping(value = "ask", method = RequestMethod.GET)
    public String toAsk() {
        return "editor/ask";
    }

    //提问验证页面
    @RequestMapping(value = "ask", method = RequestMethod.PUT)
    public @ResponseBody Boolean ask(@RequestBody Topic record) {
        record.parseDefaultDiscuss();
        return topicService.insert(record);
    }


    //前往分享经验页面
    @RequestMapping(value = "write", method = RequestMethod.GET)
    public String toWrite() {
        return "editor/write";
    }

    //分享经验验证页面
    @RequestMapping(value = "write", method = RequestMethod.PUT)
    public @ResponseBody Boolean write(@RequestBody  Topic record) {
        record.parseDefaultArticle();
        return topicService.insert(record);
    }


    //前往分享代码页面
    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String toUpload() {
        return "editor/upload";
    }

    //分享代码验证
    @RequestMapping(value = "upload", method = RequestMethod.PUT)
    public @ResponseBody Boolean upload(@RequestBody Topic record) {
        record.parseDefaultCode();
        return topicService.insert(record);
    }

}

