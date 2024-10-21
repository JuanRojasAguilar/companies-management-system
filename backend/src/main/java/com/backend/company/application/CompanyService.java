package com.backend.company.application;

import java.util.List;
import java.util.Optional;


import com.backend.company.domain.Company;
import com.backend.company.domain.CompanyDto;

public interface CompanyService {
    public Company save(CompanyDto company);
    public List<Company> findAll();
    public Optional<Company> findById(Long id);
    public Optional<Company> update(Long id, CompanyDto company);
    public Optional<Company> delete(Long id);
}
