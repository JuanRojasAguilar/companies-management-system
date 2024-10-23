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
import com.backend.country.domain.CountryDto;
import com.backend.utils.enums.Status;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryRepository repository;

	@Override
	@Transactional
	public Country save(CountryDto country) {
		Country countryDb = new Country();
		BeanUtils.copyProperties(country, countryDb, country.getClass());
		countryDb.setStatus(Status.ENABLED);

		return repository.save(countryDb);
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
		Optional<Country> countryInstance = this.findById(id);
		if (countryInstance.isPresent()) {
			countryInstance.orElseThrow().setStatus(Status.DISABLED);
			return Optional.of(repository.save(countryInstance.orElseThrow()));
		}
		return Optional.empty();
	}

	@Override
	public Optional<Country> update(Long id, CountryDto country) {
		Optional<Country> countryInstance = repository.findById(id);
	if (countryInstance.isPresent()) {
			Country countryDb = countryInstance.orElseThrow();
			BeanUtils.copyProperties(country, countryDb, country.getClass());

			return Optional.of(repository.save(countryDb));
		}
		return Optional.empty();
	}
}
