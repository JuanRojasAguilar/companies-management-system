package com.backend.orderservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.orderservice.domain.OrderService;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {
    
}
