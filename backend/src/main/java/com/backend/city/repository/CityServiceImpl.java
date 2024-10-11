package com.backend.city.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.city.application.CityService;
import com.backend.city.domain.City;

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
  public boolean delete(Long id) {
    Optional<City> cityInstance = this.findById(id);
    if (cityInstance.isPresent()) {
      repository.delete(cityInstance.get());
      return true;
    }
    return false;
  }
}
