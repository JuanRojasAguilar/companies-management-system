package com.backend.userreagent.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.userreagent.application.UserReagentService;
import com.backend.userreagent.domain.UserReagent;
import com.backend.userreagent.domain.UserReagentId;

@Service
public class UserReagentImpl implements UserReagentService {

    @Autowired
    private UserReagentRepository userReagentRepository;

    @Override
    public UserReagent save(UserReagent userReagent) {
        return userReagentRepository.save(userReagent);
    }

    @Override
    public Optional<UserReagent> update(UserReagentId id, UserReagent userReagent) {
        Optional<UserReagent> userReagentDB = userReagentRepository.findById(id);
        if (userReagentDB.isPresent()) {
            UserReagent userReagentToUpload = userReagentDB.orElseThrow();
            BeanUtils.copyProperties(userReagent, userReagentToUpload, "id");
            return Optional.of(userReagentRepository.save(userReagentToUpload));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserReagent> findById(UserReagentId id) {
        return userReagentRepository.findById(id);
    }

    @Override
    public List<UserReagent> findAll() {
        return userReagentRepository.findAll();
    }

    @Override
    public Optional<UserReagent> delete(UserReagentId id) {
        Optional<UserReagent> userReagent = userReagentRepository.findById(id);
        userReagent.ifPresent(userReagentDb -> {
           userReagentRepository.delete(userReagentDb);
        });
        return userReagent;
    }
    
}
