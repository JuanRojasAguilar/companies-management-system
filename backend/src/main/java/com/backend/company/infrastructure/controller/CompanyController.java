package com.backend.company.infrastructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.company.application.CompanyService;
import com.backend.company.domain.Company;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/companies")
public class CompanyController {
    @Autowired
    private CompanyService service;

    @GetMapping
    public List<Company> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Company> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Company save(@RequestBody Company company) {
        return service.save(company);
    }

    @PutMapping("/{id}")
    public Optional<Company> update(@PathVariable Long id, @RequestBody Company company) {
        return service.update(id, company);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
