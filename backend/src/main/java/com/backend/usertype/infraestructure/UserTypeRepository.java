package com.backend.usertype.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.usertype.domain.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {}
