package com.backend.country.infrastructure.controller;

import java.util.Optional;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.country.application.CountryService;
import com.backend.country.domain.Country;

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
	public Optional<Country> findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PutMapping("/{id}")
	@Transactional
	public Optional<Country> update(@PathVariable Long id, @RequestBody Country country) {
		return service.update(id, country);
	}

	@PostMapping
	@Transactional
	public Country save(@RequestBody Country country) {
		return service.save(country);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public boolean delete(@PathVariable Long id) {
		return service.delete(id);
	}
}
