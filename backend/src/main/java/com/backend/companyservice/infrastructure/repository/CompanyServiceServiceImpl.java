package com.backend.companyservice.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.companyservice.application.CompanyServiceService;
import com.backend.companyservice.domain.CompanyService;
import com.backend.companyservice.domain.CompanyServiceDto;
import com.backend.companyservice.domain.CompanyServiceId;
import com.backend.franchise.domain.Franchise;
import com.backend.utils.enums.Status;

@Service
public class CompanyServiceServiceImpl implements CompanyServiceService {

    @Autowired
    private CompanyServiceRepository companyServiceRepository;

    @Override
    public CompanyService save(CompanyServiceDto companyService) {
        CompanyService companyServiceDb = new CompanyService();
        companyServiceDb.setValueService(companyService.getValueService());
        companyServiceDb.setStatus(Status.ENABLED);

        Franchise franchise = new Franchise();
        franchise.setId(companyService.getFranchiseId());
        companyServiceDb.setFranchise(franchise);

        com.backend.service.domain.Service service = new com.backend.service.domain.Service();
        service.setId(companyService.getServiceId());
        companyServiceDb.setService(service);

        return companyServiceRepository.save(companyServiceDb);
    }

    @Override
    public Optional<CompanyService> update(CompanyServiceId id, CompanyServiceDto companyService) {
        Optional<CompanyService> companyServiceDB = companyServiceRepository.findById(id);
        if (companyServiceDB.isPresent()) {
            CompanyService companyServiceDb = new CompanyService();
            companyServiceDb.setValueService(companyService.getValueService());

            Franchise franchise = new Franchise();
            franchise.setId(companyService.getFranchiseId());
            companyServiceDb.setFranchise(franchise);

            com.backend.service.domain.Service service = new com.backend.service.domain.Service();
            service.setId(companyService.getServiceId());
            companyServiceDb.setService(service);

            return Optional.of(companyServiceRepository.save(companyServiceDb));
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
        Optional<CompanyService> companyInstance = this.findById(id);
        if (companyInstance.isPresent()) {
            companyInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(companyServiceRepository.save(companyInstance.orElseThrow()));
        }
        return Optional.empty();
    }
    
}
