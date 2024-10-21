package com.backend.company.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.company.application.CompanyService;
import com.backend.company.domain.Company;
import com.backend.company.domain.CompanyDto;
import com.backend.companytype.domain.CompanyType;
import com.backend.utils.enums.Status;

import org.springframework.transaction.annotation.Transactional;

@Service
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
    public Company save(CompanyDto company) {
        Company companyDb = new Company();
        BeanUtils.copyProperties(company, companyDb, company.getClass());
        companyDb.setStatus(Status.ENABLED);

        CompanyType companyType = new CompanyType();
        companyType.setId(company.getCompanyTypeId());
        companyDb.setCompanyType(companyType);

        return repository.save(companyDb);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Company> update(Long id, CompanyDto company) {
        Optional<Company> companyInstance = this.findById(id);
        if (companyInstance.isPresent()) {
            Company companyDb = companyInstance.orElseThrow();
            BeanUtils.copyProperties(company, companyDb, company.getClass());

            CompanyType companyType = new CompanyType();
            companyType.setId(company.getCompanyTypeId());
            companyDb.setCompanyType(companyType);

            return Optional.of(repository.save(companyDb));
            
        }
        return Optional.empty();
    }

    @Override
    public Optional<Company> delete(Long id) {
        Optional<Company> companyInstance = repository.findById(id);
        if (companyInstance.isPresent()) {
            repository.delete(companyInstance.get());
            return Optional.of(companyInstance).orElseThrow();
        }
        return Optional.empty();
    }
}
