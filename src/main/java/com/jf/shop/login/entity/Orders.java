package com.jf.shop.login.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Orders {

    private Integer id;
    private Integer orderId;
    private String sumPrices;
    private Integer status;
    private Date createTime;
    private Date payTime;

    public Orders() {
        super();
    }

    public Orders(Integer id) {
        this.id = id;
    }

    public Orders(Integer id, Integer orderId, String sumPrices, Integer status, Date createTime, Date payTime) {
        this.id = id;
        this.orderId = orderId;
        this.sumPrices = sumPrices;
        this.status = status;
        this.createTime = createTime;
        this.payTime = payTime;
    }
}
