package com.backend.country.application;

import java.util.Optional;
import java.util.Set;

import com.backend.country.domain.Country;
import com.backend.country.domain.CountryDto;

public interface CountryService {
	public Country save(CountryDto country);

	public Set<Country> findAll();

	public Optional<Country> findById(Long id);
	
	public Optional<Country> update(Long id, CountryDto country);

	public Optional<Country> delete(Long id);

}
