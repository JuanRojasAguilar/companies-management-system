package com.backend.userreagent.application;

import java.util.List;
import java.util.Optional;

import com.backend.userreagent.domain.UserReagent;
import com.backend.userreagent.domain.UserReagentId;

public interface UserReagentService {
    UserReagent save(UserReagent userReagent);

    Optional<UserReagent> update(UserReagentId id, UserReagent userReagent);

    Optional<UserReagent> findById(UserReagentId id);
    
    List<UserReagent> findAll();

    Optional<UserReagent> delete(UserReagentId id);
}
