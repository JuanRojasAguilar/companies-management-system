package com.backend.statusorderservice.infrastructure;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.statusorderservice.application.StatusOrderServiceService;
import com.backend.statusorderservice.domain.StatusOrderService;
import com.backend.statusorderservice.domain.StatusOrderServiceDto;
import com.backend.utils.enums.Status;

@Service
public class StatusOrderServiceServiceImpl implements StatusOrderServiceService {
	@Autowired
	private StatusOrderServiceRepository repository;

	@Override
	public StatusOrderService save(StatusOrderServiceDto statusOrderService) {
		StatusOrderService statusOrderServiceDb = new StatusOrderService();
		statusOrderServiceDb.setName(statusOrderService.getName());
		statusOrderServiceDb.setStatus((Status.ENABLED));

		return repository.save(statusOrderServiceDb);
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
	public Optional<StatusOrderService> delete(Long id) {
		Optional<StatusOrderService> statusOrderServiceInstance = this.findById(id);
        if (statusOrderServiceInstance.isPresent()) {
            statusOrderServiceInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(repository.save(statusOrderServiceInstance.orElseThrow()));
        }
            return Optional.empty();
	}

	@Override
	public Optional<StatusOrderService> update(Long id, StatusOrderServiceDto statusOrderService) {
		Optional<StatusOrderService> statusOrderServiceInstance = repository.findById(id);
		if (statusOrderServiceInstance.isPresent()) {
			StatusOrderService statusOrderServiceDb = new StatusOrderService();
			statusOrderServiceDb.setName(statusOrderService.getName());

			return Optional.of(repository.save(statusOrderServiceDb));
		}
		return Optional.empty();
	}
}
