package com.backend.detailorder.application;

import java.util.Optional;
import java.util.Set;

import com.backend.detailorder.domain.DetailOrder;

public interface DetailOrderService {
   	public Set<DetailOrder> findAll();
    public Optional<DetailOrder> findById(Long id);
    public Optional<DetailOrder> update(Long id, DetailOrder detailOrder);
    public DetailOrder save(DetailOrder detailOrder);
    public Optional<DetailOrder> delete(Long id);
}
