package com.backend.usertelephone.repository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.usertelephone.application.UserTelephoneService;
import com.backend.usertelephone.domain.UserTelephone;

@Service
public class UserTelephoneServiceImpl implements UserTelephoneService {
  @Autowired
  private UserTelephoneRepository repository;

  @Override
  public Set<UserTelephone> findAll() {
    Set<UserTelephone> telephones = new LinkedHashSet<>((List<UserTelephone>) repository.findAll());
    return telephones;
  }

  @Override
  public Optional<UserTelephone> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  public UserTelephone save(UserTelephone userTelephone) {
    return repository.save(userTelephone);
  }

  @Override
  public Optional<UserTelephone> update(Long id, UserTelephone userTelephone) {
    Optional<UserTelephone> telephoneInstance = repository.findById(id);
    if (telephoneInstance.isPresent()) {
      UserTelephone newTelephone = telephoneInstance.get();
      BeanUtils.copyProperties(userTelephone, newTelephone);
      return Optional.of(repository.save(newTelephone));
    }
    return Optional.empty();
  }

  @Override
  public boolean delete(Long id) {
    Optional<UserTelephone> telephoneInstance = repository.findById(id);
    if (telephoneInstance.isPresent()) {
      try {
        repository.delete(telephoneInstance.get());
        return true;
      } catch (IllegalArgumentException e) {
        return false;
      }
    }
    return false;
  }

}
