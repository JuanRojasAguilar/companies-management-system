package com.backend.city.repository.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.city.application.CityService;
import com.backend.city.domain.City;

@RequestMapping("/api/cities")
@RestController
public class CityController {
  @Autowired
  private CityService service;

  @GetMapping
  @Transactional(readOnly = true)
  public List<City> findAll() {
    return service.findAll();
  }
  
  @GetMapping("/{id}")
  @Transactional(readOnly = true)
  public Optional<City> findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping 
  @Transactional
  public City save(@RequestBody City city) {
    return service.save(city);
  }

  @PostMapping("/{id}")
  @Transactional
  public Optional<City> update(@PathVariable Long id, @RequestBody City city) {
    return service.update(id, city);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public boolean delete(@PathVariable Long id) {
    return service.delete(id);
  }
}
