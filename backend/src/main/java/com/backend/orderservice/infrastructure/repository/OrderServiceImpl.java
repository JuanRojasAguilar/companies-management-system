package com.backend.orderservice.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.orderservice.application.OrderServiceService;
import com.backend.orderservice.domain.OrderService;

@Service
public class OrderServiceImpl implements OrderServiceService {

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Override
    public OrderService save(OrderService orderService) {
        return orderServiceRepository.save(orderService);
    }

    @Override
    public Optional<OrderService> update(Long id, OrderService orderService) {
        Optional<OrderService> orderServiceDB = orderServiceRepository.findById(id);
        if (orderServiceDB.isPresent()) {
            OrderService orderServiceToUpload = orderServiceDB.orElseThrow();
            BeanUtils.copyProperties(orderService, orderServiceToUpload, "id");
            return Optional.of(orderServiceRepository.save(orderServiceToUpload));
        }
        return Optional.empty();
    }

    @Override
    public Optional<OrderService> findById(Long id) {
        return orderServiceRepository.findById(id);
    }

    @Override
    public List<OrderService> findAll() {
        return orderServiceRepository.findAll();
    }

    @Override
    public Optional<OrderService> delete(Long id) {
        Optional<OrderService> orderService = orderServiceRepository.findById(id);
        orderService.ifPresent(orderServiceDb -> {
           orderServiceRepository.delete(orderServiceDb);
        });
        return orderService;
    }
    
}
