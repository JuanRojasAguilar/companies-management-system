package com.backend.orderwork.infrastructure;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.orderwork.domain.OrderWork;


@Repository
public interface OrderWorkRepository extends JpaRepository<OrderWork, Long> {}
