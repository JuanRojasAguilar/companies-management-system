package com.backend.franchise.application;

import java.util.List;
import java.util.Optional;

import com.backend.franchise.domain.Franchise;

public interface FranchiseService {
    List<Franchise> findAll();
    Optional<Franchise> findById(Long id);
    Optional<Franchise> update(Long id, Franchise franchise);
    Franchise save(Franchise franchise);
    Optional<Franchise> delete(Long id);
}
