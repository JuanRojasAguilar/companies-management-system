package com.backend.companyservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.companyservice.domain.CompanyService;
import com.backend.companyservice.domain.CompanyServiceId;

@Repository
public interface CompanyServiceRepository extends JpaRepository<CompanyService, CompanyServiceId> {
    
}
