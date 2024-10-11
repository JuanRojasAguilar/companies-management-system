package com.backend.orderstate.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.orderstate.application.OrderStateService;
import com.backend.orderstate.domain.OrderState;

@Service
public class OrderStateImpl implements OrderStateService {

    @Autowired
    private OrderStateRepository orderStateRepository;

    @Override
    public OrderState save(OrderState orderState) {
        return orderStateRepository.save(orderState);
    }

    @Override
    public Optional<OrderState> update(Long id, OrderState orderState) {
        Optional<OrderState> orderStateDB = orderStateRepository.findById(id);
        if (orderStateDB.isPresent()) {
            OrderState orderStateToUpload = orderStateDB.orElseThrow();
            BeanUtils.copyProperties(orderState, orderStateToUpload, "id");
            return Optional.of(orderStateRepository.save(orderStateToUpload));
        }
        return Optional.empty();
    }

    @Override
    public Optional<OrderState> findById(Long id) {
        return orderStateRepository.findById(id);
    }

    @Override
    public List<OrderState> findAll() {
        return orderStateRepository.findAll();
    }

    @Override
    public Optional<OrderState> delete(Long id) {
        Optional<OrderState> orderState = orderStateRepository.findById(id);
        orderState.ifPresent(orderStateDb -> {
           orderStateRepository.delete(orderStateDb);
        });
        return orderState;
    }
    
}
