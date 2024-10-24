package com.backend.statusapproval.infrastructure;


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
import com.backend.statusapproval.domain.StatusApprovalDto;
import com.backend.utils.enums.Status;

@Service
public class StatusApprovalServiceImpl implements StatusApprovalService {
	@Autowired
	private StatusApprovalRepository repository;

	@Override
	public StatusApproval save(StatusApprovalDto statusApproval) {
		StatusApproval statusApprovalDb = new StatusApproval();
		BeanUtils.copyProperties(statusApproval, statusApprovalDb, statusApproval.getClass());
		statusApprovalDb.setStatus(Status.ENABLED);
		
		return repository.save(statusApprovalDb);
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
	public Optional<StatusApproval> delete(Long id) {
		try {
			StatusApproval statusInstance = this.findById(id).get();
			repository.delete(statusInstance);
			return Optional.of(statusInstance);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<StatusApproval> update(Long id, StatusApprovalDto statusApproval) {
		Optional<StatusApproval> statusApprovalInstance = this.findById(id);
        if (statusApprovalInstance.isPresent()) {
            statusApprovalInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(repository.save(statusApprovalInstance.orElseThrow()));
        }
            return Optional.empty();
	}
}
