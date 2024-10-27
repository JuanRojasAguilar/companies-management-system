package com.backend.emailtype.application;

import java.util.Optional;
import java.util.Set;

import com.backend.emailtype.domain.EmailType;
import com.backend.emailtype.domain.EmailTypeDto;

public interface EmailTypeService {
   	public Set<EmailType> findAll();
    public Optional<EmailType> findById(Long id);
    public Optional<EmailType> update(Long id, EmailTypeDto emailType);
    public EmailType save(EmailTypeDto emailType);
    public Optional<EmailType> delete(Long id);
}
