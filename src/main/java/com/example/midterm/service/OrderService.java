package com.example.midterm.service;

import com.example.midterm.model.Order;

public interface OrderService {
    Order saveOrder(Order order);
    Order findById(Long id);
}
