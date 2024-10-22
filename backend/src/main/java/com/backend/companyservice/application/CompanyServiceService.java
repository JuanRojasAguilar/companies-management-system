package com.backend.companyservice.application;

import java.util.List;
import java.util.Optional;

import com.backend.companyservice.domain.CompanyService;
import com.backend.companyservice.domain.CompanyServiceDto;
import com.backend.companyservice.domain.CompanyServiceId;

public interface CompanyServiceService {
    public CompanyService save(CompanyServiceDto companyService);

    public Optional<CompanyService> update(CompanyServiceId id, CompanyServiceDto companyService);

    public Optional<CompanyService> findById(CompanyServiceId id);
    
    public List<CompanyService> findAll();

    public Optional<CompanyService> delete(CompanyServiceId id);
}
