package com.backend.userreagent.application;

import java.util.List;
import java.util.Optional;

import com.backend.userreagent.domain.UserReagent;
import com.backend.userreagent.domain.UserReagentId;

public interface UserReagentService {
    public UserReagent save(UserReagent userReagent);

    public Optional<UserReagent> update(UserReagentId id, UserReagent userReagent);

    public Optional<UserReagent> findById(UserReagentId id);
    
    public List<UserReagent> findAll();

    public Optional<UserReagent> delete(UserReagentId id);
}
