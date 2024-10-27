package com.backend.user.application;

import java.util.List;
import java.util.Optional;

import com.backend.user.domain.User;
import com.backend.user.domain.dto.UserDto;

public interface UserService {
  List<User> findAll();
  Optional<User> findById(String id);
  List<User> findAllEmployees();
  User save(UserDto user);
  Optional<User> update(String id, UserDto user);
  Optional<User> delete(String id);
  Optional<User> findOneByUsername(String username);
  void createadminUser();
}