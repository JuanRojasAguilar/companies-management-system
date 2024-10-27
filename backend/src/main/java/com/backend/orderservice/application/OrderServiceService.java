package com.backend.orderservice.application;

import java.util.List;
import java.util.Optional;

import com.backend.orderservice.domain.OrderService;
import com.backend.orderservice.domain.OrderServiceDto;

public interface OrderServiceService {
    public OrderService save(OrderServiceDto orderService);

    public Optional<OrderService> update(Long id, OrderServiceDto orderService);

    public Optional<OrderService> findById(Long id);
    
    public List<OrderService> findAll();

    public Optional<OrderService> delete(Long id);
}
