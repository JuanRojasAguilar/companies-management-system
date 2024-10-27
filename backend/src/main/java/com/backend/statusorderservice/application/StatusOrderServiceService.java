package com.backend.statusorderservice.application;

import java.util.Optional;
import java.util.Set;

import com.backend.statusorderservice.domain.StatusOrderService;
import com.backend.statusorderservice.domain.StatusOrderServiceDto;

public interface StatusOrderServiceService {
	public StatusOrderService save(StatusOrderServiceDto statusOrderService);

	public Set<StatusOrderService> findAll();

	public Optional<StatusOrderService> findById(Long id);

	public Optional<StatusOrderService> update(Long id, StatusOrderServiceDto statusOrderService); 

	public Optional<StatusOrderService> delete(Long id);
	 
}
