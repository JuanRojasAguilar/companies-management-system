package com.backend.country.infrastructure.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.country.application.CountryService;
import com.backend.country.domain.Country;
import com.backend.country.domain.CountryDto;

import jakarta.validation.Valid;

@RequestMapping("api/countries")
@RestController
public class CountryController {
	@Autowired
	private CountryService service;

	@GetMapping
	@Transactional(readOnly = true)
	public Set<Country> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<Country> findById(@PathVariable Long id) {
		Optional<Country> detailsOpt = service.findById(id);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.ok(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@Valid @RequestBody CountryDto country, BindingResult result, @PathVariable Long id) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<Country> detailsOpt = service.update(id, country);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody CountryDto country, BindingResult result) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(country));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Country> delete(@PathVariable Long id) {
		Country country = new Country();
		country.setId(id);
		Optional<Country> countryDelete = service.delete(id);
		if (countryDelete.isPresent()) {
			return ResponseEntity.ok(countryDelete.orElseThrow());
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
