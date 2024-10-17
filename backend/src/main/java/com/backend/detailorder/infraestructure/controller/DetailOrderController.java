package com.backend.detailorder.infraestructure.controller;

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

import com.backend.detailorder.application.DetailOrderService;
import com.backend.detailorder.domain.DetailOrder;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/emails/types")
public class DetailOrderController {
    @Autowired
    private DetailOrderService service;

    @GetMapping
    @Transactional(readOnly = true)
    public Set<DetailOrder> findAll() {
        return service.findAll();
    }

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<DetailOrder> findById(@PathVariable Long id) {
		Optional<DetailOrder> detailsOpt = service.findById(id);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.ok(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@Valid @RequestBody DetailOrder detailOrder, BindingResult result, @PathVariable Long id) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<DetailOrder> detailsOpt = service.update(id, detailOrder);
		if (detailsOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(detailsOpt.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody DetailOrder detailOrder, BindingResult result) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(detailOrder));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DetailOrder> delete(@PathVariable Long id) {
		DetailOrder detailOrder = new DetailOrder();
		detailOrder.setId(id);
		Optional<DetailOrder> detailOrderDelete = service.delete(id);
		if (detailOrderDelete.isPresent()) {
			return ResponseEntity.ok(detailOrderDelete.orElseThrow());
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
