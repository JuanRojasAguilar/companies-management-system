package com.backend.usertelephone.repository.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.usertelephone.application.UserTelephoneService;
import com.backend.usertelephone.domain.UserTelephone;
import com.backend.usertelephone.domain.UserTelephoneDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users/telephones")
public class UserTelephoneController {
  @Autowired
  private UserTelephoneService service;

  @GetMapping
  public Set<UserTelephone> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  @Transactional(readOnly = true)
  public ResponseEntity<?> findById(@PathVariable Long id) {
    return service.findById(id)
            .map(response -> ResponseEntity.ok(response))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<?> save(@Valid @RequestBody UserTelephoneDto userTelephone, BindingResult bindingResult) {
    return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : ResponseEntity.status(HttpStatus.CREATED).body(service.save(userTelephone));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UserTelephoneDto userTelephone, BindingResult bindingResult) {
    return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : service.update(id, userTelephone)
              .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
              .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    return service.delete(id)
            .map(userTelephone -> ResponseEntity.ok(userTelephone))
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
