package com.backend.telephonetype.application;

import java.util.Optional;
import java.util.Set;

import com.backend.telephonetype.domain.TelephoneType;

public interface TelephoneTypeService {
    Set<TelephoneType> findAll();
    Optional<TelephoneType> findById(Long id);
    Optional<TelephoneType> update(Long id, TelephoneType telephoneType);
    TelephoneType save(TelephoneType telephoneType);
    boolean delete(Long id);
}
