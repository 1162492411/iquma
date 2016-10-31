package com.iquma.service.impl;

import com.iquma.dao.CommentMapper;
import com.iquma.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mo on 2016/10/7.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List selectCommentsByRid(Integer rid) {
        return this.commentMapper.selectCommentsByRid(rid);
    }
}
