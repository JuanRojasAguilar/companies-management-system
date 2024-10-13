package com.backend.orderwork.infrastructure.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.orderwork.application.OrderWorkService;
import com.backend.orderwork.domain.OrderWork;

public class OrderWorkController{
	@Autowired
	private OrderWorkService service;

	@GetMapping
	@Transactional(readOnly = true)
	public Set<OrderWork> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public Optional<OrderWork> findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PutMapping("/{id}")
	@Transactional
	public Optional<OrderWork> update(@PathVariable Long id, @RequestBody OrderWork orderWork) {
		return service.update(id, orderWork);
	}

	@PostMapping
	@Transactional
	public OrderWork save(@RequestBody OrderWork orderWork) {
		return service.save(orderWork);
	}

	@DeleteMapping
	@Transactional
	public boolean delete(@PathVariable Long id) {
		return service.delete(id);
	}
}
