package com.backend.reagent.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.reagent.application.ReagentService;
import com.backend.reagent.domain.Reagent;

@Service
public class ReagentImpl implements ReagentService {
    @Autowired
    private ReagentRepository reagentRepository;

    @Transactional
    @Override
    public Reagent save(Reagent reagent) {
        return reagentRepository.save(reagent);
    }

    @Transactional
    @Override
    public Optional<Reagent> update(Long id, Reagent reagent) {
        Optional<Reagent> reagentDb = reagentRepository.findById(id);
        if (reagentDb.isPresent()) {
            Reagent reagentToUpload = reagentDb.orElseThrow();
            BeanUtils.copyProperties(reagent, reagentToUpload, "id");
            return Optional.of(reagentRepository.save(reagentToUpload));
        }
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Reagent> findById(Long id) {
        return reagentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Reagent> findAll() {
        return reagentRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Reagent> delete(Long id) {
        Optional<Reagent> reagentToDelete = reagentRepository.findById(id);
        reagentToDelete.ifPresent( reagent ->
            reagentRepository.delete(reagent)
        );
        return reagentToDelete;
    }
}
