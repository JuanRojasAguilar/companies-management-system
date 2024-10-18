package com.backend.service.infrastructure.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.service.application.ServiceService;
import com.backend.service.domain.Service;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("api/services")
@RestController
public class ServiceController {
    @Autowired
    private ServiceService service;

    @GetMapping
    public List<Service> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@PathVariable Long id, @Valid @RequestBody Service newService,
            BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
                ? validation(bindingResult)
                : ResponseEntity.status(HttpStatus.CREATED).body(service.save(newService));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Service newService,
            BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
                ? validation(bindingResult)
                : service.update(id, newService)
                        .map(response -> ResponseEntity.ok(response))
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id)
                .map(response -> ResponseEntity.ok(response))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<?> validation(BindingResult bindingResult) {
        Map<String, String> errors = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> "el campo" + error.getField() + " " + error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
