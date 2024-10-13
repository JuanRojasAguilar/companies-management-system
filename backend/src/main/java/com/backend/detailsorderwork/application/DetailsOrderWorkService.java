package com.backend.detailsorderwork.application;

import java.util.Optional;
import java.util.Set;

import com.backend.detailsorderwork.domain.DetailsOrderWork;

public interface DetailsOrderWorkService {
	public DetailsOrderWork save(DetailsOrderWork detailsOrderWork);

	public Set<DetailsOrderWork> findAll();

	public Optional<DetailsOrderWork> findById(Long id);

	public Optional<DetailsOrderWork> update(Long id, DetailsOrderWork detailsOrderWork);

	public Optional<DetailsOrderWork> delete(Long id);
	 
}
