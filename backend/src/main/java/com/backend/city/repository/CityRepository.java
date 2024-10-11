package com.backend.city.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.city.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {}
