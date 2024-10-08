package com.backend.companytype.infraestructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.companytype.domain.CompanyType;


@Repository
public interface CompanyTypeRepository extends CrudRepository<CompanyType, Long> {}
