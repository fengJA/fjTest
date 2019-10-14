package com.jf.shop.login.service;

import com.jf.shop.login.entity.Goods;

public interface GoodsService {

    Goods selectGoodsById(Integer id);

    int deleteGoodsById(Integer id);

    void updateGoods(Goods goods);

    void insertGoods(Goods goods);
}
