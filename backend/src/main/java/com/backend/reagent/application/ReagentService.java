package com.backend.reagent.application;

import java.util.List;
import java.util.Optional;

import com.backend.reagent.domain.Reagent;

public interface ReagentService {
    public Reagent save(Reagent reagent);

    public Optional<Reagent> update(Long id, Reagent reagent);

    public Optional<Reagent> findById(Long id);
    
    public List<Reagent> findAll();

    public Optional<Reagent> delete(Long id);
}
