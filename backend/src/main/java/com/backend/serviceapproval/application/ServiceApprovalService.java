package com.backend.serviceapproval.application;

import java.util.Optional;
import java.util.Set;

import com.backend.serviceapproval.domain.ServiceApproval;

public interface ServiceApprovalService {
	public ServiceApproval save(ServiceApproval serviceApproval);

	public Set<ServiceApproval> findAll();

	public Optional<ServiceApproval> findById(Long id);

	public Optional<ServiceApproval> update(Long id, ServiceApproval serviceApproval);

	public Optional<ServiceApproval> delete(Long id);
	 
}
