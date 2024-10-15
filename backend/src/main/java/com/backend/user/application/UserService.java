package com.backend.user.application;

import java.util.List;
import java.util.Optional;

import com.backend.user.domain.User;

public interface UserService {
  List<User> findAll();
  Optional<User> findById(Long id);
  User save(User user);
  Optional<User> update(Long id, User user);
  boolean delete(Long id);
}
