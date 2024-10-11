package com.backend.franchise.infraestructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.franchise.application.FranchiseService;
import com.backend.franchise.domain.Franchise;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {
    @Autowired
    private FranchiseService service;

    
  @GetMapping
  @Transactional(readOnly = true)
  public List<Franchise> findAll() {
    return service.findAll();
  }
  
  @GetMapping("/{id}")
  @Transactional(readOnly = true)
  public Optional<Franchise> findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping 
  @Transactional
  public Franchise save(@RequestBody Franchise franchise) {
    return service.save(franchise);
  }

  @PostMapping("/{id}")
  @Transactional
  public Optional<Franchise> update(@PathVariable Long id, @RequestBody Franchise franchise) {
    return service.update(id, franchise);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public boolean delete(@PathVariable Long id) {
    return service.delete(id);
  }
    
}
