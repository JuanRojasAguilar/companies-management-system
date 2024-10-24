package com.backend.usertype.application;

import java.util.Optional;
import java.util.Set;

import com.backend.usertype.domain.UserType;
import com.backend.usertype.domain.UserTypeDto;

public interface UserTypeService {
    Set<UserType> findAll();
    Optional<UserType> findById(Long id);
    UserType save(UserTypeDto userType);
    Optional<UserType> update(Long id, UserTypeDto userType);
    Optional<UserType> delete(Long id);
    Optional<UserType> findByName(String name);
}
