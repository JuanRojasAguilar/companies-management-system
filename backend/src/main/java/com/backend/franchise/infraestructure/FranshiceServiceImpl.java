package com.backend.franchise.infraestructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.city.domain.City;
import com.backend.company.domain.Company;
import com.backend.franchise.application.FranchiseService;
import com.backend.franchise.domain.Franchise;
import com.backend.franchise.domain.FranchiseDto;
import com.backend.utils.enums.Status;

@Service
public class FranshiceServiceImpl implements FranchiseService {
    @Autowired
    private FranchiseRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Franchise> findAll() {
        return (List<Franchise>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Franchise> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Franchise> update(Long id, FranchiseDto franchise) {
        Optional<Franchise> franchiseInstance = repository.findById(id);
        if (franchiseInstance.isPresent()) {
            Franchise franchiseDb = new Franchise(); 
            BeanUtils.copyProperties(franchise, franchiseDb, franchise.getClass());

            City city = new City();
            city.setId(franchise.getCityId());
            franchiseDb.setCity(city);

            Company company = new Company();
            company.setId(franchise.getCompanyId());
            franchiseDb.setCompany(company);

            return Optional.of(repository.save(franchiseDb));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Franchise save(FranchiseDto franchise) {
        Franchise franchiseDb = new Franchise(); 
        BeanUtils.copyProperties(franchise, franchiseDb, franchise.getClass());
        franchiseDb.setStatus(Status.ENABLED);

        City city = new City();
        city.setId(franchise.getCityId());
        franchiseDb.setCity(city);

        Company company = new Company();
        company.setId(franchise.getCompanyId());
        franchiseDb.setCompany(company);

        return repository.save(franchiseDb);
    }

    @Override
    @Transactional
    public Optional<Franchise> delete(Long id) {
        Optional<Franchise> franchiseInstance = repository.findById(id);
        if (franchiseInstance.isPresent()) {
            franchiseInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(repository.save(franchiseInstance.orElseThrow()));
	    }
        return Optional.empty();   
    }
}
