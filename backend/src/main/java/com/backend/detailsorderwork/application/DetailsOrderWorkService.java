package com.backend.detailsorderwork.application;

import java.util.Optional;
import java.util.Set;

import com.backend.detailsorderwork.domain.DetailsOrderWorkDto;
import com.backend.detailsorderwork.domain.DetailsOrderWork;

public interface DetailsOrderWorkService {
	public DetailsOrderWork save(DetailsOrderWorkDto detailsOrderWork);

	public Set<DetailsOrderWork> findAll();

	public Optional<DetailsOrderWork> findById(Long id);

	public Optional<DetailsOrderWork> update(Long id, DetailsOrderWorkDto detailsOrderWork);

	public Optional<DetailsOrderWork> delete(Long id);
	 
}
