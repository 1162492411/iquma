package com.iquma.controller;

import com.iquma.pojo.Tag;
import com.iquma.service.RoleService;
import com.iquma.service.SectionService;
import com.iquma.service.TagService;
import com.iquma.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("api")
public class apiController {

    @Autowired
    private SectionService sectionService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("getAllSections")
    public @ResponseBody
    List getAllSections(){
        return sectionService.selectAll();
    }

    @RequestMapping("getAllTypes")
    public @ResponseBody List getAllTypes(){
        return typeService.selectAll();
    }

    @RequestMapping("getTagsByPid/{pid}")
    public @ResponseBody List getTags(@PathVariable String pid,Tag condition){
        condition.setPid(Byte.parseByte(pid));
        return tagService.selectTagsByPid(condition);
    }

    @RequestMapping("getAllRoles")
    public @ResponseBody List getAllRoles(){
        return roleService.selectAll();
    }

}


