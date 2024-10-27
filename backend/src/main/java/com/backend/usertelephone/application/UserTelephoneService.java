package com.backend.usertelephone.application;

import java.util.Optional;
import java.util.Set;

import com.backend.usertelephone.domain.UserTelephone;
import com.backend.usertelephone.domain.UserTelephoneDto;

public interface UserTelephoneService {
  Set<UserTelephone> findAll();
  Optional<UserTelephone> findById(Long id);
  UserTelephone save(UserTelephoneDto userTelephone);
  Optional<UserTelephone> update(Long id, UserTelephoneDto userTelephone);
  Optional<UserTelephone> delete(Long id);
}

