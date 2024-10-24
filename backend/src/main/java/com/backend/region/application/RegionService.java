package com.backend.region.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.region.domain.Region;
import com.backend.region.domain.RegionDto;

@Service
public interface RegionService {
    List<Region> findAll();
    Optional<Region> findById(Long id);
    Region save(RegionDto region);
    Optional<Region> update(Long id, RegionDto region);
    Optional<Region> delete(Long id);
}
