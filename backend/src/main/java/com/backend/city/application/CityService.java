package com.backend.city.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.city.domain.City;

@Service
public interface CityService {
    List<City> findAll();
    Optional<City> findById(Long id);
    Optional<City> update(Long id, City city);
    City save(City city);
    boolean delete(Long id);
}
