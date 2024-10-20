package com.backend.city.application;

import java.util.List;
import java.util.Optional;

import com.backend.city.domain.City;

public interface CityService {
    List<City> findAll();
    Optional<City> findById(Long id);
    Optional<City> update(Long id, City city);
    City save(City city);
    Optional<City> delete(Long id);
}
