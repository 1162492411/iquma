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

//    //接收标签--测试用
//    @RequestMapping("tagsSubmit")
//    public @ResponseBody Boolean tagsSubmit(@RequestBody String tags){
////        JSONArray tag = JSONArray.parseArray(tags);
////        for (int i = 0; i < tag.size(); i++) {
////            System.out.println("分离出标签:" + tag.get(i));
////        }
//        System.out.println("测试接收标签时收到参数:" + tags);
//        return false;
//    }

    //检测主贴是否被用户收藏
    @RequestMapping(value = "getIsFavorite" , method = RequestMethod.POST)
    public @ResponseBody Boolean checkIsFavorite(@RequestBody Favorite con){
        con.setUid(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        System.out.println("检测主贴是否被用户收藏时收到参数:" + con);
        return null != this.favoriteService.selectByCondition(con);
    }

    //查询投票信息
    @RequestMapping(value = "getRateInfo", method = RequestMethod.POST)
    public @ResponseBody String getRateInfo(@RequestBody Operation condition){
        condition.setUid(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        String action = operationService.selectsRateInfo(condition);
        return action == null? "null" : action.substring(action.length()-4);
    }

}


