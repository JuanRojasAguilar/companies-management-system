package com.backend.orderstate.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.orderstate.application.OrderStateService;
import com.backend.orderstate.domain.OrderState;
import com.backend.orderstate.domain.OrderStateDto;
import com.backend.utils.enums.Status;

@Service
public class OrderStateImpl implements OrderStateService {

    @Autowired
    private OrderStateRepository orderStateRepository;

    @Transactional
    @Override
    public OrderState save(OrderStateDto orderState) {
        OrderState orderStateDb = new OrderState();
        orderStateDb.setName(orderState.getName());
        orderStateDb.setStatus(Status.ENABLED);

        return orderStateRepository.save(orderStateDb);
    }

    @Transactional
    @Override
    public Optional<OrderState> update(Long id, OrderStateDto orderState) {
        Optional<OrderState> orderStateDB = orderStateRepository.findById(id);
        if (orderStateDB.isPresent()) {
            OrderState orderStateDb = new OrderState();
            orderStateDb.setName(orderState.getName());

            return Optional.of(orderStateRepository.save(orderStateDb));
        }
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<OrderState> findById(Long id) {
        return orderStateRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderState> findAll() {
        return orderStateRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<OrderState> delete(Long id) {
        Optional<OrderState> orderState = this.findById(id);
        if (orderState.isPresent()) {
            orderState.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(orderStateRepository.save(orderState.orElseThrow()));
        }
            return Optional.empty();
    }
    
}
