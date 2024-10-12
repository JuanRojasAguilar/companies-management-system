package com.backend.statusorderservice.infrastructure;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.backend.statusorderservice.application.StatusOrderServiceService;
import com.backend.statusorderservice.domain.StatusOrderService;

public class StatusOrderServiceServiceImpl implements StatusOrderServiceService {
	@Autowired
	private StatusOrderServiceRepository repository;

	@Override
	public StatusOrderService save(StatusOrderService statusOrderService) {
		return repository.save(statusOrderService);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<StatusOrderService> findAll() {
		Set<StatusOrderService> statusOrderServices = new LinkedHashSet<>((List<StatusOrderService>) repository.findAll());
		return statusOrderServices;
	}

	@Override
	public Optional<StatusOrderService> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public boolean delete(Long id) {
		try {
			StatusOrderService statusOrderInstance = this.findById(id).get();
			repository.delete(statusOrderInstance);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Optional<StatusOrderService> update(Long id, StatusOrderService statusOrderService) {
		Optional<StatusOrderService> statusOrderServiceInstance = repository.findById(id);
		if (statusOrderServiceInstance.isPresent()) {
			StatusOrderService newStatusOrderService = statusOrderServiceInstance.get();
			BeanUtils.copyProperties(statusOrderService, newStatusOrderService);
			return Optional.of(repository.save(newStatusOrderService));
		}
		return Optional.empty();
	}
}
