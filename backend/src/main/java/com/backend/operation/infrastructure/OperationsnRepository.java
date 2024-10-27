package com.backend.operation.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.operation.domain.Operations;

public interface OperationsnRepository extends JpaRepository<Operations, Long> {
    @Query("SELECT o FROM Operations o where o.permitAll = true")
    List<Operations> findByPubliccAcces();
}
