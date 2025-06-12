package com.example.laptopshop.service;

import java.util.List;

import com.example.laptopshop.entity.Orders;

public interface OrdersService {
    Orders placOrders (int userId);
    Orders getOrders(int userId);
    List<Orders> getAllOrders();
}
