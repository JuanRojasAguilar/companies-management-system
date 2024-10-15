package com.backend.user.infraestructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.user.application.UserService;
import com.backend.user.domain.User;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository repository;

  @Override
  @Transactional(readOnly = true)
  public List<User> findAll() {
    return repository.findAll();
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
