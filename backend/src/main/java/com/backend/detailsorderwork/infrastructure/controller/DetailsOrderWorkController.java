package com.backend.detailsorderwork.infrastructure.controller;

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

import com.backend.detailsorderwork.application.DetailsOrderWorkService;
import com.backend.detailsorderwork.domain.DetailsOrderWork;

import jakarta.validation.Valid;

@RequestMapping("/api/orders/details")
@RestController
public class DetailsOrderWorkController{
	@Autowired
	private DetailsOrderWorkService service;

	@GetMapping
	@Transactional(readOnly = true)
	public Set<DetailsOrderWork> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<DetailsOrderWork> findById(@PathVariable Long id) {
		Optional<DetailsOrderWork> detailsOpt = service.findById(id);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.ok(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@Valid @RequestBody DetailsOrderWork detailsOrderWork, BindingResult result, @PathVariable Long id ) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<DetailsOrderWork> detailsOpt = service.update(id, detailsOrderWork);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody DetailsOrderWork orderWork, BindingResult result) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(orderWork));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DetailsOrderWork> delete(@PathVariable Long id) {
		DetailsOrderWork detailsOrderWork = new DetailsOrderWork();
		detailsOrderWork.setId(id);
		Optional<DetailsOrderWork> detailsOrderWorkDelete = service.delete(id);
		if (detailsOrderWorkDelete.isPresent()) {
			return ResponseEntity.ok(detailsOrderWorkDelete.orElseThrow());
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
