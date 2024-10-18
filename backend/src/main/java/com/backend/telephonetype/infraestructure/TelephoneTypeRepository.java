package com.backend.telephonetype.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.telephonetype.domain.TelephoneType;

@Repository
public interface TelephoneTypeRepository extends JpaRepository<TelephoneType, Long> {}
