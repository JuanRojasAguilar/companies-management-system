package com.backend.city.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.city.application.CityService;
import com.backend.city.domain.City;
import com.backend.city.domain.CityDto;
import com.backend.region.domain.Region;
import com.backend.utils.enums.Status;

@Service
public class CityServiceImpl implements CityService {
  @Autowired
  private CityRepository repository;

  @Override
  @Transactional(readOnly = true)
  public List<City> findAll() {
    return repository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<City> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public Optional<City> update(Long id, CityDto city) {
    Optional<City> cityInstance = this.findById(id);
    if (cityInstance.isPresent()) {
      City cityDb = cityInstance.orElseThrow();
      cityDb.setName(city.getName());

      Region region = new Region();
      region.setId(city.getRegionId());
      cityDb.setRegion(region);
      return Optional.of(repository.save(cityDb));
    }
    return Optional.empty();
  }

  @Override
  @Transactional
  public City save(CityDto city) {
    City cityDb = new City();
    cityDb.setName(city.getName());
    cityDb.setStatus(Status.ENABLED);

    Region region = new Region();
    region.setId(city.getRegionId());
    cityDb.setRegion(region);

    return repository.save(cityDb);
  }

  @Override
  @Transactional
  public Optional<City> delete(Long id) {
    Optional<City> cityInstance = this.findById(id);
    if (cityInstance.isPresent()) {
      cityInstance.orElseThrow().setStatus(Status.DISABLED);
      return Optional.of(repository.save(cityInstance.orElseThrow()));
    }
    return Optional.empty();
  }
}
