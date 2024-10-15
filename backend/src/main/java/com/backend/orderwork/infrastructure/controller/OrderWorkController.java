package com.backend.orderwork.infrastructure.controller;

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

import com.backend.orderwork.application.OrderWorkService;
import com.backend.orderwork.domain.OrderWork;

import jakarta.validation.Valid;

@RequestMapping("/api/order-works")
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
	public ResponseEntity<OrderWork> findById(@PathVariable Long id) {
		Optional<OrderWork> detailsOpt = service.findById(id);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.ok(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@Valid @RequestBody OrderWork orderWork, BindingResult result, @PathVariable Long id ) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<OrderWork> detailsOpt = service.update(id, orderWork);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody OrderWork orderWork, BindingResult result) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(orderWork));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<OrderWork> delete(@PathVariable Long id) {
		OrderWork orderWork = new OrderWork();
		orderWork.setId(id);
		Optional<OrderWork> orderWorkDelete = service.delete(id);
		if (orderWorkDelete.isPresent()) {
			return ResponseEntity.ok(orderWorkDelete.orElseThrow());
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
