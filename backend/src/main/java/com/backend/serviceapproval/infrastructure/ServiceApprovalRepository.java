package com.backend.serviceapproval.infrastructure;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.serviceapproval.domain.ServiceApproval;

@Repository
public interface ServiceApprovalRepository extends JpaRepository<ServiceApproval, Long> {}
