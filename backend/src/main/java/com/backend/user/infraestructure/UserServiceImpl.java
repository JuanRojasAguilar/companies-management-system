package com.backend.user.infraestructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.user.application.UserService;
import com.backend.user.domain.User;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository repository;

  @Override
  public List<User> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<User> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  public User save(User user) {
    return repository.save(user);
  }

  @Override
  public Optional<User> update(Long id, User user) {
    Optional<User> userInstance = this.findById(id);
    if (userInstance.isPresent()) {
      User newUser = userInstance.get();
      BeanUtils.copyProperties(user, newUser);
      return Optional.of(repository.save(newUser));
    }
    return Optional.empty();
  }

  @Override
  public boolean delete(Long id) {
    Optional<User> userInstance = this.findById(id);
    if (userInstance.isPresent()) {
      repository.delete(userInstance.get());
      return true;
    }
    return false;

  }

}
