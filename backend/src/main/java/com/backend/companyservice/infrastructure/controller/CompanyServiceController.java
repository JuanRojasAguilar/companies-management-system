package com.backend.companyservice.infrastructure.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.companyservice.application.CompanyServiceService;
import com.backend.companyservice.domain.CompanyService;
import com.backend.companyservice.domain.CompanyServiceId;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/company-services")
public class CompanyServiceController {
    @Autowired
    private CompanyServiceService companyServiceService;

    @GetMapping()
    public List<CompanyService> getAll() {
        return companyServiceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable CompanyServiceId id) {
        return companyServiceService.findById(id)
            .map(companyServiceNoOp -> ResponseEntity.ok(companyServiceNoOp))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CompanyService companyService, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : ResponseEntity.status(HttpStatus.CREATED).body(companyServiceService.save(companyService));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable CompanyServiceId id, @Valid @RequestBody CompanyService companyService, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : companyServiceService.update(id, companyService)
                .map(updateOrderResponse -> ResponseEntity.status(HttpStatus.OK).body(updateOrderResponse))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable CompanyServiceId id) {    
        return companyServiceService.delete(id)
            .map(companyServiceNoOp -> ResponseEntity.ok(companyServiceNoOp))
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
