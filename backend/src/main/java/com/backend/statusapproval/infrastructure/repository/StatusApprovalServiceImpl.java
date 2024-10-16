package com.backend.statusapproval.infrastructure.repository;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.statusapproval.application.StatusApprovalService;
import com.backend.statusapproval.domain.StatusApproval;

@Service
public class StatusApprovalServiceImpl implements StatusApprovalService {
	@Autowired
	private StatusApprovalRepository repository;

	@Override
	@Transactional
	public StatusApproval save(StatusApproval statusApproval) {
		return repository.save(statusApproval);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<StatusApproval> findAll() {
		Set<StatusApproval> statusApprovals = new LinkedHashSet<>((List<StatusApproval>) repository.findAll());
		return statusApprovals;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<StatusApproval> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Optional<StatusApproval> delete(Long id) {
		Optional<StatusApproval> statusInstance = repository.findById(id);
		if (statusInstance.isPresent()) {
			repository.delete(statusInstance.get());
			return Optional.of(statusInstance.get());
		}
		return Optional.empty();
	}

	@Override
	public Optional<StatusApproval> update(Long id, StatusApproval statusApproval) {
		Optional<StatusApproval> statusInstance = repository.findById(id);
		if (statusInstance.isPresent()) {
			StatusApproval newStatus = statusInstance.get();
			BeanUtils.copyProperties(statusApproval, newStatus);
			return Optional.of(repository.save(newStatus));
		}
		return Optional.empty();
	}
}
