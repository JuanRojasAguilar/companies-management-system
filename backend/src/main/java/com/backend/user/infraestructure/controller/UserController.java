package com.backend.user.infraestructure.controller;

import java.util.List;
import java.util.Map;
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

import com.backend.user.application.UserService;
import com.backend.user.application.auth.AuthenticationService;
import com.backend.user.domain.User;
import com.backend.user.domain.dto.UserDto;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserService service;

  @Autowired 
  private AuthenticationService authenticationService;

  @GetMapping
  public List<User> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable String id) {
    return service.findById(id)
            .map(response -> ResponseEntity.ok(response))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/login/{username}")
  public ResponseEntity<?> loginUserByEmail(@PathVariable String username) {
    return service.findOneByUsername(username)
            .map(response -> ResponseEntity.ok(response))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }
  

  @GetMapping("/employees")
  public List<User> findAllEmployees() {
    return service.findAllEmployees();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UserDto user, BindingResult bindingResult) {
    return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : service.update(id, user)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  @Transactional
  public ResponseEntity<?> save(@Valid @RequestBody UserDto user, BindingResult bindingResult) {
    return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.registerOneCustomer(user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    return service.delete(id)
            .map(user -> ResponseEntity.ok(user))
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
