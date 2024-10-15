package com.backend.emailtype.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.emailtype.domain.EmailType;

@Repository
public interface EmailTypeRepository extends JpaRepository<EmailType, Long> {}
