package com.backend.detailsorderwork.infrastructure;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.detailsorderwork.application.DetailsOrderWorkService;
import com.backend.detailsorderwork.domain.DetailsOrderWork;

@Service
public class DetailsOrderWorkServiceImpl implements DetailsOrderWorkService {
	@Autowired
	private DetailsOrderWorkRepository repository;

	@Override
	public DetailsOrderWork save(DetailsOrderWork orderWork) {
		return repository.save(orderWork);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<DetailsOrderWork> findAll() {
		Set<DetailsOrderWork> detailsOrderWorks = new LinkedHashSet<>((List<DetailsOrderWork>) repository.findAll());
		return detailsOrderWorks;
	}

	@Override
	public Optional<DetailsOrderWork> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<DetailsOrderWork> delete(Long id) {
		try {
			DetailsOrderWork detailsOrderWorkInstance = this.findById(id).get();
			repository.delete(detailsOrderWorkInstance);
			return Optional.of(detailsOrderWorkInstance);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<DetailsOrderWork> update(Long id, DetailsOrderWork orderWork) {
		Optional<DetailsOrderWork> detailsOrderWorkInstance = repository.findById(id);
		if (detailsOrderWorkInstance.isPresent()) {
			DetailsOrderWork newDetailsOrderWork = detailsOrderWorkInstance.get();
			BeanUtils.copyProperties(orderWork, newDetailsOrderWork);
			return Optional.of(repository.save(newDetailsOrderWork));
		}
		return Optional.empty();
	}
}
