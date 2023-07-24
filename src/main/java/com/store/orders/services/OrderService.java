package com.store.orders.services;

import com.store.orders.models.OrderModel;
import com.store.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public OrderModel save(OrderModel orderModel) {
        return orderRepository.save(orderModel);
    }

    public Optional<OrderModel> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Page<OrderModel> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public void delete(OrderModel orderModel) {
        orderRepository.delete(orderModel);
    }
}
