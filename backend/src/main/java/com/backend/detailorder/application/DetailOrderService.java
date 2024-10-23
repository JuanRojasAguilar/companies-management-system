package com.backend.detailorder.application;

import java.util.Optional;
import java.util.Set;

import com.backend.detailorder.domain.DetailOrder;
import com.backend.detailorder.domain.DetailOrderDto;

public interface DetailOrderService {
   	public Set<DetailOrder> findAll();
    public Optional<DetailOrder> findById(Long id);
    public Optional<DetailOrder> update(Long id, DetailOrderDto detailOrder);
    public DetailOrder save(DetailOrderDto detailOrder);
    public Optional<DetailOrder> delete(Long id);
}
