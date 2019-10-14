package com.jf.shop.login.entity;

import lombok.Data;

@Data
public class Goods {

    private Integer id;
    private String goodsName;
    private String prices;
    private Integer stock;
    private String style;

    public Goods() {
        super();
    }

    public Goods(Integer id) {
        this.id = id;
    }

    public Goods(Integer id, String goodsName, String prices, Integer stock, String style) {
        this.id = id;
        this.goodsName = goodsName;
        this.prices = prices;
        this.stock = stock;
        this.style = style;
    }
}
