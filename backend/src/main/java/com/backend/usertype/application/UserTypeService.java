package com.backend.usertype.application;

import java.util.Optional;
import java.util.Set;

import com.backend.usertype.domain.UserType;

public interface UserTypeService {
    Set<UserType> findAll();
    Optional<UserType> findById(Long id);
    UserType save(UserType userType);
    Optional<UserType> update(Long id, UserType userType);
    Optional<UserType> delete(Long id);
}
