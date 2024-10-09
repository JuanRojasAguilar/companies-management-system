package com.backend.service.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.service.domain.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {  
}
