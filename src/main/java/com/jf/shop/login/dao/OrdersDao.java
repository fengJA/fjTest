package com.jf.shop.login.dao;

import com.jf.shop.login.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersDao {

    Orders selectOrdersById(Integer id);

    int deleteOrdersById(Integer orderId);

    void updateOrders(Orders orders);

    void insertOrders(Orders orders);
}
