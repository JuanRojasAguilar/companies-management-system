package com.backend.country.infrastructure.repository;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.backend.country.domain.Country;
import com.backend.country.infrastructure.CountryRepository;

public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryRepository repository;

	@Override
	@Transactional
	public Country save(Country country) {
		return repository.save(country);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Country> findAll() {
		Set<Country> countries = new LinkedHashSet<>((List<Country>) repository.findAll());
		return countries;

	}
	@Override
	public Optional<Country> findById(Long id) {
		return repository.findById(id); 
	}
	@Override
	public boolean delete(Long id) {
		try {
			Country countryInstance = this.findById(id).get();
			repository.delete(countryInstance);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
