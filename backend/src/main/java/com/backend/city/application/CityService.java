package com.backend.city.application;

import java.util.List;
import java.util.Optional;

import com.backend.city.domain.City;
import com.backend.city.domain.CityDto;

public interface CityService {
    List<City> findAll();

    Optional<City> findById(Long id);

    Optional<City> update(Long id, CityDto city);

    City save(CityDto city);

    Optional<City> delete(Long id);
}
