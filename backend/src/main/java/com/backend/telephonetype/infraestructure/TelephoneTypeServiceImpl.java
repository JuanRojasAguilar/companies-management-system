package com.backend.telephonetype.infraestructure;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.telephonetype.application.TelephoneTypeService;
import com.backend.telephonetype.domain.TelephoneType;

@Service
public class TelephoneTypeServiceImpl implements TelephoneTypeService {
    @Autowired
    private TelephoneTypeRepository repository;

    @Override
    public Set<TelephoneType> findAll() {
        Set<TelephoneType> types = new LinkedHashSet<>((List<TelephoneType>) repository.findAll());
        return types;
    }

    @Override
    public Optional<TelephoneType> findById(Long id) {
        return repository.findById(id);
    }

    @Override
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
    public TelephoneType save(TelephoneType telephoneType) {
        return repository.save(telephoneType);
    }

    @Override
    public boolean delete(Long id) {
        Optional<TelephoneType> telTypeInstance = repository.findById(id);
        if (telTypeInstance.isPresent()) {
            repository.delete(telTypeInstance.get());
            return true;
        }
        return false;
    }
}
