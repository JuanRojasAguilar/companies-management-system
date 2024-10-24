package com.backend.reagent.application;

import java.util.List;
import java.util.Optional;

import com.backend.reagent.domain.Reagent;
import com.backend.reagent.domain.ReagentDto;

public interface ReagentService {
    public Reagent save(ReagentDto reagent);

    public Optional<Reagent> update(Long id, ReagentDto reagent);

    public Optional<Reagent> findById(Long id);
    
    public List<Reagent> findAll();

    public Optional<Reagent> delete(Long id);
}
