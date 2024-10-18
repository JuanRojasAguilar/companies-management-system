package com.backend.country.infrastructure;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.country.application.CountryService;
import com.backend.country.domain.Country;

@Service
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
	public Optional<Country> delete(Long id) {
		try {
			Country countryInstance = this.findById(id).get();
			repository.delete(countryInstance);
			return Optional.of(countryInstance);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Country> update(Long id, Country country) {
		Optional<Country> countryInstance = repository.findById(id);
	if (countryInstance.isPresent()) {
			Country newCountry = countryInstance.get();
			BeanUtils.copyProperties(country, newCountry);
			return Optional.of(repository.save(newCountry));
		}
		return Optional.empty();
	}
}
