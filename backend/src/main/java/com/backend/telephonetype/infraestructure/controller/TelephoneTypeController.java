package com.backend.telephonetype.infraestructure.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.telephonetype.application.TelephoneTypeService;
import com.backend.telephonetype.domain.TelephoneType;

@RestController
@RequestMapping("/api/telephones/types")
public class TelephoneTypeController {
    @Autowired
    private TelephoneTypeService service;

    @GetMapping
    @Transactional(readOnly = true)
    public Set<TelephoneType> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public Optional<TelephoneType> findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    @Transactional
    public TelephoneType save(@RequestBody TelephoneType telephoneType) {
        return service.save(telephoneType);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
