package com.iquma.controller;

import com.iquma.exception.NoSuchSectionException;
import com.iquma.pojo.*;
import com.iquma.service.*;
import com.iquma.utils.BinomialUtil;
import com.iquma.utils.ENUMS;
import org.apache.shiro.SecurityUtils;
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
    @Autowired
    private TagService tagService;
    private static final byte HIGH_RATE_COUNT = 8;

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

    //前往教程列表页面--按页数
    @RequestMapping(value = "tutorials/{page}", method = RequestMethod.GET)
    public String toTutorialsByPage(@PathVariable int page,Tag tag, Topic topic, Model model) {
        return toTopicsByCondition(null,null,page,tag,topic,model, ENUMS.SECTION_TUTORIAL);
    }

    //前往教程列表页面--按页数或类型
    @RequestMapping(value = "tutorials/{type}/{page}", method = RequestMethod.GET)
    public String toTutorialsByType(@PathVariable String type, @PathVariable int page,Tag tag, Topic topic, Model model) {
        //如果是按类型获取主贴
        if("hottest".equals(type) || "unanswered".equals(type) || "new".equals(type))
            return toTopicsByCondition(null,type,page,tag,topic,model,ENUMS.SECTION_TUTORIAL);
        else//如果是按标签获取主贴
            return toTopicsByCondition(type,null,page,tag,topic,model,ENUMS.SECTION_TUTORIAL);
    }

    //前往教程列表页面--按页数和类型和分类
    @RequestMapping(value = "tutorials/{tname}/{type}/{page}", method = RequestMethod.GET)
    public String toTutorialsByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model)  {
        return toTopicsByCondition(tname,type,page,ta,topic,model,ENUMS.SECTION_TUTORIAL);
    }

    //前往提问列表页面--按页数
    @RequestMapping(value = "discusses/{page}", method = RequestMethod.GET)
    public String toDiscussesByPage(@PathVariable int page,Tag tag, Topic topic, Model model) {
        return toTopicsByCondition(null,null,page,tag,topic,model,ENUMS.SECTION_DISCUSS);
    }

    //前往提问列表页面--按页数和类型
    @RequestMapping(value = "discusses/{type}/{page}", method = RequestMethod.GET)
    public String toDiscussesByType(@PathVariable String type, @PathVariable int page,Tag tag, Topic topic, Model model) {
        //如果是按类型获取主贴
        if("hottest".equals(type) || "unanswered".equals(type) || "new".equals(type))
            return toTopicsByCondition(null,type,page,tag,topic,model,ENUMS.SECTION_DISCUSS);
        else//如果是按标签获取主贴
            return toTopicsByCondition(type,null,page,tag,topic,model,ENUMS.SECTION_DISCUSS);
    }

    //前往提问列表页面--按页数和类型和分类
    @RequestMapping(value = "discusses/{tname}/{type}/{page}", method = RequestMethod.GET)
    public String toDiscussesByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model) {
        return toTopicsByCondition(tname,type,page,ta,topic,model,ENUMS.SECTION_DISCUSS);
    }

    //前往经验列表页面--按页数
    @RequestMapping(value = "articles/{page}", method = RequestMethod.GET)
    public String toArticlesByPage(@PathVariable int page,Tag tag, Topic topic, Model model) {
        return toTopicsByCondition(null,null,page,tag,topic,model,ENUMS.SECTION_ARTICLE);
    }

    //前往经验列表页面--按页数和类型
    @RequestMapping(value = "articles/{type}/{page}", method = RequestMethod.GET)
    public String toArticlesByType(@PathVariable String type, @PathVariable int page,Tag tag, Topic topic, Model model) {
        //如果是按类型获取主贴
        if("hottest".equals(type) || "unanswered".equals(type) || "new".equals(type))
            return toTopicsByCondition(null,type,page,tag,topic,model,ENUMS.SECTION_ARTICLE);
        else//如果是按标签获取主贴
            return toTopicsByCondition(type,null,page,tag,topic,model,ENUMS.SECTION_ARTICLE);
    }

    //前往经验列表页面--按页数和类型和分类
    @RequestMapping(value = "articles/{tname}/{type}/{page}", method = RequestMethod.GET)
    public String toArticlesByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model)  {
        return toTopicsByCondition(tname,type,page,ta,topic,model,ENUMS.SECTION_ARTICLE);
    }

    //前往代码列表页面--按页数
    @RequestMapping(value = "codes/{page}", method = RequestMethod.GET)
    public String toCodesByPage(@PathVariable int page,Tag tag, Topic topic, Model model) {
        return toTopicsByCondition(null,null,page,tag,topic,model,ENUMS.SECTION_CODE);
    }

    //前往代码列表页面--按页数和类型
    @RequestMapping(value = "codes/{type}/{page}", method = RequestMethod.GET)
    public String toCodesByType(@PathVariable String type, @PathVariable int page,Tag tag, Topic topic, Model model)  {
        //如果是按类型获取主贴
        if("hottest".equals(type) || "unanswered".equals(type) || "new".equals(type))
            return toTopicsByCondition(null,type,page,tag,topic,model,ENUMS.SECTION_CODE);
        else//如果是按标签获取主贴
            return toTopicsByCondition(type,null,page,tag,topic,model,ENUMS.SECTION_CODE);
    }

    //前往代码列表页面--按页数和类型和分类
    @RequestMapping(value = "codes/{tname}/{type}/{page}", method = RequestMethod.GET)
    public String toCodesByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model)  {
        return toTopicsByCondition(tname,type,page,ta,topic,model,ENUMS.SECTION_CODE);
    }

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

    //显示主贴--按主贴id
    @RequestMapping(value = {"tutorial/{tid}","discuss/{tid}","article/{tid}","code/{tid}"})
    public String toTopicByDefault(@PathVariable Integer tid, Reply reply, Model model)  {
        return toTopics(tid,null,0,reply,model);
    }

    //显示主贴--按分页显示回复或者按时间显示回复
    @RequestMapping(value = {"tutorial/{tid}/{con}","discuss/{tid}/{con}","article/{tid}/{con}","code/{tid}/{con}"},method = RequestMethod.GET)
    public String toTopicByDefaultPage(@PathVariable int tid,@PathVariable String con, Reply reply, Model model) {
        if(con.equals("null") || "time".equals(con))//如果是按时间显示或按默认显示
            return toTopics(tid,con,0,reply,model);
        else//如果按分页显示
            return toTopics(tid,null,Integer.parseInt(con),reply,model);
    }

    //显示主贴，并按时间和分页显示回复
    @RequestMapping(value = {"tutorial/{tid}/{sort}/{page}","discuss/{tid}/{sort}/{page}","article/{tid}/{sort}/{page}","code/{tid}/{sort}/{page}"}, method = RequestMethod.GET)
    public String toTopicSortByTimePage(@PathVariable Integer tid, @PathVariable String sort, @PathVariable Integer page, Reply reply, Model model) {
        return toTopics(tid,sort,page,reply,model);
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

    //关闭主贴
    @RequestMapping( value = {"tutorial/{id}/block","discuss/{id}/block","article/{id}/block","code/{id}/block"}, method = RequestMethod.POST )
    public @ResponseBody Boolean block(@RequestBody Topic record){
        return topicService.changeStatus(record.getId());
    }

    //删除主贴
    @RequestMapping(value = {"tutorial/{id}","discuss/{id}","article/{id}","code/{id}"}, method = RequestMethod.DELETE)
    public @ResponseBody Boolean delete(@RequestBody Topic record) {
        return topicService.deleteById(record.getId());
    }

    //收藏主贴
    @RequiresUser
    @RequestMapping(value = {"tutorial/{id}/favorite","discuss/{id}/favorite","article/{id}/favorite","code/{id}/favorite"},method = RequestMethod.POST)
    public @ResponseBody Boolean favorite(@RequestBody Topic record){
        return this.favoriteService.insert(new Favorite(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")),record.getId(),new Date()));
    }

    //赞同主贴
    @RequestMapping(value = {"tutorial/{id}/like","discuss/{id}/like","article/{id}/like","code/{id}/like"}, method = RequestMethod.POST)
    public @ResponseBody Boolean like(@RequestBody Topic record){
        record = topicService.selectById(record.getId());
        double likeCount = binomialUtil.getLikeCount(record.getLikeCount());
        double hateCount = record.getHateCount();
        return topicService.rate(new Topic(record.getId(),likeCount,hateCount,binomialUtil.getRateCount(likeCount,hateCount)));
    }

    //反对主贴
    @RequestMapping(value = {"tutorial/{id}/hate","discuss/{id}/hate","article/{id}/hate","code/{id}/hate"}, method = RequestMethod.POST)
    public @ResponseBody Boolean hate(@RequestBody Topic record){
        record = topicService.selectById(record.getId());
        double likeCount = record.getLikeCount();
        double hateCount = binomialUtil.getHateCount(record.getHateCount());
        return topicService.rate(new Topic(record.getId(),likeCount,hateCount,binomialUtil.getRateCount(likeCount,hateCount)));
    }

}
