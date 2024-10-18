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
    public Optional<DetailOrder> update(Long id, DetailOrder detailOrder) {
        Optional<DetailOrder> detailOrderInstance = repository.findById(id);
        if (detailOrderInstance.isPresent()) {
            DetailOrder newDetailOrder = detailOrderInstance.get();
            BeanUtils.copyProperties(detailOrder, newDetailOrder);
            return Optional.of(repository.save(newDetailOrder));
        }
        return Optional.empty();
    }

    @Override
    public DetailOrder save(DetailOrder detailOrder) {
        return repository.save(detailOrder);
    }

    @Override
    public Optional<DetailOrder> delete(Long id) {
        Optional<DetailOrder> detailOrderInstance = repository.findById(id);
        if (detailOrderInstance.isPresent()) {
            repository.delete(detailOrderInstance.get());
            return Optional.of(detailOrderInstance).orElseThrow();
        }
        return Optional.empty();
    }
    
}
