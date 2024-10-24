package com.backend.orderservice.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.orderservice.application.OrderServiceService;
import com.backend.orderservice.domain.OrderService;
import com.backend.orderservice.domain.OrderServiceDto;
import com.backend.orderstate.domain.OrderState;
import com.backend.user.domain.User;
import com.backend.utils.enums.Status;

@Service
public class OrderServiceImpl implements OrderServiceService {

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Override
    public OrderService save(OrderServiceDto orderService) {
        OrderService orderServiceDb = new OrderService();
        BeanUtils.copyProperties(orderService, orderServiceDb);
        orderServiceDb.setStatus(Status.ENABLED);

        User client = new User();
        client.setId(orderService.getClientId());
        orderServiceDb.setClient(client);

        User employee = new User();
        employee.setId(orderService.getEmployeeId());
        orderServiceDb.setClient(employee);

        OrderState orderState = new OrderState();
        orderState.setId(orderService.getOrderStateId());
        orderServiceDb.setOrderState(orderState);

        return orderServiceRepository.save(orderServiceDb);

    }   

    @Override
    public Optional<OrderService> update(Long id, OrderServiceDto orderService) {
        Optional<OrderService> orderServiceDB = orderServiceRepository.findById(id);
        if (orderServiceDB.isPresent()) {
            OrderService orderServiceDb = new OrderService();
            BeanUtils.copyProperties(orderService, orderServiceDb, orderService.getClass());

            User client = new User();
            client.setId(orderService.getClientId());
            orderServiceDb.setClient(client);

            User employee = new User();
            employee.setId(orderService.getEmployeeId());
            orderServiceDb.setClient(employee);

            OrderState orderState = new OrderState();
            orderState.setId(orderService.getOrderStateId());
            orderServiceDb.setOrderState(orderState);

            return Optional.of(orderServiceRepository.save(orderServiceDb));
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
        Optional<OrderService> orderServiceInstance = this.findById(id);
        if (orderServiceInstance.isPresent()) {
            orderServiceInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(orderServiceRepository.save(orderServiceInstance.orElseThrow()));
        }
            return Optional.empty();
    }
    
}
