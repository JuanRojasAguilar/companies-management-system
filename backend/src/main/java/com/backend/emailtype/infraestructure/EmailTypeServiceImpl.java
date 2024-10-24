package com.backend.emailtype.infraestructure;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.emailtype.application.EmailTypeService;
import com.backend.emailtype.domain.EmailType;
import com.backend.emailtype.domain.EmailTypeDto;
import com.backend.utils.enums.Status;

@Service
public class EmailTypeServiceImpl implements EmailTypeService {
    @Autowired
    private EmailTypeRepository repository;

    @Override
    public Set<EmailType> findAll() {
        Set<EmailType> types = new LinkedHashSet<>((List<EmailType>) repository.findAll());
        return types;
    }

    @Override
    public Optional<EmailType> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<EmailType> update(Long id, EmailTypeDto emailType) {
        Optional<EmailType> emailTypeInstance = repository.findById(id);
        if (emailTypeInstance.isPresent()) {
            EmailType emailTypeDb = new EmailType();
            BeanUtils.copyProperties(emailType, emailTypeDb, emailType.getClass());
            
            return Optional.of(repository.save(emailTypeDb));
        }
        return Optional.empty();
    }

    @Override
    public EmailType save(EmailTypeDto emailType) {
        EmailType emailTypeDb = new EmailType();
        BeanUtils.copyProperties(emailType, emailTypeDb, emailType.getClass());
        emailTypeDb.setStatus(Status.ENABLED);

        return repository.save(emailTypeDb);
    }

    @Override
    public Optional<EmailType> delete(Long id) {
        Optional<EmailType> emailTypeInstance = repository.findById(id);
        if (emailTypeInstance.isPresent()) {
            emailTypeInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(repository.save(emailTypeInstance.orElseThrow()));
        }
        return Optional.empty();
    }
    
}
