package com.backend.companytype.application;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.backend.companytype.domain.CompanyType;

@Service
public interface CompanyTypeService {
    public CompanyType save(CompanyType companyType);
    public Set<CompanyType> findAll();
    public Optional<CompanyType> findById(Long id);
    public boolean delete(Long id);
}
