package com.backend.user.infraestructure;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.LinkedHashSet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.user.application.UserService;
import com.backend.user.domain.User;
import com.backend.usertype.domain.UserType;
import com.backend.usertype.infraestructure.UserTypeRepository;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository repository;

  @Autowired
  private UserTypeRepository userTypeRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

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
    Optional<UserType> optionalUserType = userTypeRepository.findByName("USER_ROLE");
    Set<UserType> roles = new LinkedHashSet<>();

    optionalUserType.ifPresent(roles::add);

    if (user.isAdmin()) {
      optionalUserType = userTypeRepository.findByName("ADMIN_ROLE");
      optionalUserType.ifPresent(roles::add);
    }

    user.setUserType(roles);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return repository.save(user);
  }

  @Override
  @Transactional
  public Optional<User> update(String id, User user) {
    Optional<User> userInstance = this.findById(id);
    if (userInstance.isPresent()) {
      User newUser = userInstance.get();
      BeanUtils.copyProperties(user, newUser, "id");
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
