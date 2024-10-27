package com.backend.franchise.infraestructure.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.franchise.application.FranchiseService;
import com.backend.franchise.domain.Franchise;
import com.backend.franchise.domain.FranchiseDto;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {
	@Autowired
	private FranchiseService service;

	@GetMapping
	public List<Franchise> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<Franchise> findById(@PathVariable Long id) {
		Optional<Franchise> detailsOpt = service.findById(id);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.ok(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@Valid @RequestBody FranchiseDto franchise, BindingResult result,
			@PathVariable Long id) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<Franchise> detailsOpt = service.update(id, franchise);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody FranchiseDto franchise, BindingResult result) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(franchise));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Franchise> delete(@PathVariable Long id) {
		Franchise franchise = new Franchise();
		franchise.setId(id);
		Optional<Franchise> franchiseDelete = service.delete(id);
		if (franchiseDelete.isPresent()) {
			return ResponseEntity.ok(franchiseDelete.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	private ResponseEntity<?> validation(BindingResult bindingResult) {
		Map<String, String> errors = bindingResult.getFieldErrors().stream()
				.collect(Collectors.toMap(
						error -> error.getField(),
						error -> "el campo" + error.getField() + " " + error.getDefaultMessage()));

		return ResponseEntity.badRequest().body(errors);
	}
}
