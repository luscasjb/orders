package com.store.orders.services;

import com.store.orders.dtos.OrderDTO;
import com.store.orders.enums.StatusOrder;
import com.store.orders.models.OrderModel;
import com.store.orders.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderDTO findById(Long id) {
        OrderModel orderModel= orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        orderModel.setStatusOrder(StatusOrder.UPDATED);
        orderModel.setUpdatedAt(LocalDateTime.now());
        return modelMapper.map(orderModel, OrderDTO.class);
    }

    public Page<OrderDTO> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).map(o -> modelMapper.map(o, OrderDTO.class));
    }

    public OrderDTO saveOrder(OrderDTO orderDto) {
        OrderModel orderModel = modelMapper.map(orderDto, OrderModel.class);
        orderModel.setStatusOrder(StatusOrder.CREATED);
        orderModel.setCreatedAt(LocalDateTime.now());
        orderModel.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(orderModel);
        return modelMapper.map(orderModel, OrderDTO.class);
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDto){
        OrderModel orderModel = modelMapper.map(orderDto, OrderModel.class);
        orderModel.setIdOrder(id);
        orderModel.setStatusOrder(StatusOrder.UPDATED);
        orderModel.setUpdatedAt(LocalDateTime.now());
        orderModel = orderRepository.save(orderModel);
        return modelMapper.map(orderModel, OrderDTO.class);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
