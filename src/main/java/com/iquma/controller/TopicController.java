package com.iquma.controller;

import com.iquma.exception.NoSuchSectionException;
import com.iquma.pojo.*;
import com.iquma.service.*;
import com.iquma.utils.BinomialUtil;
import com.iquma.utils.CASTS;
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
    @Autowired
    private BinomialUtil binomialUtil;

    //显示主贴--通用
    private String toTopics(Integer tid, String sort,Integer page, Reply reply, Model model){
        reply.setTid(tid);//查询主贴是否存在
        Topic topic = topicService.selectById(tid);
        if(topic == null) return "status/emptyQuery";//主贴不存在则跳转空查询页面
        else{
            model.addAttribute("topic", topic);
            int total = this.replyService.selectByCondition(reply).size();
            model.addAttribute("total",total);
            model.addAttribute("currentPage",page);
            model.addAttribute("totalPage",total % 10 == 0 ? total / 10 : total / 10 + 1);
            if("time".equals(sort)){//如果回复按时间排序
                model.addAttribute("replies",replyService.selectByConditionSortByTime(page == 0 ? 1 : page, reply));
                return "topics/topic_time";
            }
            else{//如果主贴回复按默认排序
                model.addAttribute("replies",replyService.selectByConditionAndPage(page == 0? 1 : page ,reply));
                return "topics/topic_default";
            }
        }
    }



    //显示主贴
    @RequestMapping(value = {"tutorial/{tid}","discuss/{tid}","article/{tid}","code/{tid}"})
    public String toTopicByDefault(@PathVariable Integer tid, Reply reply, Model model) throws NoSuchSectionException {
        return toTopics(tid,null,0,reply,model);
    }

    //显示主贴--按分页显示回复或者按时间显示回复
    @RequestMapping(value = {"tutorial/{ti}/{con}","discuss/{ti}/{con}","article/{ti}/{con}","code/{ti}/{con}"},method = RequestMethod.GET)
    public String toTopicByDefaultPage(@PathVariable int ti,@PathVariable String con, Reply reply, Model model) throws NoSuchSectionException {
        if(con.equals("null") || "time".equals(con))//如果是按时间显示或按默认显示
            return toTopics(ti,con,0,reply,model);
        else//如果按分页显示
            return toTopics(ti,null,Integer.parseInt(con),reply,model);
    }

    //显示主贴，并按时间和分页显示回复
    @RequestMapping(value = {"tutorial/{tid}/{sort}/{page}","discuss/{tid}/{sort}/{page}","article/{tid}/{sort}/{page}","code/{tid}/{sort}/{page}"}, method = RequestMethod.GET)
    public String toTopicSortByTimePage(@PathVariable Integer tid, @PathVariable String sort, @PathVariable Integer page, Reply reply, Model model) {
        return toTopics(tid,sort,page,reply,model);
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


    //赞同主贴
    @RequestMapping(value = {"tutorial/{id}/like","discuss/{id}/like","article/{id}/like","code/{id}/like"}, method = RequestMethod.PUT)
    public @ResponseBody Boolean like(@RequestBody Topic record){
        record = topicService.selectById(record.getId());
        double likeCount = binomialUtil.getLikeCount(record.getLikeCount());
        double hateCount = record.getHateCount();
        return topicService.rate(new Topic(record.getId(),likeCount,hateCount,binomialUtil.getRateCount(likeCount,hateCount)));
    }

    //反对主贴
    @RequestMapping(value = {"tutorial/{id}/hate","discuss/{id}/hate","article/{id}/hate","code/{id}/hate"}, method = RequestMethod.PUT)
    public @ResponseBody Boolean hate(@RequestBody Topic record){
        record = topicService.selectById(record.getId());
        double likeCount = record.getLikeCount();
        double hateCount = binomialUtil.getHateCount(record.getHateCount());
        return topicService.rate(new Topic(record.getId(),likeCount,hateCount,binomialUtil.getRateCount(likeCount,hateCount)));
    }

}
