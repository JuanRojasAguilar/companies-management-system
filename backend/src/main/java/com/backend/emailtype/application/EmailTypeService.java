package com.backend.emailtype.application;

import java.util.Optional;
import java.util.Set;

import com.backend.emailtype.domain.EmailType;


public interface EmailTypeService {
    Set<EmailType> findAll();
    Optional<EmailType> findById(Long id);
    Optional<EmailType> update(Long id, EmailType emailType);
    EmailType save(EmailType emailType);
    boolean delete(Long id);
}
