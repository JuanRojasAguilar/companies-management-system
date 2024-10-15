package com.backend.orderwork.infrastructure;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.backend.orderwork.application.OrderWorkService;
import com.backend.orderwork.domain.OrderWork;

public class OrderWorkServiceImpl implements OrderWorkService {
	@Autowired
	private OrderWorkRepository repository;

	@Override
	public OrderWork save(OrderWork orderWork) {
		return repository.save(orderWork);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<OrderWork> findAll() {
		Set<OrderWork> orderWorks = new LinkedHashSet<>((List<OrderWork>) repository.findAll());
		return orderWorks;
	}

	@Override
	public Optional<OrderWork> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<OrderWork> delete(Long id) {
		try {
			OrderWork orderWorkInstance = this.findById(id).get();
			repository.delete(orderWorkInstance);
			return Optional.of(orderWorkInstance);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<OrderWork> update(Long id, OrderWork orderWork) {
		Optional<OrderWork> orderWorkInstance = repository.findById(id);
		if (orderWorkInstance.isPresent()) {
			OrderWork newOrderWork = orderWorkInstance.get();
			BeanUtils.copyProperties(orderWork, newOrderWork);
			return Optional.of(repository.save(newOrderWork));
		}
		return Optional.empty();
	}
}
