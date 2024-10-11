package com.backend.region.infraestructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.region.application.RegionService;
import com.backend.region.domain.Region;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionRepository repository;

    @Override
    public List<Region> findAll() {
        return (List<Region>) repository.findAll();
    }

    @Override
    public Optional<Region> findById(Long id) {
        Optional<Region> regionInstance = repository.findById(id);
        if (regionInstance.isPresent()) {
            return Optional.of(regionInstance.get());
        }
        return Optional.empty();
    }

    @Override
    public Region save(Region region) {
        return repository.save(region);
    }

    @Override
    public Optional<Region> update(Long id, Region region) {
        Optional<Region> regionInstance = repository.findById(id);
        if (regionInstance.isPresent()) {
            Region newRegion = regionInstance.get();
            BeanUtils.copyProperties(region, newRegion);
            return Optional.of(repository.save(newRegion));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        Optional<Region> regionInstance = repository.findById(id);
        if (regionInstance.isPresent()) {
            repository.delete(regionInstance.get());
            return true;
        }
        return false;
    }
    
}
