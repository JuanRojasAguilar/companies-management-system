package com.backend.serviceapproval.infrastructure.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.serviceapproval.application.ServiceApprovalService;
import com.backend.serviceapproval.domain.ServiceApproval;

import jakarta.validation.Valid;

@RequestMapping("/api/details-order-controllers")
@RestController
public class ServiceApprovalController{
	@Autowired
	private ServiceApprovalService service;

	@GetMapping
	@Transactional(readOnly = true)
	public Set<ServiceApproval> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<ServiceApproval> findById(@PathVariable Long id) {
		Optional<ServiceApproval> detailsOpt = service.findById(id);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.ok(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@Valid @RequestBody ServiceApproval serviceApproval, BindingResult result, @PathVariable Long id ) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<ServiceApproval> detailsOpt = service.update(id, serviceApproval);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody ServiceApproval serviceApproval, BindingResult result) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(serviceApproval));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ServiceApproval> delete(@PathVariable Long id) {
		ServiceApproval serviceApproval = new ServiceApproval();
		serviceApproval.setId(id);
		Optional<ServiceApproval> serviceApprovalDelete = service.delete(id);
		if (serviceApprovalDelete.isPresent()) {
			return ResponseEntity.ok(serviceApprovalDelete.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errors = new HashMap<>();

		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), "El campo" + err.getField() + " " + err.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errors);
	}
}
