package com.backend.statusapproval.infrastructure.controller;

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

import com.backend.statusapproval.application.StatusApprovalService;
import com.backend.statusapproval.domain.StatusApproval;

@RequestMapping("/api/status-approval")
@RestController
public class StatusApprovalController{
	@Autowired
	private StatusApprovalService service;

	@GetMapping
	@Transactional(readOnly = true)
	public Set<StatusApproval> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public Optional<StatusApproval> findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PutMapping("/{id}")
	@Transactional
	public Optional<StatusApproval> update(@PathVariable Long id, @RequestBody StatusApproval statusApproval) {
		return service.update(id, statusApproval);
	}

	@PostMapping
	@Transactional
	public StatusApproval save(@RequestBody StatusApproval statusApproval) {
		return service.save(statusApproval);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public boolean delete(@PathVariable Long id) {
		return service.delete(id);
	}
}
