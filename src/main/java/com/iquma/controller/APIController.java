package com.iquma.controller;

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

    //检测主贴是否被用户收藏
    @RequestMapping(value = "getIsFavorite" , method = RequestMethod.POST)
    public @ResponseBody Boolean checkIsFavorite(@RequestBody Favorite con){
        System.out.println("检测主贴是否被用户收藏时收到参数:" + con);
        return null != this.favoriteService.selectByCondition(con);
    }

    //查询投票信息
    @RequestMapping(value = "getRateInfo", method = RequestMethod.POST)
    public @ResponseBody String getRateInfo(@RequestBody Operation condition){
        String uid = String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid"));//首先获取条件参数
        condition.setUid(uid);
        return filterOperations(operationService.selectsByCondition(condition));//查询登录用户与该主贴/回复的全部操作记录并进行过滤
    }

    //过滤用户操作信息,仅取出用户对该主贴/回复的投票信息
    private String filterOperations(List list){
        for (int i = 0; i < list.size(); i++) {//遍历查询到的用户对该主贴/回复的操作信息
            String temp = ((Operation)list.get(i)).getPermission().getPermission();
            String action = temp.substring(temp.length() -4);//截取操作类型
            if("like".equals(action) || "hate".equals(action)) return action;//若发现是投票信息则返回投票信息
        }
        return "err";
    }


}


