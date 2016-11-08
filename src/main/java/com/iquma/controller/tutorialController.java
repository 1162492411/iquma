package com.iquma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mo on 2016/10/30.
 */
@Controller
@RequestMapping("tutorials")
public class tutorialController {

    @RequestMapping()
    public String toTutorials(){
        return "tutorials/tutorials";
    }

    @RequestMapping("tutorial")
    public void toTutorial() { }

    @RequestMapping("tutorial/update")
    public String toUpdateTutorial(){
        return "tutorials/update";
    }


}
