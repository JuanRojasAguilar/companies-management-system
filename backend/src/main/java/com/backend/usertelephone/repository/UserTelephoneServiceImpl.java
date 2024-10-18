package com.backend.usertelephone.repository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.usertelephone.application.UserTelephoneService;
import com.backend.usertelephone.domain.UserTelephone;

@Service
public class UserTelephoneServiceImpl implements UserTelephoneService {
  @Autowired
  private UserTelephoneRepository repository;

  @Override
  @Transactional(readOnly = true)
  public Set<UserTelephone> findAll() {
    Set<UserTelephone> telephones = new LinkedHashSet<>((List<UserTelephone>) repository.findAll());
    return telephones;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UserTelephone> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public UserTelephone save(UserTelephone userTelephone) {
    return repository.save(userTelephone);
  }

  @Override
  @Transactional
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
  @Transactional
  public Optional<UserTelephone> delete(Long id) {
    Optional<UserTelephone> telephoneInstance = repository.findById(id);
    if (telephoneInstance.isPresent()) {
      try {
        repository.delete(telephoneInstance.get());
        return Optional.of(telephoneInstance.get());
      } catch (IllegalArgumentException e) {
        return Optional.empty();
      }
    }
    return Optional.empty();
  }

}
