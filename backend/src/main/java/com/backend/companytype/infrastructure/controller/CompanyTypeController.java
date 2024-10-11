package com.backend.companytype.infrastructure.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.companytype.application.CompanyTypeService;
import com.backend.companytype.domain.CompanyType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/companies/types")
public class CompanyTypeController {
    @Autowired
    private CompanyTypeService service;

    @GetMapping
    @Transactional(readOnly = true)
    public Set<CompanyType> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public Optional<CompanyType> findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    @Transactional
    public CompanyType save(@RequestBody CompanyType companyType) {
        return service.save(companyType);
    }
}
