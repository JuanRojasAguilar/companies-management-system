package com.backend.telephonetype.infraestructure;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.telephonetype.application.TelephoneTypeService;
import com.backend.telephonetype.domain.TelephoneType;
import com.backend.telephonetype.domain.TelephoneTypeDto;

@Service
public class TelephoneTypeServiceImpl implements TelephoneTypeService {
    @Autowired
    private TelephoneTypeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Set<TelephoneType> findAll() {
        Set<TelephoneType> types = new LinkedHashSet<>((List<TelephoneType>) repository.findAll());
        return types;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TelephoneType> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Optional<TelephoneType> update(Long id, TelephoneType telephoneType) {
        Optional<TelephoneType> telTypeInstance = repository.findById(id);
        if (telTypeInstance.isPresent()) {
            TelephoneType newTelType = telTypeInstance.get();
            BeanUtils.copyProperties(telephoneType, newTelType);
            return Optional.of(repository.save(newTelType));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public TelephoneType save(TelephoneTypeDto telephoneType) {
        return repository.save(telephoneType);
    }

    @Override
    @Transactional
    public Optional<TelephoneType> delete(Long id) {
        Optional<TelephoneType> telTypeInstance = repository.findById(id);
        if (telTypeInstance.isPresent()) {
            repository.delete(telTypeInstance.get());
            return Optional.of(telTypeInstance.get());
        }
        return Optional.empty();
    }
}
