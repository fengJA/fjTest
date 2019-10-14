package com.jf.shop.login.dao;

import com.jf.shop.login.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsDao {

    Goods selectGoodsById(Integer id);

    int deleteGoodsById(Integer id);

    void updateGoods(Goods goods);

    void insertGoods(Goods goods);
}
