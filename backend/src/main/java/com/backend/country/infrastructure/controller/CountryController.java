package com.backend.country.infrastructure.controller;

import java.util.Optional;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.country.application.CountryService;
import com.backend.country.domain.Country;

public class CountryController {
	@Autowired
	private CountryService service;

	@GetMapping
	public Set<Country> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Country> findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Country save(@RequestBody Country country) {
		return service.save(country);
	}
}
