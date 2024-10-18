package com.backend.statusorderservice.application;

import java.util.Optional;
import java.util.Set;

import com.backend.statusorderservice.domain.StatusOrderService;

public interface StatusOrderServiceService {
	public StatusOrderService save(StatusOrderService statusOrderService);

	public Set<StatusOrderService> findAll();

	public Optional<StatusOrderService> findById(Long id);

	public Optional<StatusOrderService> update(Long id, StatusOrderService statusOrderService); 

	public Optional<StatusOrderService> delete(Long id);
	 
}
