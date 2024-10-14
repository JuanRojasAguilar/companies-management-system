package com.backend.statusorderservice.infrastructure.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.statusorderservice.application.StatusOrderServiceService;
import com.backend.statusorderservice.domain.StatusOrderService;

@RequestMapping("/api/status-order-services")
@RestController
public class StatusOrderServiceController{
	@Autowired
	private StatusOrderServiceService service;

	@GetMapping
	@Transactional(readOnly = true)
	public Set<StatusOrderService> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public Optional<StatusOrderService> findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PutMapping("/{id}")
	@Transactional
	public Optional<StatusOrderService> update(@PathVariable Long id, @RequestBody StatusOrderService statusOrderService) {
		return service.update(id, statusOrderService);
	}

	@PostMapping
	@Transactional
	public StatusOrderService save(@RequestBody StatusOrderService statusApproval) {
		return service.save(statusApproval);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public boolean delete(@PathVariable Long id) {
		return service.delete(id);
	}
}
