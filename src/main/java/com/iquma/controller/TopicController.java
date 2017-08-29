package com.iquma.controller;

import com.iquma.exception.NoSuchTagException;
import com.iquma.exception.NoSuchTopicException;
import com.iquma.pojo.*;
import com.iquma.service.*;
import com.iquma.utils.BinomialUtil;
import java.util.Date;
import com.iquma.utils.GsonUtil;
import com.iquma.utils.PageUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

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
    private static int oldTopicCount = -2;//存储旧主贴总数
    private static String oldTname = "-2";//存储旧标签名
    private static Byte oldTagId = -2;//存储旧标签id
    private static String oldType = "-2";//存储旧分类方式
    private static int oldTopicId = -3;//存储旧主题id
    private static int oldReplyCount = -3;//存储旧回复总数
    private static Topic oldTopic = null;//存储旧主贴

    /**
     * 前往主贴列表页面--按页数和类型和分类--通用
     */
    @RequestMapping({"tutorials/**","questions/**","articles/**","codes/**"})
    public String toTopicsByCondition(Tag ta, Topic topic, Model model,HttpServletRequest httpServletRequest) throws NoSuchTagException {
        String[] parameters = urlConverters(httpServletRequest);//1-版块;2-标签;3-类型;4-页数
        String tags = handleTname(parameters[2],ta,topic);
        String sec = (parameters[1] == null ? null : parameters[1].substring(0,parameters[1].length() - 1));
        handleType(parameters[3],topic);
        topic.setSec(sec);
        int totalPage =  getTotalTopicPage(topic,parameters[2],parameters[3]);
        int page = PageUtil.getCurrentPage(Integer.parseInt(parameters[4]),totalPage);
        //绑定结果到model中
        model.addAttribute("sec",sec);
        model.addAttribute("tag",parameters[2]);
        model.addAttribute("type", parameters[3]);
        model.addAttribute("tags",tags);
        model.addAttribute("topics", topicService.selectsByPage(page,topic));
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",totalPage);
        oldTname = (parameters[2] == null ? "-2" : parameters[2]);
        oldType = (parameters[3] == null? "-2" : parameters[3]);
        return "topics/topics";
    }

    /**
     * 辅助函数--处理查询条件中的标签
     * 若标签为空，则返回所有标签，否则返回指定标签所在的标签体系内的所有标签
     * @param tname 查询条件-标签
     * @exception NoSuchTagException 不存在指定标签异常
     */
    private String handleTname(String tname, Tag tag, Topic topic) throws NoSuchTagException {
        if(null == tname)
            return GsonUtil.toJson(tagService.selectAll());//若没有选择标签则取出所有标签
        else {
            tag.setName(tname);//设置主贴类别
            Byte tid = oldTname.equals(tname) ? oldTagId : (oldTagId = tagService.selectByCondition(tag).getId());
            topic.setTid(tid);//设定查询条件
            return GsonUtil.toJson(tagService.selectsRelevant(tid));//若已选择标签则返回该标签所属的标签体系中的所有标签
        }
    }

    /**
     * 辅助函数--处理查询条件中的类型
     * @param type 类型
     */
    private void handleType(String type,Topic topic){
        if ("hottest".equals(type)) topic.setIsHigh(true);//按评分加载主贴
        else if ("unanswered".equals(type)) topic.setNoReply(true);//按回答数加载主贴
    }

    /**
     * 辅助函数--获取符合条件的主贴的总页数
     * @param tname 搜索条件之标签
     * @param type 搜索条件之类型
     */
    private int getTotalTopicPage(Topic condition,String tname,String type){
        if(!oldTname.equals(tname) || !oldType.equals(type))  oldTopicCount = topicService.selectsCount(condition);//若除页数外的搜索条件没有变化，则使用旧的总主贴数;否则重新查询
        return PageUtil.getTotalPage(oldTopicCount);
    }

    /**
     * 辅助函数--判断传入的数据是否是类型
     * @param condition 传入的数据
     * @return 是否是类型
     */
    private Boolean isType(String condition){
        return "hottest".equals(condition) || "unanswered".equals(condition) || "new".equals(condition);
    }

    /**
     * 工具函数--提取URL中的条件
     */
    private String[] urlConverters(HttpServletRequest httpServletRequest){
        String[] results = new String[5];//1-版块;2-标签;3-类型;4-页数
        String originUrl = httpServletRequest.getRequestURI();
        String[] originUrls = originUrl.split("/");
        if(originUrls.length >= 2){ //处理originUrls[1]
            results[1] = String.valueOf(originUrls[1]);//版块
        }
        if(originUrls.length >= 3){ //处理originUrls[2]
            String tempA = String.valueOf(originUrls[2]);
            try{
                int tempB = Integer.parseInt(tempA);
                results[4] = tempA;//版块、页数
            }catch (NumberFormatException e){
                if(isType(tempA)) results[3] = tempA;//版块、类型
                else results[2] = tempA;//版块、标签
            }
        }
        if(originUrls.length >= 4){//处理originUrls[3]
            String tempA = String.valueOf(originUrls[3]);
            try{
                int tempB = Integer.parseInt(tempA);
                results[4] = tempA;//版块、类型、页数;版块、标签、页数
            }catch (NumberFormatException e){
                if(isType(tempA)) results[3] = tempA;//版块、标签、类型;版块、类型、类型(x)
            }
        }
        if(originUrls.length >= 5){//处理originUrls[4]
            results[4] = String.valueOf(originUrls[4]);//版块、标签、类型、页数
        }
        if(null == results[4]) results[4] = "1";//特殊设置默认值
        return results;
    }

    /**
     * 显示主贴--通用
     * @param tid 话题id
     * @param sort 回复的排序方式--默认/时间
     * @param page 页数
     */
    private String toTopic(Integer tid, String sort,Integer page, Reply reply, Model model) throws NoSuchTopicException {
        Topic topic = oldTopicId == tid ? oldTopic : (oldTopic = topicService.selectById(tid));//查询主贴是否存在
        if(oldTopicId != tid)
            topicService.increaseViewCount(topic.getId());//仅当选择新主贴时更新浏览量(防止重复计算)
        int replyTotalPage = getTotalReplyPage(tid,reply);
        int replyCurrentPage = PageUtil.getCurrentPage(page,replyTotalPage);
        model.addAttribute("topic", topic);
        model.addAttribute("sort",sort == null ? (sort = "default") : sort);
        model.addAttribute("total",oldReplyCount);
        model.addAttribute("currentPage",replyCurrentPage);
        model.addAttribute("totalPage",replyTotalPage);
        model.addAttribute("replies", sort.equals("default") ? replyService.selectsByPage(page,reply) : replyService.selectsByPageSorted(page, reply));
        oldTopicId = tid;
        return "topics/topic";
    }

    /**
     * 显示主贴--按主贴id显示回复
     */
    @RequestMapping(value = {"tutorial/{tid}","question/{tid}","article/{tid}","code/{tid}"})
    public String toTopicByID(@PathVariable Integer tid, Reply reply, Model model)  throws NoSuchTopicException{
        return toTopic(tid,"default",1,reply,model);
    }

    /**
     * 显示主贴--按分页/时间显示回复
     */
    @RequestMapping({"tutorial/{tid}/{con}","question/{tid}/{con}","article/{tid}/{con}","code/{tid}/{con}"})
    public String toTopicByDefaultORTime(@PathVariable int tid,@PathVariable String con, Reply reply, Model model) throws NoSuchTopicException{
        if("time".equals(con))//如果是按时间显示
            return toTopic(tid,con,1,reply,model);
        else//如果按分页显示
            return toTopic(tid,"default",Integer.parseInt(con),reply,model);
    }

    /**
     * 显示主贴，并按时间和分页显示回复
     */
    @RequestMapping({"tutorial/{tid}/{sort}/{page}","question/{tid}/{sort}/{page}","article/{tid}/{sort}/{page}","code/{tid}/{sort}/{page}"})
    public String toTopicByTimePage(@PathVariable Integer tid, @PathVariable String sort, @PathVariable Integer page, Reply reply, Model model) throws NoSuchTopicException {
        return toTopic(tid,sort,page,reply,model);
    }

    /**
     * 辅助函数--获取主贴的回复总页数
     * 仅当该次查询与上次查询的不是同一主贴的回复时查询主贴的总回复页数
     * @param tid 查询的主贴ID
     * @return 主贴的总回复页数
     */
    private int getTotalReplyPage(Integer tid,Reply reply){
        reply.setTid(tid);
        oldReplyCount = (oldTopicId == tid ? oldReplyCount : replyService.selectsCount(reply));
        return PageUtil.getTotalPage(oldReplyCount);
    }

    //前往主贴更新页面
    @RequestMapping(value = {"tutorial/{id}/update","question/{id}/update","article/{id}/update","code/{id}/update"}, method = RequestMethod.GET)
    public String toUpdateTopic(@PathVariable Integer id, Model model) throws NoSuchTopicException {
        Topic topic = topicService.selectById(id);
        if(topic == null) throw new NoSuchTopicException();//主贴不存在则跳转空查询页面
        if(SecurityUtils.getSubject().isPermitted(topic.getSec() + ":update:" + topic.getId())){
            model.addAttribute("topic",topic);
            return "topics/update";
        }
        else throw new ShiroException();
    }

    //更新主贴验证
    @RequestMapping(value = {"tutorial/{idd}/update","question/{idd}/update","article/{idd}/update","code/{idd}/update"}, method = RequestMethod.PUT)
    public @ResponseBody Boolean update(@RequestBody Topic record) throws NoSuchTopicException {
        if(SecurityUtils.getSubject().isPermitted((topicService.selectById(record.getId()).getSec() + ":update:" + record.getId())))
        {System.out.println("传递信息时收到的信息是" + record);   return topicService.update(record);}
        else
           return false;
    }

    //关闭主贴
    @RequestMapping( value = {"tutorial/{id}/block","question/{id}/block","article/{id}/block","code/{id}/block"}, method = RequestMethod.POST )
    public @ResponseBody Boolean block(@RequestBody Topic record){
        if(SecurityUtils.getSubject().isPermitted(record.getSec() + ":block:" + record.getId()))
        return topicService.changeStatus(record.getId());
        else throw new ShiroException();
    }

    //删除主贴
    @RequestMapping(value = {"tutorial/{id}","question/{id}","article/{id}","code/{id}"}, method = RequestMethod.DELETE)
    public @ResponseBody Boolean delete(@RequestBody Topic record) {
        if(SecurityUtils.getSubject().isPermitted(record.getSec() + ":block:" + record.getId()))
        return topicService.deleteById(record.getId());
        else throw new ShiroException();
    }

    //收藏主贴
    @RequiresUser
    @RequestMapping(value = {"tutorial/{id}/favorite","question/{id}/favorite","article/{id}/favorite","code/{id}/favorite"},method = RequestMethod.POST)
    public @ResponseBody Boolean favorite(@RequestBody Topic record){
        return this.favoriteService.insert(new Favorite(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")),record.getId(),new Date()));
    }

    //赞同主贴
    @RequiresPermissions(value = {"tutorial:like","question:like","article:like","code:like"},logical=Logical.OR)
    @RequestMapping(value = {"tutorial/{id}/like","question/{id}/like","article/{id}/like","code/{id}/like"}, method = RequestMethod.POST)
    public @ResponseBody Boolean like(@RequestBody Topic record) throws NoSuchTopicException {
        record = topicService.selectById(record.getId());
        double likeCount = binomialUtil.getLikeCount(record.getLikeCount());
        double hateCount = record.getHateCount();
        return topicService.rate(new Topic(record.getId(),likeCount,hateCount,binomialUtil.getRateCount(likeCount,hateCount)));
    }

    //反对主贴
    @RequestMapping(value = {"tutorial/{id}/hate","question/{id}/hate","article/{id}/hate","code/{id}/hate"}, method = RequestMethod.POST)
    public @ResponseBody Boolean hate(@RequestBody Topic record) throws NoSuchTopicException {
        if(SecurityUtils.getSubject().isPermitted(record.getSec() + ":hate:" + record.getId())){
            record = topicService.selectById(record.getId());
            double likeCount = record.getLikeCount();
            double hateCount = binomialUtil.getHateCount(record.getHateCount());
            return topicService.rate(new Topic(record.getId(),likeCount,hateCount,binomialUtil.getRateCount(likeCount,hateCount)));
        }
        else throw new ShiroException();
    }


    /**
     * 查看所有标签
     */
    @RequestMapping("tags")
    public String toTags(Model model){
        //TODO:待填写
        model.addAttribute("tags",GsonUtil.toJson(tagService.selectAll()));
        return "topics/tags";
    }

}
