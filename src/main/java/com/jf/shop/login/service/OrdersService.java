package com.jf.shop.login.service;

import com.jf.shop.login.entity.Orders;

public interface OrdersService {

    Orders selectOrdersById(Integer id);

    int deleteOrdersById(Integer orderId);

    void updateOrders(Orders orders);

    void insertOrders(Orders orders);
}
