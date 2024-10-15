package com.backend.emailtype.infraestructure.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.emailtype.application.EmailTypeService;
import com.backend.emailtype.domain.EmailType;


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
    public Optional<EmailType> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Transactional
    public EmailType save(@RequestBody EmailType emailType) {
        return service.save(emailType);
    }

    @PostMapping("/{id}")
    @Transactional
    public Optional<EmailType> update(@PathVariable Long id, @RequestBody EmailType emailType) {
        return service.update(id, emailType);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
