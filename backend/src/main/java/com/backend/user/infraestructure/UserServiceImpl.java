package com.backend.user.infraestructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.user.application.UserService;
import com.backend.user.domain.User;
import com.backend.usertype.domain.UserType;
import com.backend.usertype.infraestructure.UserTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository repository;

  private final UserTypeRepository userTypeRepository;

  @Override
  @Transactional(readOnly = true)
  public List<User> findAll() {
    return repository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> findAllEmployees() {
    UserType employeeType = userTypeRepository.findByName("EMPLOYEES").get();
    return repository
        .findAll().stream()
        .filter(user -> user.getUserTypeId() == employeeType).collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<User> findById(String id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public User save(User user) {
    return repository.save(user);
  }

  @Override
  @Transactional
  public Optional<User> update(String id, User user) {
    Optional<User> userInstance = this.findById(id);
    if (userInstance.isPresent()) {
      User newUser = userInstance.get();
      BeanUtils.copyProperties(user, newUser);
      return Optional.of(repository.save(newUser));
    }
    return Optional.empty();
  }

  @Override
  @Transactional
  public Optional<User> delete(String id) {
    Optional<User> userInstance = this.findById(id);
    if (userInstance.isPresent()) {
      repository.delete(userInstance.get());
      return Optional.of(userInstance.get());
    }
    return Optional.empty();
  }
}
