package com.backend.userreagent.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.userreagent.domain.UserReagent;

@Repository
public interface UserReagentRepository extends JpaRepository<UserReagent, Long> {}