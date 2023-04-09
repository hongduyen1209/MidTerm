package com.example.midterm.service.impl;

import com.example.midterm.model.Order;
import com.example.midterm.model.User;
import com.example.midterm.repository.OrderRepository;
import com.example.midterm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public Order saveOrder(Order order) {
        order.setId(0L);
        return repository.save(order);
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = repository.findById(id);
        if(order.isPresent()){
            return order.get();
        }
        return null;
    }
}
