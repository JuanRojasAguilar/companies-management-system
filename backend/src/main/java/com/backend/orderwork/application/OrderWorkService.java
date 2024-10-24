package com.backend.orderwork.application;

import java.util.Optional;
import java.util.Set;

import com.backend.orderwork.domain.OrderWork;
import com.backend.orderwork.domain.OrderWorkDto;

public interface OrderWorkService {
	public OrderWork save(OrderWorkDto orderWork);

	public Set<OrderWork> findAll();

	public Optional<OrderWork> findById(Long id);

	public Optional<OrderWork> update(Long id, OrderWorkDto orderWork);

	public Optional<OrderWork> delete(Long id);
	 
}
