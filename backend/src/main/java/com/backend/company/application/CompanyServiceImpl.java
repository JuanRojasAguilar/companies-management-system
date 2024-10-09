package com.backend.company.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.company.domain.Company;
import com.backend.company.infraestructure.CompanyRepository;
import org.springframework.transaction.annotation.Transactional;

public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository repository;

    @Override
    @Transactional(readOnly=true)
    public List<Company> findAll() {
        return (List<Company>) repository.findAll();
    }

    @Override
    @Transactional
    public Company save(Company company) {
        return repository.save(company);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Company> update(Long id, Company company) {
        Optional<Company> companyInstance = this.findById(id);
        if (companyInstance.isPresent()) {
            Company newCompany = companyInstance.get();
            if (company.getName() != null) newCompany.setName(company.getName());
            if (company.getCompanyType() != null) newCompany.setCompanyType(company.getCompanyType());
            repository.save(newCompany);
            return Optional.of(newCompany);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        Optional<Company> companyInstance = this.findById(id);
        if (companyInstance.isPresent()) {
            repository.delete(companyInstance.get());
            return true;
        }
        return false;
    }
}
