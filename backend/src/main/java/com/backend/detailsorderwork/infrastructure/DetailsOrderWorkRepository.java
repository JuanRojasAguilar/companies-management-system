package com.backend.detailsorderwork.infrastructure;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.detailsorderwork.domain.DetailsOrderWork;


@Repository
public interface DetailsOrderWorkRepository extends JpaRepository<DetailsOrderWork, Long> {}
