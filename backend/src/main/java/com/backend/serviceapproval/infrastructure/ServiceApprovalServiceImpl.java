package com.backend.serviceapproval.infrastructure;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.backend.serviceapproval.application.ServiceApprovalService;
import com.backend.serviceapproval.domain.ServiceApproval;

public class ServiceApprovalServiceImpl implements ServiceApprovalService {
	@Autowired
	private ServiceApprovalRepository repository;

	@Override
	public ServiceApproval save(ServiceApproval orderWork) {
		return repository.save(orderWork);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<ServiceApproval> findAll() {
		Set<ServiceApproval> serviceApprovals = new LinkedHashSet<>((List<ServiceApproval>) repository.findAll());
		return serviceApprovals;
	}

	@Override
	public Optional<ServiceApproval> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<ServiceApproval> delete(Long id) {
		try {
			ServiceApproval serviceApprovalInstance = this.findById(id).get();
			repository.delete(serviceApprovalInstance);
			return Optional.of(serviceApprovalInstance);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<ServiceApproval> update(Long id, ServiceApproval orderWork) {
		Optional<ServiceApproval> serviceApprovalInstance = repository.findById(id);
		if (serviceApprovalInstance.isPresent()) {
			ServiceApproval newServiceApproval = serviceApprovalInstance.get();
			BeanUtils.copyProperties(orderWork, newServiceApproval);
			return Optional.of(repository.save(newServiceApproval));
		}
		return Optional.empty();
	}
}
