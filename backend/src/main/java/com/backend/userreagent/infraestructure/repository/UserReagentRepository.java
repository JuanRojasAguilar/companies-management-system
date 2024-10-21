package com.backend.userreagent.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.userreagent.domain.UserReagent;
import com.backend.userreagent.domain.UserReagentId;

public interface UserReagentRepository extends JpaRepository<UserReagent, UserReagentId> {
    
}
