package com.backend.companyservice.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.companyservice.application.CompanyServiceService;
import com.backend.companyservice.domain.CompanyService;
import com.backend.companyservice.domain.CompanyServiceId;

@Service
public class CompanyServiceServiceImpl implements CompanyServiceService {

    @Autowired
    private CompanyServiceRepository companyServiceRepository;

    @Override
    public CompanyService save(CompanyService companyService) {
        return companyServiceRepository.save(companyService);
    }

    @Override
    public Optional<CompanyService> update(CompanyServiceId id, CompanyService companyService) {
        Optional<CompanyService> companyServiceDB = companyServiceRepository.findById(id);
        if (companyServiceDB.isPresent()) {
            CompanyService companyServiceToUpload = companyServiceDB.orElseThrow();
            BeanUtils.copyProperties(companyService, companyServiceToUpload, "id");
            return Optional.of(companyServiceRepository.save(companyServiceToUpload));
        }
        return Optional.empty();
    }

    @Override
    public Optional<CompanyService> findById(CompanyServiceId id) {
        return companyServiceRepository.findById(id);
    }

    @Override
    public List<CompanyService> findAll() {
        return companyServiceRepository.findAll();
    }

    @Override
    public Optional<CompanyService> delete(CompanyServiceId id) {
        Optional<CompanyService> companyService = companyServiceRepository.findById(id);
        companyService.ifPresent(companyServiceDb -> {
           companyServiceRepository.delete(companyServiceDb);
        });
        return companyService;
    }
    
}
