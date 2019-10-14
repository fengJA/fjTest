package com.jf.shop.login.service.Impl;

import com.jf.shop.login.dao.GoodsDao;
import com.jf.shop.login.entity.Goods;
import com.jf.shop.login.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;

public class GoodsServiceImp implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public Goods selectGoodsById(Integer id) {
        return goodsDao.selectGoodsById(id);
    }

    @Override
    public int deleteGoodsById(Integer id) {
        return goodsDao.deleteGoodsById(id);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsDao.updateGoods(goods);
    }

    @Override
    public void insertGoods(Goods goods) {
        goodsDao.insertGoods(goods);
    }
}
