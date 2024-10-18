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
    public Optional<EmailType> update(Long id, EmailType emailType) {
        Optional<EmailType> emailTypeInstance = repository.findById(id);
        if (emailTypeInstance.isPresent()) {
            EmailType newEmailType = emailTypeInstance.get();
            BeanUtils.copyProperties(emailType, newEmailType);
            return Optional.of(repository.save(newEmailType));
        }
        return Optional.empty();
    }

    @Override
    public EmailType save(EmailType emailType) {
        return repository.save(emailType);
    }

    @Override
    public Optional<EmailType> delete(Long id) {
        Optional<EmailType> emailTypeInstance = repository.findById(id);
        if (emailTypeInstance.isPresent()) {
            repository.delete(emailTypeInstance.get());
            return Optional.of(emailTypeInstance).orElseThrow();
        }
        return Optional.empty();
    }
    
}
