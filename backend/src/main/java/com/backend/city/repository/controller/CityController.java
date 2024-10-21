package com.backend.city.repository.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.city.application.CityService;
import com.backend.city.domain.City;
import com.backend.city.domain.CityDto;

import jakarta.validation.Valid;

@RequestMapping("/api/cities")
@RestController
public class CityController {
  @Autowired
  private CityService cityService;

  @GetMapping()
  public List<City> getAll() {
    return cityService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    return cityService.findById(id)
        .map(cityNoOp -> ResponseEntity.ok(cityNoOp))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping()
  public ResponseEntity<?> create(@Valid @RequestBody CityDto city, BindingResult bindingResult) {
    return bindingResult.hasFieldErrors()
        ? validation(bindingResult)
        : ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(city));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CityDto city, BindingResult bindingResult) {
    return bindingResult.hasFieldErrors()
        ? validation(bindingResult)
        : cityService.update(id, city)
            .map(updateOrderResponse -> ResponseEntity.status(HttpStatus.OK).body(updateOrderResponse))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")

  public ResponseEntity<?> delete(@PathVariable Long id) {
    return cityService.delete(id)
        .map(cityNoOp -> ResponseEntity.ok(cityNoOp))
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
