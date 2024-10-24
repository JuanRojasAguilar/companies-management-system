package com.backend.emailtype.infraestructure.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

import com.backend.emailtype.application.EmailTypeService;
import com.backend.emailtype.domain.EmailType;
import com.backend.emailtype.domain.EmailTypeDto;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/emails/types")
public class EmailTypeController {
    @Autowired
    private EmailTypeService service;

    @GetMapping
    @Transactional(readOnly = true)
    public Set<EmailType> findAll() {
        return service.findAll();
    }

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<EmailType> findById(@PathVariable Long id) {
		Optional<EmailType> detailsOpt = service.findById(id);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.ok(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@Valid @RequestBody EmailTypeDto emailType, BindingResult result, @PathVariable Long id) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<EmailType> detailsOpt = service.update(id, emailType);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody EmailTypeDto emailType, BindingResult result) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(emailType));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<EmailType> delete(@PathVariable Long id) {
		EmailType emailType = new EmailType();
		emailType.setId(id);
		Optional<EmailType> emailTypeDelete = service.delete(id);
		if (emailTypeDelete.isPresent()) {
			return ResponseEntity.ok(emailTypeDelete.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errors = new HashMap<>();

		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), "El campo" + err.getField() + " " + err.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errors);
	}
}
