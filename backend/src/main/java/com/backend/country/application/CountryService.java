package com.backend.country.application;

import java.util.Optional;
import java.util.Set;

import com.backend.country.domain.Country;

public interface CountryService {
	public Country save(Country country);

	public Set<Country> findAll();

	public Optional<Country> findById(Long id);

	public boolean delete(Long id);

}
