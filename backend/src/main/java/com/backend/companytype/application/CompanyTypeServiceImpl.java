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
    public CompanyType save(CompanyType companyType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<CompanyType> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
