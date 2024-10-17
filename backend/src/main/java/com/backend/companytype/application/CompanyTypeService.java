package com.backend.companytype.application;

import java.util.Optional;
import java.util.Set;

import com.backend.companytype.domain.CompanyType;

public interface CompanyTypeService {
    public CompanyType save(CompanyType companyType);
    public Set<CompanyType> findAll();
    public Optional<CompanyType> findById(Long id);
	public Optional<CompanyType> update(Long id, CompanyType companyType);
    public Optional<CompanyType> delete(Long id);
}
