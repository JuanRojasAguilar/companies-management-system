package com.backend.city.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.city.application.CityService;
import com.backend.city.domain.City;

@Service
public class CityServiceImpl implements CityService {
  @Autowired
  private CityRepository repository;

  @Override
  @Transactional(readOnly = true)
  public List<City> findAll() {
    return repository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<City> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public Optional<City> update(Long id, City city) {
    Optional<City> cityInstance = this.findById(id);
    if (cityInstance.isPresent()) {
      return Optional.of(cityInstance.get());
    }
    return Optional.empty();
  }

  @Override
  @Transactional
  public City save(City city) {
    return repository.save(city);
  }

  @Override
  @Transactional
  public Optional<City> delete(Long id) {
    Optional<City> cityInstance = this.findById(id);
    if (cityInstance.isPresent()) {
      repository.delete(cityInstance.get());
      return Optional.of(cityInstance.get());
    }
    return Optional.empty();
  }
}
