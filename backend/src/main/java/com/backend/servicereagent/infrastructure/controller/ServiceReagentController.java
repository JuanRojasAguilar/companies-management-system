package com.backend.servicereagent.infrastructure.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.servicereagent.application.ServiceReagentService;
import com.backend.servicereagent.domain.entity.ServiceReagent;
import com.backend.servicereagent.domain.entity.ServiceReagentPk;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/service-reagents")
public class ServiceReagentController {
    @Autowired
    private ServiceReagentService serviceReagentService;

    @GetMapping()
    public List<ServiceReagent> getAll() {
        return serviceReagentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ServiceReagentPk id) {
        return serviceReagentService.findById(id)
            .map(serviceReagentNoOp -> ResponseEntity.ok(serviceReagentNoOp))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody ServiceReagent serviceReagent, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : ResponseEntity.status(HttpStatus.CREATED).body(serviceReagentService.save(serviceReagent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable ServiceReagentPk id, @Valid @RequestBody ServiceReagent serviceReagent, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : serviceReagentService.update(id, serviceReagent)
                .map(updateOrderResponse -> ResponseEntity.status(HttpStatus.OK).body(updateOrderResponse))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ServiceReagentPk id) {    
        return serviceReagentService.delete(id)
            .map(serviceReagentNoOp -> ResponseEntity.ok(serviceReagentNoOp))
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
