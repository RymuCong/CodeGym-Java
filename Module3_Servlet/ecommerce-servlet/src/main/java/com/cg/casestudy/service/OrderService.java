package com.cg.casestudy.service;

import com.cg.casestudy.entity.Order;

import java.util.List;

public interface OrderService {

    int insertOrder(Order order);

    List<Order> getAllOrderByUserId(int uid);

    Order getOrderById(int id);

    List<Order> getAllOrder();

    void updateOrderStatus(int oid, String status);
}
