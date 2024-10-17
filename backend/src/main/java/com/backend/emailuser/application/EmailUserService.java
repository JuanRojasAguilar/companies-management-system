package com.backend.emailuser.application;

import java.util.List;
import java.util.Optional;

import com.backend.emailuser.domain.EmailUser;

public interface EmailUserService {
    public EmailUser save(EmailUser emailUser);

    public Optional<EmailUser> update(Long id, EmailUser emailUser);

    public Optional<EmailUser> findById(Long id);
    
    public List<EmailUser> findAll();

    public Optional<EmailUser> delete(Long id);
}