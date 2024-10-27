package com.backend.reagent.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.reagent.application.ReagentService;
import com.backend.reagent.domain.Reagent;
import com.backend.reagent.domain.ReagentDto;
import com.backend.utils.enums.Status;

@Service
public class ReagentImpl implements ReagentService {
    @Autowired
    private ReagentRepository reagentRepository;

    @Transactional
    @Override
    public Reagent save(ReagentDto reagent) {
        Reagent reagentDb = new Reagent();
        reagentDb.setName(reagent.getName());
        reagentDb.setSerial(reagent.getSerial());
        reagentDb.setStatus(Status.ENABLED);

        return reagentRepository.save(reagentDb);
    }

    @Transactional
    @Override
    public Optional<Reagent> update(Long id, ReagentDto reagent) {
        Optional<Reagent> reagentDb = reagentRepository.findById(id);
        if (reagentDb.isPresent()) {
            Reagent reagentToUpload = new Reagent();
            reagentToUpload.setName(reagent.getName());
            reagentToUpload.setSerial(reagent.getSerial());

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
        Optional<Reagent> reagentInstance = this.findById(id);
        if (reagentInstance.isPresent()) {
            reagentInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(reagentRepository.save(reagentInstance.orElseThrow()));
        }
            return Optional.empty();
    }
}
