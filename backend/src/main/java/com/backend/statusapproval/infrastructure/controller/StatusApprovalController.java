package com.backend.statusapproval.infrastructure.controller;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.statusapproval.application.StatusApprovalService;
import com.backend.statusapproval.domain.StatusApproval;

import jakarta.validation.Valid;

@RequestMapping("/api/statusapproval")
public class StatusApprovalController {
	@Autowired
	private StatusApprovalService service;

	@GetMapping
	public Set<StatusApproval> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return service.findById(id)
				.map(status -> ResponseEntity.ok(status))
				.orElseGet(() -> ResponseEntity.notFound().build());			
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody StatusApproval statusApproval, BindingResult bindingResult) {
		return bindingResult.hasFieldErrors()
				? validation(bindingResult)
				: ResponseEntity.status(HttpStatus.CREATED).body(service.save(statusApproval));
	}

    private ResponseEntity<?> validation(BindingResult bindingResult) {
        Map<String, String> errors = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> "el campo" + error.getField() + " " + error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}
