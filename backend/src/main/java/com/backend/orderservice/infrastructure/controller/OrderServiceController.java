package com.backend.orderservice.infrastructure.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.orderservice.application.OrderServiceService;
import com.backend.orderservice.domain.OrderService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/orders/services")
public class OrderServiceController {
    @Autowired
    private OrderServiceService orderServiceService;

    @GetMapping()
    public List<OrderService> getAll() {
        return orderServiceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return orderServiceService.findById(id)
            .map(orderServiceNoOp -> ResponseEntity.ok(orderServiceNoOp))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody OrderService orderService, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : ResponseEntity.status(HttpStatus.CREATED).body(orderServiceService.save(orderService));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody OrderService orderService, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors()
            ? validation(bindingResult)
            : orderServiceService.update(id, orderService)
                .map(updateOrderResponse -> ResponseEntity.status(HttpStatus.OK).body(updateOrderResponse))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {    
        return orderServiceService.delete(id)
            .map(orderServiceNoOp -> ResponseEntity.ok(orderServiceNoOp))
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
