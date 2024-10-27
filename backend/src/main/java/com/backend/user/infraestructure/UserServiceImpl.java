package com.backend.user.infraestructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.backend.franchise.domain.Franchise;
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
  public Optional<User> update(String id, UserDto user) {
    Optional<User> userInstance = this.findById(id);
    if (userInstance.isPresent()) {
      validatePassword(user);

      User usetToUpdate = new User();
      usetToUpdate.setId(user.getId());
      usetToUpdate.setName(user.getName());
      usetToUpdate.setLastname(user.getLastname());
      usetToUpdate.setUsername(user.getUsername());
      usetToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));

      if (user.getFranchiseId() != null) {
        Franchise franchise = new Franchise();
        franchise.setId(user.getFranchiseId());
        usetToUpdate.setFranchise(franchise);
      }

      if (user.getUsertypeId() == 0) {
        UserType defaultRole = userTypeRepository.findDefaultRole()
            .orElseThrow(() -> new ObjectNotFoundException("Role not found. Default Role"));
        usetToUpdate.setUserType(defaultRole);
      } else {
        UserType userType = new UserType();
        userType.setId(user.getUsertypeId());
        usetToUpdate.setUserType(userType);
      }

      return Optional.of(repository.save(usetToUpdate));
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
    user.setId(newUser.getId());
    user.setName(newUser.getName());
    user.setLastname(newUser.getLastname());
    user.setUsername(newUser.getUsername());
    user.setPassword(passwordEncoder.encode(newUser.getPassword()));

    if (newUser.getFranchiseId() != null) {
      Franchise franchise = new Franchise();
      franchise.setId(newUser.getFranchiseId());
      user.setFranchise(franchise);
    }

    if (newUser.getUsertypeId() == 0) {
      UserType defaultRole = userTypeRepository.findDefaultRole()
          .orElseThrow(() -> new ObjectNotFoundException("Role not found. Default Role"));
      user.setUserType(defaultRole);
    } else {
      UserType userType = new UserType();
      userType.setId(newUser.getUsertypeId());
      user.setUserType(userType);
    }

    return repository.save(user);
  }

  @Override
  public Optional<User> findOneByUsername(String username) {
    return repository.findByUsername(username);
  }

  private void validatePassword(UserDto dto) {

    if (!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatedPassword())) {
      throw new InvalidPasswordException("Passwords don't match");
    }

    if (!dto.getPassword().equals(dto.getRepeatedPassword())) {
      throw new InvalidPasswordException("Passwords don't match");
    }
  }

  @Override
  public void createadminUser() {
    UserDto user = new UserDto();
    user.setId("1");
    user.setUsername("root");
    user.setPassword("12345678");
    user.setRepeatedPassword("12345678");
    user.setUsertypeId((long) 2);

    this.save(user);
  }
}
