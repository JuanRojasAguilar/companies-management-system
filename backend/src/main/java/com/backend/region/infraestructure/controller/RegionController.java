package com.backend.region.infraestructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.region.application.RegionService;
import com.backend.region.domain.Region;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/regions")
public class RegionController {
    @Autowired
    private RegionService service;

    @GetMapping
    public List<Region> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Region> findById(@RequestParam Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Region save(@RequestBody Region region) {
        return service.save(region);
    }
    
    @DeleteMapping("/{id}")
    public boolean delete(@RequestParam Long id) {
        return service.delete(id);
    }
}
