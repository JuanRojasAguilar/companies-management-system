package com.backend.statusorderservice.infrastructure.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.statusorderservice.application.StatusOrderServiceService;
import com.backend.statusorderservice.domain.StatusOrderService;

public class StatusOrderServiceController{
	@Autowired
	private StatusOrderServiceService service;

	@GetMapping
	public Set<StatusOrderService> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Optional<StatusOrderService> findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public StatusOrderService save(@RequestBody StatusOrderService statusApproval) {
		return service.save(statusApproval);
	}
}
