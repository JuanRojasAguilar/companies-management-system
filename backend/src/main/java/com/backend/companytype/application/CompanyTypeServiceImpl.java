package com.backend.companytype.application;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.companytype.domain.CompanyType;
import com.backend.companytype.infraestructure.CompanyTypeRepository;

@Service
public class CompanyTypeServiceImpl implements CompanyTypeService {
    @Autowired
    private CompanyTypeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Set<CompanyType> findAll() {
        Set<CompanyType> types = new LinkedHashSet<>((List<CompanyType>) repository.findAll());
        return types;
    }

    @Override
    @Transactional
    public CompanyType save(CompanyType companyType) {
        return repository.save(companyType);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyType> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        try {
            CompanyType companyInstance = this.findById(id).get();
            repository.delete(companyInstance);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
