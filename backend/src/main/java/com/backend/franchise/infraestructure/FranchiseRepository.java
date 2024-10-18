package com.backend.franchise.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.franchise.domain.Franchise;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {}
