package com.backend.usertelephone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.usertelephone.domain.UserTelephone;

@Repository
public interface UserTelephoneRepository extends JpaRepository<UserTelephone, Long> {}
