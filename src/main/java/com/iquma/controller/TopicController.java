package com.iquma.controller;


import com.iquma.pojo.*;
import com.iquma.service.*;
import com.iquma.utils.BinomialUtil;
import com.iquma.utils.CASTS;
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


    /**
     * 前往主贴列表页面--按页数和类型和分类--通用
     * @param tname 标签名
     * @param type 分类方式--时间/未回答/高评分
     * @param page 页数
     * @param sec 版块
     */
    private String toTopicsByCondition(String tname, String type,Integer page, Tag ta, Topic topic, Model model,String sec){
        List tags = handleTname(tname,ta,topic);
        handleType(type,topic);
        topic.setSec(sec);
        List<Topic> results = topicService.selectsSimpleByConditionAndPage(page,topic);
        //绑定结果到model中
        model.addAttribute("sec",sec);
        model.addAttribute("tag",tname == null? "null" : tname);
        model.addAttribute("tags",tags);
        model.addAttribute("type", type);
        if(0 == results.size()) model.addAttribute("searchEmpty",Boolean.TRUE);
        else model.addAttribute("topics", CASTS.translateToSB(results));
        int totalPage = getTotalPage(topic);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage",page >= totalPage?totalPage : page <= 0? 1 : page);
        return "topics/topics";
    }

    //处理查询条件中的标签
    private List handleTname(String tname, Tag tag, Topic topic){
        if(null == tname)
            return tagService.selectAll();//若没有选择标签则取出所有标签
        else {
            tag.setName(tname);//设置主贴类别
            Byte tid = tagService.selectByCondition(tag).getId();
            topic.setTid(tid);//设定查询条件
            return tagService.selectsRelevant(tid);//若已选择标签则返回该标签所属的标签体系中的所有标签
        }
    }

    //处理查询条件中的类型
    private void handleType(String type,Topic topic){
        if ("hottest".equals(type)) topic.setRateCount(ENUMS.HIGH_RATE_COUNT);//按评分加载主贴
        else if ("unanswered".equals(type)) topic.setReplyCount(0);//按回答数加载主贴
    }

    //获取符合条件的主贴的总页数
    private int getTotalPage(Topic condition){
        int total = topicService.selectsSimpleByCondition(condition).size();
        return total % 10 == 0 ? total / 10 : total / 10 + 1;
    }

    //前往教程列表页面--默认
    @RequestMapping("tutorials")
    public String toTutorials(Tag tag, Topic topic, Model model){
        return toTopicsByCondition(null,"new",1,tag,topic,model, ENUMS.SEC_TUTORIAL);
    }

    //前往教程列表页面--按页数
    @RequestMapping("tutorials/{page}")
    public String toTutorialsByPage(@PathVariable int page,Tag tag, Topic topic, Model model) {
        return toTopicsByCondition(null,"new",page,tag,topic,model, ENUMS.SEC_TUTORIAL);
    }

    //前往教程列表页面--按页数或类型
    @RequestMapping("tutorials/{type}/{page}")
    public String toTutorialsByType(@PathVariable String type, @PathVariable int page,Tag tag, Topic topic, Model model) {
        //如果是按类型获取主贴
        if("hottest".equals(type) || "unanswered".equals(type) || "new".equals(type))
            return toTopicsByCondition(null,type,page,tag,topic,model,ENUMS.SEC_TUTORIAL);
        else//如果是按标签获取主贴
            return toTopicsByCondition(type,null,page,tag,topic,model,ENUMS.SEC_TUTORIAL);
    }

    //前往教程列表页面--按页数和类型和分类
    @RequestMapping("tutorials/{tname}/{type}/{page}")
    public String toTutorialsByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model)  {
        return toTopicsByCondition(tname,type,page,ta,topic,model,ENUMS.SEC_TUTORIAL);
    }

    //前往提问列表页面--默认
    @RequestMapping("questions")
    public String toQuestions(Tag tag, Topic topic, Model model){
        return toTopicsByCondition(null,"new",1,tag,topic,model,ENUMS.SEC_QUESTION);
    }

    //前往提问列表页面--按页数
    @RequestMapping("questions/{page}")
    public String toQuestionsByPage(@PathVariable int page,Tag tag, Topic topic, Model model) {
        return toTopicsByCondition(null,"new",page,tag,topic,model,ENUMS.SEC_QUESTION);
    }

    //前往提问列表页面--按页数和类型
    @RequestMapping("questions/{type}/{page}")
    public String toQuestionsByType(@PathVariable String type, @PathVariable int page,Tag tag, Topic topic, Model model) {
        //如果是按类型获取主贴
        if("hottest".equals(type) || "unanswered".equals(type) || "new".equals(type))
            return toTopicsByCondition(null,type,page,tag,topic,model,ENUMS.SEC_QUESTION);
        else//如果是按标签获取主贴
            return toTopicsByCondition(type,null,page,tag,topic,model,ENUMS.SEC_QUESTION);
    }

    //前往提问列表页面--按页数和类型和分类
    @RequestMapping("questions/{tname}/{type}/{page}")
    public String toQuestionsByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model) {
        return toTopicsByCondition(tname,type,page,ta,topic,model,ENUMS.SEC_QUESTION);
    }

    //前往经验列表页面--默认
    @RequestMapping("articles")
    public String toArticles(Tag tag, Topic topic, Model model){
        return toTopicsByCondition(null,"new",1,tag,topic,model,ENUMS.SEC_ARTICLE);
    }

    //前往经验列表页面--按页数
    @RequestMapping("articles/{page}")
    public String toArticlesByPage(@PathVariable int page,Tag tag, Topic topic, Model model) {
        return toTopicsByCondition(null,"new",page,tag,topic,model,ENUMS.SEC_ARTICLE);
    }

    //前往经验列表页面--按页数和类型
    @RequestMapping("articles/{type}/{page}")
    public String toArticlesByType(@PathVariable String type, @PathVariable int page,Tag tag, Topic topic, Model model) {
        //如果是按类型获取主贴
        if("hottest".equals(type) || "unanswered".equals(type) || "new".equals(type))
            return toTopicsByCondition(null,type,page,tag,topic,model,ENUMS.SEC_ARTICLE);
        else//如果是按标签获取主贴
            return toTopicsByCondition(type,null,page,tag,topic,model,ENUMS.SEC_ARTICLE);
    }

    //前往经验列表页面--按页数和类型和分类
    @RequestMapping("articles/{tname}/{type}/{page}")
    public String toArticlesByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model)  {
        return toTopicsByCondition(tname,type,page,ta,topic,model,ENUMS.SEC_ARTICLE);
    }

    //前往代码列表页面--默认
    @RequestMapping("codes")
    public String toCodes(Tag tag, Topic topic, Model model){
        return toTopicsByCondition(null,"new",1,tag,topic,model,ENUMS.SEC_CODE);
    }

    //前往代码列表页面--按页数
    @RequestMapping("codes/{page}")
    public String toCodesByPage(@PathVariable int page,Tag tag, Topic topic, Model model) {
        return toTopicsByCondition(null,"new",page,tag,topic,model,ENUMS.SEC_CODE);
    }

    //前往代码列表页面--按页数和类型
    @RequestMapping("codes/{type}/{page}")
    public String toCodesByType(@PathVariable String type, @PathVariable int page,Tag tag, Topic topic, Model model)  {
        //如果是按类型获取主贴
        if("hottest".equals(type) || "unanswered".equals(type) || "new".equals(type))
            return toTopicsByCondition(null,type,page,tag,topic,model,ENUMS.SEC_CODE);
        else//如果是按标签获取主贴
            return toTopicsByCondition(type,null,page,tag,topic,model,ENUMS.SEC_CODE);
    }

    //前往代码列表页面--按页数和类型和分类
    @RequestMapping("codes/{tname}/{type}/{page}")
    public String toCodesByTag(@PathVariable String tname, @PathVariable String type, @PathVariable int page, Tag ta, Topic topic, Model model)  {
        return toTopicsByCondition(tname,type,page,ta,topic,model,ENUMS.SEC_CODE);
    }

    /**
     * 显示主贴--通用
     * @param tid 话题id
     * @param sort 回复的排序方式--默认/时间
     * @param page 页数
     */

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
    @RequestMapping(value = {"tutorial/{tid}","question/{tid}","article/{tid}","code/{tid}"})
    public String toTopicByDefault(@PathVariable Integer tid, Reply reply, Model model)  {
        return toTopics(tid,null,0,reply,model);
    }

    //显示主贴--按分页显示回复或者按时间显示回复
    @RequestMapping({"tutorial/{tid}/{con}","question/{tid}/{con}","article/{tid}/{con}","code/{tid}/{con}"})
    public String toTopicByDefaultPage(@PathVariable int tid,@PathVariable String con, Reply reply, Model model) {
        if(con.equals("null") || "time".equals(con))//如果是按时间显示或按默认显示
            return toTopics(tid,con,0,reply,model);
        else//如果按分页显示
            return toTopics(tid,null,Integer.parseInt(con),reply,model);
    }

    //显示主贴，并按时间和分页显示回复
    @RequestMapping({"tutorial/{tid}/{sort}/{page}","question/{tid}/{sort}/{page}","article/{tid}/{sort}/{page}","code/{tid}/{sort}/{page}"})
    public String toTopicSortByTimePage(@PathVariable Integer tid, @PathVariable String sort, @PathVariable Integer page, Reply reply, Model model) {
        return toTopics(tid,sort,page,reply,model);
    }

    //前往主贴更新页面
    @RequestMapping(value = {"tutorial/{id}/update","question/{id}/update","article/{id}/update","code/{id}/update"}, method = RequestMethod.GET)
    public String toUpdateTopic(@PathVariable Integer id, Model model){
        Topic topic = topicService.selectById(id);
        if(topic == null) return "status/emptyQuery";//主贴不存在则跳转空查询页面
        model.addAttribute("topic",topic);
        return "topics/update";
    }

    //更新主贴验证
    @RequestMapping(value = {"tutorial/{id}/update","question/{id}/update","article/{id}/update","code/{id}/update"}, method = RequestMethod.PUT)
    public @ResponseBody Boolean update(@RequestBody Topic record){
         return topicService.update(record);
    }

    //关闭主贴
    @RequestMapping( value = {"tutorial/{id}/block","question/{id}/block","article/{id}/block","code/{id}/block"}, method = RequestMethod.POST )
    public @ResponseBody Boolean block(@RequestBody Topic record){
        return topicService.changeStatus(record.getId());
    }

    //删除主贴
    @RequestMapping(value = {"tutorial/{id}","question/{id}","article/{id}","code/{id}"}, method = RequestMethod.DELETE)
    public @ResponseBody Boolean delete(@RequestBody Topic record) {
        return topicService.deleteById(record.getId());
    }

    //收藏主贴
    @RequiresUser
    @RequestMapping(value = {"tutorial/{id}/favorite","question/{id}/favorite","article/{id}/favorite","code/{id}/favorite"},method = RequestMethod.POST)
    public @ResponseBody Boolean favorite(@RequestBody Topic record){
        return this.favoriteService.insert(new Favorite(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")),record.getId(),new Date()));
    }

    //赞同主贴
    @RequestMapping(value = {"tutorial/{id}/like","question/{id}/like","article/{id}/like","code/{id}/like"}, method = RequestMethod.POST)
    public @ResponseBody Boolean like(@RequestBody Topic record){
        record = topicService.selectById(record.getId());
        double likeCount = binomialUtil.getLikeCount(record.getLikeCount());
        double hateCount = record.getHateCount();
        return topicService.rate(new Topic(record.getId(),likeCount,hateCount,binomialUtil.getRateCount(likeCount,hateCount)));
    }

    //反对主贴
    @RequestMapping(value = {"tutorial/{id}/hate","question/{id}/hate","article/{id}/hate","code/{id}/hate"}, method = RequestMethod.POST)
    public @ResponseBody Boolean hate(@RequestBody Topic record){
        record = topicService.selectById(record.getId());
        double likeCount = record.getLikeCount();
        double hateCount = binomialUtil.getHateCount(record.getHateCount());
        return topicService.rate(new Topic(record.getId(),likeCount,hateCount,binomialUtil.getRateCount(likeCount,hateCount)));
    }

}
