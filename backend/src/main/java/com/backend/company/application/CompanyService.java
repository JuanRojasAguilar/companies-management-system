package com.backend.company.application;

import java.util.List;
import java.util.Optional;


import com.backend.company.domain.Company;

public interface CompanyService {
    public Company save(Company company);
    public List<Company> findAll();
    public Optional<Company> findById(Long id);
    public Optional<Company> update(Long id, Company company);
    public boolean delete(Long id);
}
