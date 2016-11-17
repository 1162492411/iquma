package com.iquma.service.impl;

import com.iquma.dao.ReplyMapper;
import com.iquma.pojo.Reply;
import com.iquma.service.ReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mo on 2016/10/4.
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Resource
    private ReplyMapper replyMapper;

    @Override
    public List selectByCondition(Reply condition) {
        return this.replyMapper.selectByCondition(condition);
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
        return this.replyMapper.updateByPrimaryKeyWithBLOBs(record) > 0;
    }

    @Override
    public Boolean changeStatus(Integer id) {
        return this.replyMapper.changeStatusByPrimaryKey(id);
    }
}
