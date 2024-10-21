package com.backend.companytype.application;

import java.util.Optional;
import java.util.Set;

import com.backend.companytype.domain.CompanyType;
import com.backend.companytype.domain.CompanyTypeDto;

public interface CompanyTypeService {
    public CompanyType save(CompanyTypeDto companyType);
    public Set<CompanyType> findAll();
    public Optional<CompanyType> findById(Long id);
	public Optional<CompanyType> update(Long id, CompanyTypeDto companyType);
    public Optional<CompanyType> delete(Long id);
}
