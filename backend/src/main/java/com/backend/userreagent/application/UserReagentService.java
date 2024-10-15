package com.backend.userreagent.application;

import java.util.List;
import java.util.Optional;

import com.backend.userreagent.domain.UserReagent;

public interface UserReagentService {
    List<UserReagent> findAll();
    Optional<UserReagent> findById(Long id);
    UserReagent save(UserReagent userReagent);
    Optional<UserReagent> update(Long id, UserReagent userReagent);
    Optional<UserReagent> delete(Long id);
}
