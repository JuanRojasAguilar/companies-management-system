package com.backend.statusorderservice.infrastructure.controller;

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

import com.backend.statusorderservice.application.StatusOrderServiceService;
import com.backend.statusorderservice.domain.StatusOrderService;
import com.backend.statusorderservice.domain.StatusOrderServiceDto;

import jakarta.validation.Valid;

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
	public ResponseEntity<StatusOrderService> findById(@PathVariable Long id) {
		Optional<StatusOrderService> detailsOpt = service.findById(id);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.ok(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@Valid @RequestBody StatusOrderServiceDto statusOrderService, BindingResult result, @PathVariable Long id ) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<StatusOrderService> detailsOpt = service.update(id, statusOrderService);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody StatusOrderServiceDto statusOrderService, BindingResult result) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(statusOrderService));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<StatusOrderService> delete(@PathVariable Long id) {
		StatusOrderService statusOrderService = new StatusOrderService();
		statusOrderService.setId(id);
		Optional<StatusOrderService> statusOrderServiceDelete = service.delete(id);
		if (statusOrderServiceDelete.isPresent()) {
			return ResponseEntity.ok(statusOrderServiceDelete.orElseThrow());
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
