package com.backend.user.application;

import java.util.List;
import java.util.Optional;

import com.backend.user.domain.User;

public interface UserService {
  List<User> findAll();
  Optional<User> findById(String id);
  User save(User user);
  Optional<User> update(String id, User user);
  boolean delete(String id);
}
