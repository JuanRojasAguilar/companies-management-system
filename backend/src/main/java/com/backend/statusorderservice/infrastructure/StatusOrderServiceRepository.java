package com.backend.statusorderservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.statusorderservice.domain.StatusOrderService;

@Repository
public interface StatusOrderServiceRepository extends JpaRepository<StatusOrderService, Long> {
}
