package com.backend.country.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.country.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {}
