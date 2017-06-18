package com.iquma.service.impl;

import com.github.pagehelper.PageHelper;
import com.iquma.dao.ReplyMapper;
import com.iquma.pojo.Reply;
import com.iquma.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public List selectByCondition(Reply condition) {
        return this.replyMapper.selectByCondition(condition);
    }

    @Override
    public List selectByConditionSortByTime(int page, Reply condition) {
        PageHelper.startPage(page,3);
        return this.replyMapper.selectByConditionSortByTime(condition);
    }

    @Override
    public List selectByConditionAndPage(int start, Reply condition) {
//        PageHelper.startPage(page,3);
//        return this.replyMapper.selectByCondition(condition);
        return this.replyMapper.selectByConditionAndPage(start * 3,start *3 + 3,condition);
    }

    @Override
    public Boolean rate(Reply record) {
        return this.replyMapper.updateByPrimaryKeySelective(record) > 0;
    }

    @Override
    public Boolean insert(Reply record) {
        return this.replyMapper.insertSelective(record) > 0;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return this.replyMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Boolean update(Reply record) {
        return this.replyMapper.updateByPrimaryKeySelective(record) > 0;
    }

    @Override
    public Boolean changeStatus(Integer id) {
        return this.replyMapper.changeStatusByPrimaryKey(id);
    }

    @Override
    public Boolean adopt(Integer id) {
        return this.replyMapper.adopt(id);
    }

    @Override
    public Reply selectById(Integer id) {
        return this.replyMapper.selectByPrimaryKey(id);
    }
}
