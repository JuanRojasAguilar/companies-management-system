package com.backend.serviceapproval.application;

import java.util.Optional;
import java.util.Set;

import com.backend.serviceapproval.domain.ServiceApproval;
import com.backend.serviceapproval.domain.ServiceApprovalDto;

public interface ServiceApprovalService {
	public ServiceApproval save(ServiceApprovalDto serviceApproval);

	public Set<ServiceApproval> findAll();

	public Optional<ServiceApproval> findById(Long id);

	public Optional<ServiceApproval> update(Long id, ServiceApprovalDto serviceApproval);

	public Optional<ServiceApproval> delete(Long id);
	 
}
