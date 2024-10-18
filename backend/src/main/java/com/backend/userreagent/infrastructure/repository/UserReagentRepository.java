package com.backend.userreagent.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.userreagent.domain.UserReagent;
import com.backend.userreagent.domain.UserReagentId;

@Repository
public interface UserReagentRepository extends JpaRepository<UserReagent, UserReagentId> {
    
}
