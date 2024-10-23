package com.backend.detailorder.infraestructure;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.detailorder.application.DetailOrderService;
import com.backend.detailorder.domain.DetailOrder;
import com.backend.detailorder.domain.DetailOrderDto;
import com.backend.orderservice.domain.OrderService;
import com.backend.utils.enums.Status;

@Service
public class DetailOrderServiceImpl implements DetailOrderService {
    @Autowired
    private DetailOrderRepository repository;

    @Override
    public Set<DetailOrder> findAll() {
        Set<DetailOrder> types = new LinkedHashSet<>((List<DetailOrder>) repository.findAll());
        return types;
    }

    @Override
    public Optional<DetailOrder> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<DetailOrder> update(Long id, DetailOrderDto detailOrder) {
        Optional<DetailOrder> detailOrderInstance = repository.findById(id);
        if (detailOrderInstance.isPresent()) {
            DetailOrder detailOrderDb = detailOrderInstance.orElseThrow();
            BeanUtils.copyProperties(detailOrder, detailOrderDb, detailOrder.getClass());

            com.backend.service.domain.Service service = new com.backend.service.domain.Service();
            service.setId(detailOrder.getServiceId());
            detailOrderDb.setService(service);

            OrderService orderService = new OrderService();
            orderService.setId(detailOrder.getServiceId());
            detailOrderDb.setService(service);

            return Optional.of(repository.save(detailOrderDb));
        }
        return Optional.empty();
    }

    @Override
    public DetailOrder save(DetailOrderDto detailOrder) {
        DetailOrder detailOrderDb = new DetailOrder();
        BeanUtils.copyProperties(detailOrder, detailOrderDb, detailOrder.getClass());
        detailOrderDb.setStatus(Status.DISABLED);

        com.backend.service.domain.Service service = new com.backend.service.domain.Service();
        service.setId(detailOrder.getServiceId());
        detailOrderDb.setService(service);

        OrderService orderService = new OrderService();
        orderService.setId(detailOrder.getServiceId());
        detailOrderDb.setService(service);

        return repository.save(detailOrderDb);
    }

    @Override
    public Optional<DetailOrder> delete(Long id) {
        Optional<DetailOrder> detailOrderInstance = repository.findById(id);
        if (detailOrderInstance.isPresent()) {
            detailOrderInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(repository.save(detailOrderInstance.orElseThrow()));
        }
        return Optional.empty();
    }
    
}
