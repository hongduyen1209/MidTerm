package com.example.midterm.service.impl;

import com.example.midterm.model.OrderDetail;
import com.example.midterm.model.OrderDetailPK;
import com.example.midterm.repository.OrderDetailRepository;
import com.example.midterm.service.OrderDetailService;
import com.example.midterm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository repository;


    @Override
    public void saveOrderDetail(OrderDetail orderDetail) {
        repository.save(orderDetail);
    }
}
