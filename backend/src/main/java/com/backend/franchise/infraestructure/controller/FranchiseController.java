package com.backend.franchise.infraestructure.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.franchise.application.FranchiseService;
import com.backend.franchise.domain.Franchise;

import jakarta.validation.Valid;

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
  public List<Franchise> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    return service.findById(id)
        .map(franchise -> ResponseEntity.ok(franchise))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<?> save(@Valid @RequestBody Franchise franchise, BindingResult bindingResult) {
    return bindingResult.hasFieldErrors()
        ? validation(bindingResult)
        : ResponseEntity.status(HttpStatus.CREATED).body(service.save(franchise));
  }

  @PostMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Franchise franchise, BindingResult bindingResult) {
    return bindingResult.hasFieldErrors()
        ? validation(bindingResult)
        : service.update(id, franchise)
          .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
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
