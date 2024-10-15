package com.backend.usertelephone.application;

import java.util.Optional;
import java.util.Set;

import com.backend.usertelephone.domain.UserTelephone;

public interface UserTelephoneService {
  Set<UserTelephone> findAll();
  Optional<UserTelephone> findById(Long id);
  UserTelephone save(UserTelephone userTelephone);
  Optional<UserTelephone> update(Long id, UserTelephone userTelephone);
  boolean delete(Long id);
}

