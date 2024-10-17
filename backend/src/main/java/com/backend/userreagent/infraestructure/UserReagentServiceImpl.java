package com.backend.userreagent.infraestructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.userreagent.application.UserReagentService;
import com.backend.userreagent.domain.UserReagent;

@Service
public class UserReagentServiceImpl implements UserReagentService {
    @Autowired
    private UserReagentRepository repository;

    @Override
    public List<UserReagent> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<UserReagent> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public UserReagent save(UserReagent userReagent) {
        return repository.save(userReagent);
    }

    @Override
    public Optional<UserReagent> update(Long id, UserReagent userReagent) {
        Optional<UserReagent> userReagentInstance = repository.findById(id);
        if (userReagentInstance.isPresent()) {
            UserReagent newReagent = userReagentInstance.get();
            BeanUtils.copyProperties(userReagent, newReagent, "id");
            return Optional.of(repository.save(newReagent));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserReagent> delete(Long id) {
        Optional<UserReagent> userReagentInstance = repository.findById(id);
        if (userReagentInstance.isPresent()) {
            repository.delete(userReagentInstance.get());
            return Optional.of(userReagentInstance.get());
        }
        return Optional.empty();
    }
}
