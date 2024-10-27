package com.backend.usertype.infraestructure;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.usertype.application.UserTypeService;
import com.backend.usertype.domain.UserType;


@Service
public class UserTypeServiceImpl implements UserTypeService {
    @Autowired
    private UserTypeRepository userTypeRepository;

    @Value("${security.default.role}")
    private String defaultRole;

    @Override
    public Optional<UserType> findDefaultRole() {
        return userTypeRepository.findByName(defaultRole);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserType> findByName(String name) {
        return userTypeRepository.findByName(name);
    }

}
