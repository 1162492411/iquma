package com.iquma.controller;

import com.iquma.exception.NoSuchSectionException;
import com.iquma.pojo.*;
import com.iquma.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Mo on 2016/10/30.
 */
@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private FavoriteService favoriteService;

    //显示主贴
    @RequestMapping(value = {"tutorial/{tid}","discuss/{tid}","article/{tid}","code/{tid}"})
    public String toTopic(@PathVariable Integer tid, Reply condition, Model model) throws NoSuchSectionException {
        condition.setTid(tid);//查询主贴是否存在
        Topic topic = topicService.selectById(tid);
        if(topic == null) return "status/emptyQuery";//主贴不存在则跳转空查询页面
        else{//主贴存在时查找主贴回复并绑定相关数据
            model.addAttribute("topic",topic);
            model.addAttribute("replies",replyService.selectByCondition(condition));
            return "topics/topic_default";
        }
    }

    //显示主贴，并按时间显示回复
    @RequestMapping(value = {"tutorial/{tid}/{sort}","discuss/{tid}/{sort}","article/{tid}/{sort}","code/{tid}/{sort}"}, method = RequestMethod.GET)
    public String toTopicSortByTime(@PathVariable Integer tid, @PathVariable String sort, Reply condition, Model model) {
        condition.setTid(tid);
        Topic topic = topicService.selectById(tid);
        if("time".equals(sort)){
            model.addAttribute("topic",topic);
            model.addAttribute("replies",replyService.selectByConditionSortByTime(condition));
            return "topics/topic_time";
        }
        else
            return "status/emptyQuery";
    }


    //删除主贴
    @RequestMapping(value = {"tutorial/{id}","discuss/{id}","article/{id}","code/{id}"}, method = RequestMethod.DELETE)
    public @ResponseBody Boolean delete(@RequestBody Topic record) {
        return topicService.deleteById(record.getId());
    }


    //前往主贴更新页面
    @RequestMapping(value = {"tutorial/{id}/update","discuss/{id}/update","article/{id}/update","code/{id}/update"}, method = RequestMethod.GET)
    public String toUpdateTopic(@PathVariable Integer id, Model model){
        Topic topic = topicService.selectById(id);
        if(topic == null) return "status/emptyQuery";//主贴不存在则跳转空查询页面
        model.addAttribute("topic",topic);
        return "topics/update";
    }

    //更新主贴验证
    @RequestMapping(value = {"tutorial/{id}/update","discuss/{id}/update","article/{id}/update","code/{id}/update"}, method = RequestMethod.PUT)
    public @ResponseBody Boolean update(@RequestBody Topic record){
         return topicService.update(record);
    }

    //收藏主贴
    @RequiresUser
    @RequestMapping(value = {"tutorial/{id}/favorite","discuss/{id}/favorite","article/{id}/favorite","code/{id}/favorite"},method = RequestMethod.POST)
    public @ResponseBody Boolean favorite(@RequestBody Topic record){
        return this.favoriteService.insert(new Favorite(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")),record.getId(),new Date()));
    }

    //关闭主贴
    @RequestMapping( value = {"tutorial/{x}/block","discuss/{x}/block","article/{x}/block","code/{x}/block"}, method = RequestMethod.POST )
    public @ResponseBody Boolean block(@RequestBody Topic record){
        return topicService.changeStatus(record.getId());
    }


}
