package com.iquma.service.impl;

import com.iquma.dao.FavoriteMapper;
import com.iquma.pojo.Favorite;
import com.iquma.service.FavoriteService;
import com.iquma.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by Mo on 2016/12/7.
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public boolean insert(Favorite record) {
        return this.favoriteMapper.insertSelective(record) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return this.favoriteMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Favorite selectByCondition(Favorite condition) {
        return this.favoriteMapper.selectByCondition(condition);
    }

    @Override
    public List selectsByPage(int page, Favorite condition) {
        return this.favoriteMapper.selectsByPage(PageUtil.getStart(page), condition);
    }

    @Override
    public int selectsCount(Favorite condition) {
        return this.favoriteMapper.selectsCount(condition);
    }

}
