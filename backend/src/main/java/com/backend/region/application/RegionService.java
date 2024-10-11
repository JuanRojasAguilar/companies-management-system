package com.backend.region.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.region.domain.Region;

@Service
public interface RegionService {
    List<Region> findAll();
    Optional<Region> findById(Long id);
    Region save(Region region);
    Optional<Region> update(Long id, Region region);
    boolean delete(Long id);
}
