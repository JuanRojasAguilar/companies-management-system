package com.backend.emailuser.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.emailuser.domain.EmailUser;

@Repository
public interface EmailUserRepository extends JpaRepository<EmailUser, Long> {
    
}
