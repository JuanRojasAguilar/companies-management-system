package com.backend.usertype.infraestructure;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.usertype.application.UserTypeService;
import com.backend.usertype.domain.UserType;

@Service
public class UserTypeServiceImpl implements UserTypeService {
    @Autowired
    private UserTypeRepository repository;

    @Override
    public Set<UserType> findAll() {
        Set<UserType> types = new LinkedHashSet<>((List<UserType>) repository.findAll());
        return types;
    }

    @Override
    public Optional<UserType> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public UserType save(UserType userType) {
        return repository.save(userType);
    }

    @Override
    public Optional<UserType> update(Long id, UserType userType) {
        Optional<UserType> userTypeInstance = repository.findById(id);
        if (userTypeInstance.isPresent()) {
            UserType newUserType = userTypeInstance.get();
            BeanUtils.copyProperties(userType, newUserType);
            return Optional.of(repository.save(newUserType));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        Optional<UserType> userTypeInstance = repository.findById(id);
        if (userTypeInstance.isPresent()) {
            repository.delete(userTypeInstance.get());
            return true;
        }
        return false;
    }
    
}
