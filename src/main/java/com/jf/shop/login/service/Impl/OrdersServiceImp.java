package com.jf.shop.login.service.Impl;

import com.jf.shop.login.dao.OrdersDao;
import com.jf.shop.login.entity.Orders;
import com.jf.shop.login.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImp implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public Orders selectOrdersById(Integer id) {
        return ordersDao.selectOrdersById(id);
    }

    @Override
    public int deleteOrdersById(Integer orderId) {
        return ordersDao.deleteOrdersById(orderId);
    }

    @Override
    public void updateOrders(Orders orders) {
        ordersDao.updateOrders(orders);
    }

    @Override
    public void insertOrders(Orders orders) {
        ordersDao.insertOrders(orders);
    }
}
