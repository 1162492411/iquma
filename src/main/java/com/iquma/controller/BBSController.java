package com.iquma.controller;

import com.iquma.pojo.Tag;
import com.iquma.pojo.Topic;
import com.iquma.service.TagService;
import com.iquma.service.TopicService;
import com.iquma.utils.ENUMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class BBSController {


    @Autowired
    private TagService tagService;
    private TopicService topicService;
    private static final byte HIGH_RATE_COUNT = 8;




}

