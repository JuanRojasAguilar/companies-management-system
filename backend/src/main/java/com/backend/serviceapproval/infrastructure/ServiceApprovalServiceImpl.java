package com.backend.serviceapproval.infrastructure;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.orderwork.domain.OrderWork;
import com.backend.serviceapproval.application.ServiceApprovalService;
import com.backend.serviceapproval.domain.ServiceApproval;
import com.backend.serviceapproval.domain.ServiceApprovalDto;
import com.backend.statusapproval.domain.StatusApproval;
import com.backend.user.domain.User;
import com.backend.utils.enums.Status;

@Service
public class ServiceApprovalServiceImpl implements ServiceApprovalService {
	@Autowired
	private ServiceApprovalRepository repository;

	@Override
	public ServiceApproval save(ServiceApprovalDto serviceApproval) {
		ServiceApproval serviceApprovalDb = new ServiceApproval();
		serviceApprovalDb.setFindings(serviceApproval.getFindings());
		serviceApprovalDb.setSolutions(serviceApprovalDb.getSolutions());
		serviceApprovalDb.setStatus(Status.ENABLED);

		com.backend.service.domain.Service service = new com.backend.service.domain.Service();
		service.setId(serviceApproval.getServiceId());
		serviceApprovalDb.setService(service);

		OrderWork orderWork = new OrderWork();
		orderWork.setId(serviceApproval.getOrderWorkId());
		serviceApprovalDb.setOrderWork(orderWork);

		User client = new User();
		client.setId(serviceApproval.getClientId());
		serviceApprovalDb.setClient(client);

		StatusApproval statusApproval = new StatusApproval();
		statusApproval.setId(serviceApproval.getStatusApprovalId());
		serviceApprovalDb.setStatusApproval(statusApproval);

		return repository.save(serviceApprovalDb);

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
		Optional<ServiceApproval> serviceApproval = this.findById(id);
        if (serviceApproval.isPresent()) {
            serviceApproval.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(repository.save(serviceApproval.orElseThrow()));
        }
            return Optional.empty();

	}

	@Override
	public Optional<ServiceApproval> update(Long id, ServiceApprovalDto serviceApproval) {
		Optional<ServiceApproval> serviceApprovalInstance = repository.findById(id);
		if (serviceApprovalInstance.isPresent()) {
			ServiceApproval serviceApprovalDb = new ServiceApproval();
			serviceApprovalDb.setFindings(serviceApproval.getFindings());
			serviceApprovalDb.setSolutions(serviceApprovalDb.getSolutions());

			com.backend.service.domain.Service service = new com.backend.service.domain.Service();
			service.setId(serviceApproval.getServiceId());
			serviceApprovalDb.setService(service);

			OrderWork orderWork = new OrderWork();
			orderWork.setId(serviceApproval.getOrderWorkId());
			serviceApprovalDb.setOrderWork(orderWork);

			User client = new User();
			client.setId(serviceApproval.getClientId());
			serviceApprovalDb.setClient(client);

			StatusApproval statusApproval = new StatusApproval();
			statusApproval.setId(serviceApproval.getStatusApprovalId());
			serviceApprovalDb.setStatusApproval(statusApproval);

			return Optional.of(repository.save(serviceApprovalDb));
		}
		return Optional.empty();
	}
}
