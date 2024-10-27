package com.backend.statusapproval.application;

import java.util.Optional;
import java.util.Set;

import com.backend.statusapproval.domain.StatusApproval;
import com.backend.statusapproval.domain.StatusApprovalDto;

public interface StatusApprovalService {
	StatusApproval save(StatusApprovalDto statusApproval);

	Set<StatusApproval> findAll();

	Optional<StatusApproval> findById(Long id);

	Optional<StatusApproval> update(Long id, StatusApprovalDto statusApproval);

	Optional<StatusApproval> delete(Long id);
}
