package com.backend.region.infraestructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.country.domain.Country;
import com.backend.region.application.RegionService;
import com.backend.region.domain.Region;
import com.backend.region.domain.RegionDto;
import com.backend.utils.enums.Status;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAll() {
        return (List<Region>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Region> findById(Long id) {
        Optional<Region> regionInstance = repository.findById(id);
        if (regionInstance.isPresent()) {
            return Optional.of(regionInstance.get());
        }
        return Optional.empty();
    }

    @Override
    public Region save(RegionDto region) {
        Region regionDb = new Region();
        BeanUtils.copyProperties(region, regionDb, region.getClass());
        regionDb.setStatus(Status.ENABLED);

        Country country = new Country();
        country.setId(region.getCountryId());
        regionDb.setCountry(country);

        return repository.save(regionDb);
    }

    @Override
    public Optional<Region> update(Long id, RegionDto region) {
        Optional<Region> regionInstance = repository.findById(id);
        if (regionInstance.isPresent()) {
            Region regionDb = new Region();
            BeanUtils.copyProperties(region, regionDb, region.getClass());

            Country country = new Country();
            country.setId(region.getCountryId());
            regionDb.setCountry(country);

            return Optional.of(repository.save(regionDb));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Region> delete(Long id) {
        Optional<Region> regionInstance = repository.findById(id);
        if (regionInstance.isPresent()) {
            regionInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(repository.save(regionInstance.orElseThrow()));
        }
        return Optional.empty();
    }
    
}
