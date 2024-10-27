package com.backend.userreagent.infraestructure.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.userreagent.application.UserReagentService;
import com.backend.userreagent.domain.UserReagent;
import com.backend.userreagent.domain.UserReagentDto;
import com.backend.userreagent.domain.UserReagentId;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/user-reagents")
public class UserReagentController {
    @Autowired
    private UserReagentService userReagentService;

    @GetMapping()
    public List<UserReagent> getAll() {
        return userReagentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UserReagentId id) {
        return userReagentService.findById(id)
            .map(userReagentNoOp -> ResponseEntity.ok(userReagentNoOp))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody UserReagentDto userReagent, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : ResponseEntity.status(HttpStatus.CREATED).body(userReagentService.save(userReagent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UserReagentId id, @Valid @RequestBody UserReagentDto userReagent, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : userReagentService.update(id, userReagent)
                .map(updateOrderResponse -> ResponseEntity.status(HttpStatus.OK).body(updateOrderResponse))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UserReagentId id) {    
        return userReagentService.delete(id)
            .map(userReagentNoOp -> ResponseEntity.ok(userReagentNoOp))
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
