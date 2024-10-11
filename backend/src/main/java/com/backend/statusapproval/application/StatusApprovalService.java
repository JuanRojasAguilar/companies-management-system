package com.backend.statusapproval.application;

import java.util.Optional;
import java.util.Set;

import com.backend.statusapproval.domain.StatusApproval;

public interface StatusApprovalService {
	public StatusApproval save(StatusApproval statusApproval);

	public Set<StatusApproval> findAll();

	public Optional<StatusApproval> findById(Long id);

	public boolean delete(Long id);
	 
}
