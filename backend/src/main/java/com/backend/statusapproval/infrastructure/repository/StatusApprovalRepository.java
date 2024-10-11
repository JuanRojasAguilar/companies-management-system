package com.backend.statusapproval.infrastructure.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.statusapproval.domain.StatusApproval;


@Repository
public interface StatusApprovalRepository extends JpaRepository<StatusApproval, Long> {}
