package com.backend.reagent.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.reagent.domain.Reagent;

@Repository
public interface ReagentRepository extends JpaRepository<Reagent, Long>{
    
}
