package com.backend.usertelephone.repository.controller;

import java.util.Optional;
import java.util.Set;

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

import com.backend.usertelephone.application.UserTelephoneService;
import com.backend.usertelephone.domain.UserTelephone;

@RestController
@RequestMapping("/api/users/telephones")
public class UserTelephoneController {
  @Autowired
  private UserTelephoneService service;

  @GetMapping
  @Transactional(readOnly = true)
  public Set<UserTelephone> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  @Transactional(readOnly = true)
  public Optional<UserTelephone> findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping
  @Transactional
  public UserTelephone save(@RequestBody UserTelephone userTelephone) {
    return service.save(userTelephone);
  }

  @PutMapping("/{id}")
  public Optional<UserTelephone> update(@PathVariable Long id, @RequestBody UserTelephone userTelephone) {
    return service.update(id, userTelephone);
  }

  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable Long id) {
    return service.delete(id);
  }
}
