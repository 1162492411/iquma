package com.iquma.controller;

import com.iquma.pojo.Favorite;
import com.iquma.pojo.Tag;
import com.iquma.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api")
public class apiController {

    @Autowired
    private SectionService sectionService;
    @Autowired
    private TagService tagService;
    @Autowired
    private RoleService roleService;
//    @Autowired
//    private TopicService topicService;
//    @Autowired
//    private ReplyService replyService;
    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping("getAllSections")
    public @ResponseBody
    List getAllSections(){
        return sectionService.selectAll();
    }

    @RequestMapping("getFirstTags")
    public @ResponseBody List getFirstTags(Tag tag){
        tag.setPid(Byte.valueOf("0"));
        return tagService.selectTagsByCondition(tag);
    }

    @RequestMapping("getTagsByPid/{pid}")
    public @ResponseBody List getTags(@PathVariable String pid,Tag condition){
        condition.setPid(Byte.parseByte(pid));
        return tagService.selectTagsByCondition(condition);
    }

    @RequestMapping("getAllRoles")
    public @ResponseBody List getAllRoles(){
        return roleService.selectAll();
    }


//    //获取符合条件的主贴总页数
//    @RequestMapping(value = "getTopicCountByTag/{tname}")
//    public @ResponseBody int getTopicCountByCondition(@PathVariable String tname,Tag tag, Topic topic){
//        tag.setName(tname);
//        topic.setTid(tagService.selectByCondition(tag).getId());
//        topic.setSid(Byte.parseByte("1"));
//        int x = topicService.selectByCondition(topic).size();
//        System.out.println("查询总页数时查询到总数量" + x);
//        return x % 10 == 0 ? x / 10 : x / 10 + 1;
//    }

//    //分页获取符合条件的主贴
//    @RequestMapping(value =  "getTopicByConditionAndPage/{page}", method = RequestMethod.POST)
//    public @ResponseBody List getTopicByConditionAndPage(@PathVariable int page, @RequestBody Topic con){
//        return this.topicService.selectByConditionAndPage(page,con);
//    }

//    //获取符合条件的回复总页数
//    @RequestMapping(value = "getReplyCountByCondition", method = RequestMethod.POST)
//    public @ResponseBody int getReplyCountByCondition(@RequestBody Reply con){
//        int x = replyService.selectByCondition(con).size();
//        return x % 5 == 0 ? x / 5 : x / 5 + 1;
//    }
//
//    //分页获取符合条件的回复--未完成
//    @RequestMapping(value =  "getReplyByConditionAndPage/{page}", method = RequestMethod.POST)
//    public @ResponseBody List getReplyByConditionAndPage(@PathVariable int page, @RequestBody Reply con){
//        return this.replyService.selectByConditionAndPage(page,con);
//    }

    //检测主贴是否被用户收藏--未完成
    @RequestMapping(value = "getIsFavorite" , method = RequestMethod.POST)
    public @ResponseBody Boolean checkIsFavorite(@RequestBody Favorite con){
        System.out.println("检测主贴是否被用户收藏时收到参数:" + con);
        return null != this.favoriteService.selectByCondition(con);
    }

}


