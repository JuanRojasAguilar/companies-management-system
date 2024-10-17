package com.backend.servicereagent.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.servicereagent.domain.ServiceReagent;
import com.backend.servicereagent.domain.ServiceReagentId;

@Repository
public interface ServiceReagentRepository extends JpaRepository<ServiceReagent, ServiceReagentId> {
    
}
