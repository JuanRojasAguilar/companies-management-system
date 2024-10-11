package com.backend.companytype.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.companytype.domain.CompanyType;

@Repository
public interface CompanyTypeRepository extends JpaRepository<CompanyType, Long> {}
