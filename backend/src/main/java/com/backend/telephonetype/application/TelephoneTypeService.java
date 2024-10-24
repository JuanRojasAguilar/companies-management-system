package com.backend.telephonetype.application;

import java.util.Optional;
import java.util.Set;

import com.backend.telephonetype.domain.TelephoneType;
import com.backend.telephonetype.domain.TelephoneTypeDto;

public interface TelephoneTypeService {
    Set<TelephoneType> findAll();
    Optional<TelephoneType> findById(Long id);
    Optional<TelephoneType> update(Long id, TelephoneTypeDto telephoneType);
    TelephoneType save(TelephoneTypeDto telephoneType);
    Optional<TelephoneType> delete(Long id);
}
