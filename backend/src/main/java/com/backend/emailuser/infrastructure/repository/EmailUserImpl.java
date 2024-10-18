package com.backend.emailuser.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.emailuser.application.EmailUserService;
import com.backend.emailuser.domain.EmailUser;

@Service
public class EmailUserImpl implements EmailUserService {

    @Autowired
    private EmailUserRepository emailUserRepository;

    @Override
    public EmailUser save(EmailUser emailUser) {
        return emailUserRepository.save(emailUser);
    }

    @Override
    public Optional<EmailUser> update(Long id, EmailUser emailUser) {
        Optional<EmailUser> emailUserDB = emailUserRepository.findById(id);
        if (emailUserDB.isPresent()) {
            EmailUser emailUserToUpload = emailUserDB.orElseThrow();
            BeanUtils.copyProperties(emailUser, emailUserToUpload, "id");
            return Optional.of(emailUserRepository.save(emailUserToUpload));
        }
        return Optional.empty();
    }

    @Override
    public Optional<EmailUser> findById(Long id) {
        return emailUserRepository.findById(id);
    }

    @Override
    public List<EmailUser> findAll() {
        return emailUserRepository.findAll();
    }

    @Override
    public Optional<EmailUser> delete(Long id) {
        Optional<EmailUser> emailUser = emailUserRepository.findById(id);
        emailUser.ifPresent(emailUserDb -> {
           emailUserRepository.delete(emailUserDb);
        });
        return emailUser;
    }
    
}
