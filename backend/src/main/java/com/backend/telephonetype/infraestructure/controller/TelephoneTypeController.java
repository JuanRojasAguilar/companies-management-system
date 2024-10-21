package com.backend.telephonetype.infraestructure.controller;

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

import com.backend.telephonetype.application.TelephoneTypeService;
import com.backend.telephonetype.domain.TelephoneType;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/telephones/types")
public class TelephoneTypeController {
    @Autowired
    private TelephoneTypeService service;

    @GetMapping
    public Set<TelephoneType> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(telephoneType -> ResponseEntity.ok(telephoneType))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@Valid @RequestBody TelephoneType telephoneType, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
                ? validation(bindingResult)
                : ResponseEntity.status(HttpStatus.CREATED).body(service.save(telephoneType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody TelephoneType telephoneType,
            BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
                ? validation(bindingResult)
                : service.update(id, telephoneType)
                        .map(newTelephoneType -> ResponseEntity.status(HttpStatus.OK).body(newTelephoneType))
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id)
                .map(telephoneType -> ResponseEntity.ok(telephoneType))
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
