package com.backend.franchise.infraestructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.franchise.application.FranchiseService;
import com.backend.franchise.domain.Franchise;

@Service
public class FranshiceServiceImpl implements FranchiseService {
    @Autowired
    private FranchiseRepository repository;

    @Override
    public List<Franchise> findAll() {
        return (List<Franchise>) repository.findAll();
    }

    @Override
    public Optional<Franchise> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Franchise> update(Long id, Franchise franchise) {
        Optional<Franchise> franchiseInstance = repository.findById(id);
        if (franchiseInstance.isPresent()) {
            Franchise newFranchise = franchiseInstance.get();
            BeanUtils.copyProperties(newFranchise, franchise);
            return Optional.of(repository.save(newFranchise));
        }
        return Optional.empty();
    }

    @Override
    public Franchise save(Franchise franchise) {
        return repository.save(franchise);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Franchise> franchiseInstance = repository.findById(id);
        if (franchiseInstance.isPresent()) {
            repository.delete(franchiseInstance.get());
            return true;
        }
        return false;
    }
}
