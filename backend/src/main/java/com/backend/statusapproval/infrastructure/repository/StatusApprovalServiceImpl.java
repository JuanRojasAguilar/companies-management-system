package com.backend.statusapproval.infrastructure.repository;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.backend.statusapproval.application.StatusApprovalService;
import com.backend.statusapproval.domain.StatusApproval;

public class StatusApprovalServiceImpl implements StatusApprovalService {
	@Autowired
	private StatusApprovalRepository repository;

	@Override
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
	public Optional<StatusApproval> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public boolean delete(Long id) {
		try {
			StatusApproval statusInstance = this.findById(id).get();
			repository.delete(statusInstance);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
