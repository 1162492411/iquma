package com.iquma.service.impl;

import com.iquma.dao.ReplyMapper;
import com.iquma.pojo.Reply;
import com.iquma.service.ReplyService;
import com.iquma.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

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

    @Override
    public int selectsCount(Reply condition) {
        return this.replyMapper.selectsCount(condition);
    }

    @Override
    public List selectsByPage(int page, Reply condition) {
        return this.replyMapper.selectsByPage(PageUtil.getStart(page),condition);
    }

    @Override
    public List selectsByPageSorted(int page, Reply condition) {
        return this.replyMapper.selectsByPageSorted(PageUtil.getStart(page), condition);
    }

}
