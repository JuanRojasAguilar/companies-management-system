package com.backend.usertype.infraestructure.controller;

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

import com.backend.usertype.application.UserTypeService;
import com.backend.usertype.domain.UserType;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/users/types")
public class UserTypeController {
    @Autowired
    private UserTypeService service;

    @GetMapping
    @Transactional(readOnly = true)
    public Set<UserType> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public Optional<UserType> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public Optional<UserType> update(@PathVariable Long id, @RequestBody UserType userType) {
        return service.update(id, userType);
    }
    
    @PostMapping
    @Transactional
    public UserType save(@RequestBody UserType userType) {
        return service.save(userType);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
