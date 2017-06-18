package com.iquma.controller;

import com.alibaba.fastjson.JSONArray;
import com.iquma.pojo.Favorite;
import com.iquma.pojo.Operation;
import com.iquma.pojo.Permission;
import com.iquma.pojo.Tag;
import com.iquma.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("api")
public class APIController {

    @Autowired
    private TagService tagService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private OperationService operationService;


    //获取分类
    @RequestMapping("getFirstTags")
    public @ResponseBody List getFirstTags(Tag tag){
        tag.setPid(Byte.valueOf("0"));
        return tagService.selectsByCondition(tag);
    }

    //获取分类所属的所有标签
    @RequestMapping("getTagsByPid/{id}")
    public @ResponseBody List getTags(@PathVariable Byte id,Tag condition){
//        condition.setPid(Byte.parseByte(pid));
        condition.setId(id);
        System.out.println("即将传递标签参数：" + condition);
        return tagService.selectsByCondition(condition);
    }

    //获取所有标签
    @RequestMapping("getAllTags")
    public @ResponseBody List getAllTags(){
        return tagService.selectAll();
    }

    //检测主贴是否被用户收藏
    @RequestMapping(value = "getIsFavorite" , method = RequestMethod.POST)
    public @ResponseBody Boolean checkIsFavorite(@RequestBody Favorite con){
        con.setUid(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        System.out.println("检测主贴是否被用户收藏时收到参数:" + con);
        return null != this.favoriteService.selectByCondition(con);
    }

    //查询用户对主贴的投票信息
    @RequestMapping(value = "getTopicRateInfo", method = RequestMethod.POST)
    public @ResponseBody String getTopicRateInfo(@RequestBody Operation condition){
        condition.setUid(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        String action = operationService.selectsTopicRateInfo(condition);
        return action == null? "null" : action.substring(action.length()-4);
    }

    //查询用户对回复的投票信息
    @RequestMapping(value = "getReplyRateInfo", method = RequestMethod.POST)
    public @ResponseBody String getReplyRateInfo(@RequestBody Operation condition){
        condition.setUid(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        String action = operationService.selectsReplyRateInfo(condition);
        return action == null? "null" : action.substring(action.length()-4);
    }

}


