package com.backend.franchise.infraestructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.franchise.application.FranchiseService;
import com.backend.franchise.domain.Franchise;

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
    public Optional<Franchise> update(Long id, Franchise franchise) {
        Optional<Franchise> franchiseInstance = repository.findById(id);
        if (franchiseInstance.isPresent()) {
            Franchise newFranchise = franchiseInstance.get();
            BeanUtils.copyProperties(franchise, newFranchise);
            return Optional.of(repository.save(newFranchise));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Franchise save(Franchise franchise) {
        return repository.save(franchise);
    }

    @Override
    public Optional<Franchise> delete(Long id) {
        Optional<Franchise> franchiseInstance = repository.findById(id);
        if (franchiseInstance.isPresent()) {
            repository.delete(franchiseInstance.get());
            return Optional.of(franchiseInstance).orElseThrow();
        }
        return Optional.empty();
    }
}
