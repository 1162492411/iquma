package com.iquma.pojo;

import java.util.List;

/**
 * Created by Mo on 2016/10/7.
 */
public class CommentList {

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    private List<Comment> commentList;
}
