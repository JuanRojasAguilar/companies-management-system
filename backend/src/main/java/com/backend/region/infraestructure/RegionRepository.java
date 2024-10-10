package com.backend.region.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.region.domain.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {}
