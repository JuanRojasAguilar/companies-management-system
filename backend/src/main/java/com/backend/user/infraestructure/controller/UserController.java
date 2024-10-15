package com.backend.user.infraestructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.user.application.UserService;
import com.backend.user.domain.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserService service;

  @GetMapping
  @Transactional(readOnly = true)
  public List<User> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  @Transactional(readOnly = true)
  public Optional<User> findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @PutMapping("/{id}")
  @Transactional
  public Optional<User> update(@PathVariable Long id, @RequestBody User user) {
    return service.update(id, user);
  }

  @PostMapping
  @Transactional
  public User save(@RequestBody User user) {
    return service.save(user);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public boolean delete(@PathVariable Long id) {
    return service.delete(id);
  }
}
