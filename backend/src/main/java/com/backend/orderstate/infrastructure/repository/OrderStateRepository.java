package com.backend.orderstate.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.orderstate.domain.OrderState;

@Repository
public interface OrderStateRepository extends JpaRepository<OrderState, Long> {
    
}
