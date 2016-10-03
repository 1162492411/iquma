package com.iquma.controller;

import com.iquma.pojo.Topic;
import com.iquma.service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by Mo on 2016/10/3.
 */
@Controller
@RequestMapping("discuss")
public class DiscussController {

    @Resource
    private TopicService topicService;
    private String result;

    //前往显示问题页面
    @RequestMapping()
    public String toShowTopic(@RequestParam("id")Integer id, Model model){
        model.addAttribute("topic",this.topicService.selectTopicById(id));
        return "discuss/discuss";
    }

    //前往提问页面
    @RequestMapping("add")
    public String toAddDiscuss(@RequestParam("uid")String uid, Model model){
        model.addAttribute("uid",uid);
        return "discuss/add";
    }

    //提问验证
    @RequestMapping("add/validator")
    public String validatorAddDiscuss(Topic discuss, Model model){
        System.out.println("前端传进来的数据为:" + discuss);
        if(this.topicService.insertTopic(discuss)){
            result = "成功提问";
        }
        else{
            result = "提问失败";
        }
        model.addAttribute("result",result);
        return "status/actionResult";
    }



    //前往修改问题页面
    @RequestMapping("update")
    public String toUpdateDiscuss(@RequestParam("id")Integer id, Model model) {
        model.addAttribute("topic",this.topicService.selectTopicById(id));
        return "discuss/update";
    }

    //修改问题验证
    @RequestMapping("update/validator")
    public String validatorUpdateDiscuss(Topic discuss, Model model){
        System.out.println("前端传来的信息为:" + discuss);
        if(this.topicService.updateTopic(discuss)){
            result = "修改提问成功";
        }
        else{
            result = "修改提问失败";
        }
        model.addAttribute("result",result);
        return "status/actionResult";

    }

}
