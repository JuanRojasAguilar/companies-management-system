package com.backend.emailuser.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.emailtype.domain.EmailType;
import com.backend.emailuser.application.EmailUserService;
import com.backend.emailuser.domain.EmailUser;
import com.backend.emailuser.domain.EmailUserDto;
import com.backend.user.domain.User;
import com.backend.utils.enums.Status;

@Service
public class EmailUserImpl implements EmailUserService {

    @Autowired
    private EmailUserRepository emailUserRepository;

    @Override
    public EmailUser save(EmailUserDto emailUser) {
        EmailUser emailUserDb = new EmailUser();
        emailUserDb.setEmail(emailUser.getEmail());
        emailUserDb.setStatus(Status.ENABLED);

        EmailType emailType = new EmailType();
        emailType.setId(emailUser.getEmailTypeId());
        emailUserDb.setEmailType(emailType);

        User user = new User();
        user.setId(emailUser.getUserId());
        emailUserDb.setUser(user);

        return emailUserRepository.save(emailUserDb);
    }

    @Override
    public Optional<EmailUser> update(Long id, EmailUserDto emailUser) {
        Optional<EmailUser> emailUserDB = emailUserRepository.findById(id);
        if (emailUserDB.isPresent()) {
            EmailUser emailUserDb = new EmailUser();
            emailUserDb.setEmail(emailUser.getEmail());

            EmailType emailType = new EmailType();
            emailType.setId(emailUser.getEmailTypeId());
            emailUserDb.setEmailType(emailType);

            User user = new User();
            user.setId(emailUser.getUserId());
            emailUserDb.setUser(user);

            return Optional.of(emailUserRepository.save(emailUserDb));
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
        Optional<EmailUser> emailUserInstance = this.findById(id);
        if (emailUserInstance.isPresent()) {
            emailUserInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(emailUserRepository.save(emailUserInstance.orElseThrow()));
        }
            return Optional.empty();
        }
    
}
