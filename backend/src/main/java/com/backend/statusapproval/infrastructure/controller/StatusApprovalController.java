package com.backend.statusapproval.infrastructure.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.statusapproval.application.StatusApprovalService;
import com.backend.statusapproval.domain.StatusApproval;

public class StatusApprovalController{
	@Autowired
	private StatusApprovalService service;

	@GetMapping
	public Set<StatusApproval> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Optional<StatusApproval> findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public StatusApproval save(@RequestBody StatusApproval statusApproval) {
		return service.save(statusApproval);
	}
}
