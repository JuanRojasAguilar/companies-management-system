package com.backend.detailorder.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.detailorder.domain.DetailOrder;

@Repository
public interface DetailOrderRepository extends JpaRepository<DetailOrder, Long> {

}
