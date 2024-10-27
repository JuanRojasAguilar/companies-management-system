package com.backend.usertype.application;

import java.util.Optional;

import com.backend.usertype.domain.UserType;

public interface UserTypeService {
    Optional<UserType> findDefaultRole();
    Optional<UserType> findByName(String name);
}
