package com.backend.emailtype.application;

import java.util.Optional;
import java.util.Set;

import com.backend.emailtype.domain.EmailType;

public interface EmailTypeService {
   	public Set<EmailType> findAll();
    public Optional<EmailType> findById(Long id);
    public Optional<EmailType> update(Long id, EmailType emailType);
    public EmailType save(EmailType emailType);
    public Optional<EmailType> delete(Long id);
}
