package com.backend.usertype.infraestructure;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.usertype.application.UserTypeService;
import com.backend.usertype.domain.UserType;
import com.backend.usertype.domain.UserTypeDto;
import com.backend.utils.enums.Status;


@Service
public class UserTypeServiceImpl implements UserTypeService {
    @Autowired
    private UserTypeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Set<UserType> findAll() {
        Set<UserType> types = new LinkedHashSet<>((List<UserType>) repository.findAll());
        return types;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserType> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserType> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public UserType save(UserTypeDto userType) {
        UserType userTypeDb = new UserType();
        BeanUtils.copyProperties(userType, userTypeDb, userType.getClass());
        userTypeDb.setStatus(Status.ENABLED);

        return repository.save(userTypeDb);

    }

    @Override
    @Transactional
    public Optional<UserType> update(Long id, UserTypeDto userType) {
        Optional<UserType> userTypeInstance = repository.findById(id);
        if (userTypeInstance.isPresent()) {
            UserType userTypeDb = new UserType();
            BeanUtils.copyProperties(userType, userTypeDb, userType.getClass());

            return Optional.of(repository.save(userTypeDb));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<UserType> delete(Long id) {
        Optional<UserType> userTypeInstance = repository.findById(id);
        if (userTypeInstance.isPresent()) {
            userTypeInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(repository.save(userTypeInstance.orElseThrow()));
        }
        return Optional.empty();
    }

}
