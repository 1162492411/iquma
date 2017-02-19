package com.iquma.controller;

import com.iquma.pojo.*;
import com.iquma.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mo on 2016/10/30.
 */
@Controller
@RequestMapping("tutorial")
public class tutorialController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private OperationService operationService;
    

    //显示教程
    @RequestMapping(value = "{tid}", method = RequestMethod.GET)
    public String toTutorial(@PathVariable Integer tid, Reply condition, Model model) {
        condition.setTid(tid);
        Topic tutorial = topicService.selectById(tid);
        if(tutorial == null) return "status/emptyQuery";
        else{
            model.addAttribute("tutorial",tutorial);
            model.addAttribute("replies",replyService.selectByCondition(condition));
            return "tutorials/tutorial_default";
        }
    }

    //显示教程，并按时间显示回复
    @RequestMapping(value = "{tid}/{sort}", method = RequestMethod.GET)
    public String toTutorialSortByTime(@PathVariable Integer tid, @PathVariable String sort, Reply condition, Model model) {
        condition.setTid(tid);
        Topic tutorial = topicService.selectById(tid);
        if("time".equals(sort)){
            model.addAttribute("tutorial",tutorial);
            model.addAttribute("replies",replyService.selectByConditionSortByTime(condition));
            return "tutorials/tutorial_time";
        }
        else
            return "status/emptyQuery";
    }



    //删除教程
    @RequiresPermissions("tutorial:delete")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteTutorial(@PathVariable("id") String id) {
        if (topicService.deleteById(Integer.parseInt(id))) {
            return "suc";
        } else {
            return "err";
        }
    }


    //前往教程更新页面
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String toUpdateTutorial(@PathVariable String id, Model model){
        model.addAttribute("tutorial",topicService.selectById(Integer.parseInt(id)));
        return "tutorials/update";
    }

    //更新教程验证
    @RequiresPermissions("tutorial:update")
    @RequestMapping(value = "{id}/update", method = RequestMethod.PUT)
    public @ResponseBody String updateValidator(@RequestBody Topic record) {
        System.out.println("更新教程验证时接收到条件参数" + record);
        if (topicService.update(record)) return "suc";
        else return "err";

    }

    //收藏教程
    @RequiresUser
    @RequestMapping(value = "{id}/favorite")
    public @ResponseBody String favoriteTutorial(@RequestBody Favorite record){
        System.out.println("收藏教程控制器接收到信息" + record);
        if(this.favoriteService.insert(record)) return "suc";
        else return "err";
    }

    //关闭教程
    @RequiresPermissions("tutorial:block")
    @RequestMapping( value = "{xx}/block", method = RequestMethod.POST )
    public @ResponseBody String blockTutorial(@RequestBody Operation record,Permission permission){
        //TODO:此处代码编写完毕后进行测试
        System.out.println("关闭教程时收到参数" + record);
        permission.setPermission("tutorial:block");
        record.setPid(permissionService.selectByCondition(permission).getId());
        if(topicService.changeStatus(Integer.valueOf(record.getOpid()))){
            System.out.println("关闭帖子，即将记录信息" + record);
            operationService.insert(record); //记录用户操作
            return "suc";
        }
        else
            return "err";
    }


}
