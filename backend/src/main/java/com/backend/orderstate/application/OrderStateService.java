package com.backend.orderstate.application;

import java.util.List;
import java.util.Optional;

import com.backend.orderstate.domain.OrderState;

public interface OrderStateService {
    public OrderState save(OrderState orderState);

    public Optional<OrderState> update(Long id, OrderState orderState);

    public Optional<OrderState> findById(Long id);
    
    public List<OrderState> findAll();

    public Optional<OrderState> delete(Long id);
}