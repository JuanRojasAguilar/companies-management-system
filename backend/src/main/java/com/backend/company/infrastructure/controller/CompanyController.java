package com.backend.company.infrastructure.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.company.application.CompanyService;
import com.backend.company.domain.Company;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping()
    public List<Company> getAll() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return companyService.findById(id)
            .map(companyNoOp -> ResponseEntity.ok(companyNoOp))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody Company company, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(company));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Company company, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : companyService.update(id, company)
                .map(updateOrderResponse -> ResponseEntity.status(HttpStatus.OK).body(updateOrderResponse))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {    
        return companyService.delete(id)
            .map(companyNoOp -> ResponseEntity.ok(companyNoOp))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    private ResponseEntity<?> validation(BindingResult bindingResult) {
        Map<String, String> errors = bindingResult.getFieldErrors().stream()
            .collect(Collectors.toMap(
                error -> error.getField(),
                error -> "el campo" + error.getField() + " " + error.getDefaultMessage()
            ));

        return ResponseEntity.badRequest().body(errors);
    }
}
