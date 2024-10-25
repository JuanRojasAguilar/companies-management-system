package com.backend.user.infraestructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.backend.user.application.UserService;
import com.backend.user.domain.User;
import com.backend.user.domain.dto.UserDto;
import com.backend.usertype.application.UserTypeService;
import com.backend.usertype.domain.UserType;
import com.backend.utils.exceptions.InvalidPasswordException;
import com.backend.utils.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository repository;

  @Autowired
  UserTypeService userTypeRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

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
        .filter(user -> user.getUserType() == employeeType).collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<User> findById(String id) {
    return repository.findById(id);
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

  @Override
  public User save(UserDto newUser) {
    validatePassword(newUser);

    User user = new User();
    BeanUtils.copyProperties(newUser, user, newUser.getClass());
    UserType defaultRole = userTypeRepository.findDefaultRole()
                    .orElseThrow(() -> new ObjectNotFoundException("Role not found. Default Role"));
    user.setUserType(defaultRole);

    return repository.save(user);
  }

  @Override
  public Optional<User> findOneByUsername(String username) {
    return repository.findByUsername(username);
  }

  private void validatePassword(UserDto dto) {

    if(!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatedPassword())){
        throw new InvalidPasswordException("Passwords don't match");
    }

    if(!dto.getPassword().equals(dto.getRepeatedPassword())){
        throw new InvalidPasswordException("Passwords don't match");
    }
  }
}
