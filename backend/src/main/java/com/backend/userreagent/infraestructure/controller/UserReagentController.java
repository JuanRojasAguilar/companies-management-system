package com.backend.userreagent.infraestructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.userreagent.application.UserReagentService;
import com.backend.userreagent.domain.UserReagent;
import com.backend.userreagent.domain.UserReagentId;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/api/users/reagents")
@RestController
public class UserReagentController {
    @Autowired
    private UserReagentService service;

    @GetMapping()
    public List<UserReagent> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UserReagentId id) {
        return service.findById(id)
            .map(userReagent -> ResponseEntity.ok(userReagent))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody UserReagent userReagent, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : ResponseEntity.status(HttpStatus.CREATED).body(service.save(userReagent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UserReagentId id, @Valid @RequestBody UserReagent userReagent, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : service.update(id, userReagent)
                .map(updateUserReagentResponse -> ResponseEntity.status(HttpStatus.OK).body(updateUserReagentResponse))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UserReagentId id) {    
        return service.delete(id)
            .map(userReagent -> ResponseEntity.ok(userReagent))
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
