package com.iquma.controller;

import com.iquma.pojo.Favorite;
import com.iquma.pojo.Operation;
import com.iquma.pojo.Tag;
import com.iquma.service.*;
import com.iquma.utils.GsonUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 公共API
 */
@Controller
@RequestMapping("api")
public class APIController {

    @Autowired
    private TagService tagService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private OperationService operationService;

    /**
     * 获取所有一级分类标签
     * @return 所有一级分类的标签
      */
    @RequestMapping("getFirstTags")
    public @ResponseBody String getFirstTags(){
        return GsonUtil.toJson(tagService.selectsFirstTag());
    }

    /**
     * 获取分类所属的子标签及该分类
     * @param id 分类的名称
     * @return 分类所属的子标签及该分类
     */
    @RequestMapping("getChildrenTags/{id}")
    public @ResponseBody String getTags(@PathVariable Byte id,Tag condition){
        return GsonUtil.toJson(tagService.selectsChildren(condition));
    }

    /**
     * 检测主贴是否被用户收藏
     * @param condition 包含主贴ID和用户ID的条件参数
     * @return true/false
     */
    @RequestMapping(value = "getIsFavorite" , method = RequestMethod.POST)
    public @ResponseBody Boolean checkIsFavorite(@RequestBody Favorite condition){
        condition.setUid(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        return null != this.favoriteService.selectByCondition(condition);
    }

    /**
     * 查询用户对主贴的投票信息
     * @param condition 包含回复ID和用户ID的条件参数
     * @return like/hate/null
     */
    @RequestMapping(value = "getTopicRateInfo", method = RequestMethod.POST)
    public @ResponseBody String getTopicRateInfo(@RequestBody Operation condition){
        condition.setUid(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        String action = operationService.selectsTopicRateInfo(condition);
        return action == null? "null" : action.substring(action.length()-4);
    }

    /**
     * 查询用户对回复的投票信息
     * @param condition 包含回复ID和用户ID的条件参数
     * @return like/hate/null
     */
    @RequestMapping(value = "getReplyRateInfo", method = RequestMethod.POST)
    public @ResponseBody String getReplyRateInfo(@RequestBody Operation condition){
        condition.setUid(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid")));
        String action = operationService.selectsReplyRateInfo(condition);
        return action == null? "null" : action.substring(action.length()-4);
    }

}


