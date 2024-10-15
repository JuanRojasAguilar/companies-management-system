package com.backend.statusapproval.infrastructure.controller;

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

import com.backend.statusapproval.application.StatusApprovalService;
import com.backend.statusapproval.domain.StatusApproval;

import jakarta.validation.Valid;

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
	public ResponseEntity<StatusApproval> findById(@PathVariable Long id) {
		Optional<StatusApproval> detailsOpt = service.findById(id);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.ok(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@Valid @RequestBody StatusApproval statusApproval, BindingResult result, @PathVariable Long id ) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<StatusApproval> detailsOpt = service.update(id, statusApproval);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody StatusApproval statusApproval, BindingResult result) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(statusApproval));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<StatusApproval> delete(@PathVariable Long id) {
		StatusApproval statusApproval = new StatusApproval();
		statusApproval.setId(id);
		Optional<StatusApproval> statusApprovalDelete = service.delete(id);
		if (statusApprovalDelete.isPresent()) {
			return ResponseEntity.ok(statusApprovalDelete.orElseThrow());
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
