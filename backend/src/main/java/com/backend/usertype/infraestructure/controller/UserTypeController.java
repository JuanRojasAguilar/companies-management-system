package com.backend.usertype.infraestructure.controller;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.usertype.application.UserTypeService;
import com.backend.usertype.domain.UserType;
import com.backend.usertype.domain.UserTypeDto;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/users/types")
public class UserTypeController {
  @Autowired
  private UserTypeService service;

  @GetMapping
  public Set<UserType> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    return service.findById(id)
        .map(response -> ResponseEntity.ok(response))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UserTypeDto userType,
      BindingResult bindingResult) {
    return bindingResult.hasFieldErrors()
        ? validation(bindingResult)
        : service.update(id, userType)
            .map(updatedEntity -> ResponseEntity.status(HttpStatus.OK).body(updatedEntity))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  @Transactional
  public ResponseEntity<?> save(@Valid @RequestBody UserTypeDto userType, BindingResult bindingResult) {
    return bindingResult.hasFieldErrors()
        ? validation(bindingResult)
        : ResponseEntity.status(HttpStatus.CREATED).body(service.save(userType));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> delete(@PathVariable Long id) {
    return service.delete(id)
        .map(userType -> ResponseEntity.ok(userType))
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
