package com.backend.userreagent.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.reagent.domain.Reagent;
import com.backend.user.domain.User;
import com.backend.userreagent.application.UserReagentService;
import com.backend.userreagent.domain.UserReagent;
import com.backend.userreagent.domain.UserReagentDto;
import com.backend.userreagent.domain.UserReagentId;
import com.backend.utils.enums.Status;

@Service
public class UserReagentImpl implements UserReagentService {

    @Autowired
    private UserReagentRepository userReagentRepository;

    @Override
    public UserReagent save(UserReagentDto userReagent) {
        UserReagent userReagentDb = new UserReagent();
        BeanUtils.copyProperties(userReagent, userReagentDb, userReagent.getClass());
        userReagentDb.setStatus(Status.ENABLED);

        User user = new User();
        user.setId(userReagent.getUserId());
        userReagentDb.setUser(user);

        Reagent reagent = new Reagent();
        reagent.setId(userReagent.getReagentId());
        userReagentDb.setReagent(reagent);

        com.backend.service.domain.Service service = new com.backend.service.domain.Service();
        service.setId(userReagent.getServiceId());
        userReagentDb.setService(service);

        return userReagentRepository.save(userReagentDb);
    }

    @Override
    public Optional<UserReagent> update(UserReagentId id, UserReagentDto userReagent) {
        Optional<UserReagent> userReagentDB = userReagentRepository.findById(id);
        if (userReagentDB.isPresent()) {
            UserReagent userReagentDb = new UserReagent();
            BeanUtils.copyProperties(userReagent, userReagentDb, userReagent.getClass());

            User user = new User();
            user.setId(userReagent.getUserId());
            userReagentDb.setUser(user);

            Reagent reagent = new Reagent();
            reagent.setId(userReagent.getReagentId());
            userReagentDb.setReagent(reagent);

            com.backend.service.domain.Service service = new com.backend.service.domain.Service();
            service.setId(userReagent.getServiceId());
            userReagentDb.setService(service);

            return Optional.of(userReagentRepository.save(userReagentDb));
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
        Optional<UserReagent> userReagentInstance = this.findById(id);
        if (userReagentInstance.isPresent()) {
            userReagentInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(userReagentRepository.save(userReagentInstance.orElseThrow()));
        }
            return Optional.empty();
    }
}
