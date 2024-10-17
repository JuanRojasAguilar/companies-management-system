package com.backend.emailuser.infrastructure.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.emailuser.application.EmailUserService;
import com.backend.emailuser.domain.EmailUser;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/orders/states")
public class EmailUserController {
    @Autowired
    private EmailUserService emailUserService;

    @GetMapping()
    public List<EmailUser> getAll() {
        return emailUserService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return emailUserService.findById(id)
            .map(emailUserNoOp -> ResponseEntity.ok(emailUserNoOp))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody EmailUser emailUser, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : ResponseEntity.status(HttpStatus.CREATED).body(emailUserService.save(emailUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody EmailUser emailUser, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : emailUserService.update(id, emailUser)
                .map(updateOrderResponse -> ResponseEntity.status(HttpStatus.OK).body(updateOrderResponse))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {    
        return emailUserService.delete(id)
            .map(emailUserNoOp -> ResponseEntity.ok(emailUserNoOp))
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
