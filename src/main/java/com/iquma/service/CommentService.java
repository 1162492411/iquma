package com.iquma.service;

import java.util.List;

/**
 * Created by Mo on 2016/10/7.
 */
public interface CommentService {

    List selectCommentsByRid(Integer rid);//查找某回复的所有评论;

}
