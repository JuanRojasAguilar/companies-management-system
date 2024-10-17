package com.backend.orderwork.application;

import java.util.Optional;
import java.util.Set;

import com.backend.orderwork.domain.OrderWork;

public interface OrderWorkService {
	public OrderWork save(OrderWork orderWork);

	public Set<OrderWork> findAll();

	public Optional<OrderWork> findById(Long id);

	public Optional<OrderWork> update(Long id, OrderWork orderWork);

	public Optional<OrderWork> delete(Long id);
	 
}
