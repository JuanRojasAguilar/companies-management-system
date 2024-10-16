package com.backend.franchise.application;

import java.util.List;
import java.util.Optional;

import com.backend.franchise.domain.Franchise;

public interface FranchiseService {
    public List<Franchise> findAll();
    public Optional<Franchise> findById(Long id);
    public Optional<Franchise> update(Long id, Franchise franchise);
    public Franchise save(Franchise franchise);
    public Optional<Franchise> delete(Long id);
}
