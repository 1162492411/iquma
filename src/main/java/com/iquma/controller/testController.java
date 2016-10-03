package com.iquma.controller;

import com.iquma.service.RoleService;
import com.iquma.service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Mo on 2016/8/30.
 */
@Controller
@RequestMapping("/test")
public class testController {

    @Resource
    private RoleService roleService;

    @Resource
    private TopicService topicService;

    @RequestMapping("/allRoles")
    public String toShowAllRoles(Model model) {
        Map roleList = this.roleService.getAllRoles();
        for (int i = 0; i < roleList.size(); i++) {
            System.out.println("获取到的数据为" + roleList.get(i));
        }
        model.addAllAttributes(roleList);
        return "role/main";
    }


}
