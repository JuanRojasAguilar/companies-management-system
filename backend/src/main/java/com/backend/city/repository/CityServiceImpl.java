package com.backend.city.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.city.application.CityService;
import com.backend.city.domain.City;

@Service
public class CityServiceImpl implements CityService {
  @Autowired
  private CityRepository repository;

  @Override
  public List<City> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<City> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  public Optional<City> update(Long id, City city) {
    Optional<City> cityInstance = this.findById(id);
    if (cityInstance.isPresent()) {
      return Optional.of(cityInstance.get());
    }
    return Optional.empty();
  }

  @Override
  public City save(City city) {
    return repository.save(city);
  }

  @Override
  public Optional<City> delete(Long id) {
    try {
	  City cityInstance = this.findById(id).get();
      repository.delete(cityInstance);
      return Optional.of(cityInstance);
    } catch (Exception e) {
    	return Optional.empty();
    }
  }
}
