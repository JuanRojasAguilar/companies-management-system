package com.backend.orderstate.application;

import java.util.List;
import java.util.Optional;

import com.backend.orderstate.domain.OrderState;
import com.backend.orderstate.domain.OrderStateDto;

public interface OrderStateService {
    public OrderState save(OrderStateDto orderState);

    public Optional<OrderState> update(Long id, OrderStateDto orderState);

    public Optional<OrderState> findById(Long id);
    
    public List<OrderState> findAll();

    public Optional<OrderState> delete(Long id);
}