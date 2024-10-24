package com.backend.orderwork.infrastructure;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.backend.orderservice.domain.OrderService;
import com.backend.orderwork.application.OrderWorkService;
import com.backend.orderwork.domain.OrderWork;
import com.backend.orderwork.domain.OrderWorkDto;
import com.backend.user.domain.User;
import com.backend.utils.enums.Status;

public class OrderWorkServiceImpl implements OrderWorkService {
	@Autowired
	private OrderWorkRepository repository;

	@Override
	public OrderWork save(OrderWorkDto orderWork) {
		OrderWork orderWorkDb = new OrderWork();
		BeanUtils.copyProperties(orderWork, orderWorkDb, orderWork.getClass());
		orderWorkDb.setStatus(Status.ENABLED);

		User employee = new User();
		employee.setId(orderWork.getEmployeeId());
		orderWorkDb.setEmployee(employee);

		OrderService orderService = new OrderService();
		orderService.setId(orderWork.getOrderServiceId());
		orderWorkDb.setOrderService(orderService);

		return repository.save(orderWorkDb);
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
	public Optional<OrderWork> update(Long id, OrderWorkDto orderWork) {
		Optional<OrderWork> orderWorkInstance = repository.findById(id);
		if (orderWorkInstance.isPresent()) {
			OrderWork orderWorkDb = new OrderWork();
			BeanUtils.copyProperties(orderWork, orderWorkDb, orderWork.getClass());

			User employee = new User();
			employee.setId(orderWork.getEmployeeId());
			orderWorkDb.setEmployee(employee);

			OrderService orderService = new OrderService();
			orderService.setId(orderWork.getOrderServiceId());
			orderWorkDb.setOrderService(orderService);

			return Optional.of(repository.save(orderWorkDb));
		}
		return Optional.empty();
	}
}
