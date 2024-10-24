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
import com.backend.detailsorderwork.domain.DetailsOrderWorkDto;
import com.backend.orderwork.domain.OrderWork;
import com.backend.statusorderservice.domain.StatusOrderService;
import com.backend.utils.enums.Status;

@Service
public class DetailsOrderWorkServiceImpl implements DetailsOrderWorkService {
	@Autowired
	private DetailsOrderWorkRepository repository;

	@Override
	public DetailsOrderWork save(DetailsOrderWorkDto detailsOrderWork) {
		DetailsOrderWork detailsOrderWorkDb = new DetailsOrderWork();
			BeanUtils.copyProperties(detailsOrderWork, detailsOrderWorkDb, detailsOrderWork.getClass());
			detailsOrderWorkDb.setStatus(Status.ENABLED);

			OrderWork orderWork = new OrderWork();
			orderWork.setId(detailsOrderWork.getOrderWorkId());
			detailsOrderWorkDb.setOrderWork(orderWork);

			com.backend.service.domain.Service service = new com.backend.service.domain.Service();
			service.setId(detailsOrderWork.getServiceAssignedId());
			detailsOrderWorkDb.setServiceAssigned(service);

			StatusOrderService statusOrderService = new StatusOrderService();
			statusOrderService.setId(detailsOrderWork.getStatusOrderServiceId());
			detailsOrderWorkDb.setStatusOrderService(statusOrderService);

			return repository.save(detailsOrderWorkDb);
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
		Optional<DetailsOrderWork> detailsOrderWork = this.findById(id);
		if (detailsOrderWork.isPresent()) {
			detailsOrderWork.orElseThrow().setStatus(Status.DISABLED);
			return Optional.of(repository.save(detailsOrderWork.orElseThrow()));
		}
	    	return Optional.empty();
	}

	@Override
	public Optional<DetailsOrderWork> update(Long id, DetailsOrderWorkDto detailsOrderWork) {
		Optional<DetailsOrderWork> detailsOrderWorkInstance = repository.findById(id);
		if (detailsOrderWorkInstance.isPresent()) {
			DetailsOrderWork detailsOrderWorkDb = new DetailsOrderWork();
			BeanUtils.copyProperties(detailsOrderWork, detailsOrderWorkDb, detailsOrderWork.getClass());

			OrderWork orderWork = new OrderWork();
			orderWork.setId(detailsOrderWork.getOrderWorkId());
			detailsOrderWorkDb.setOrderWork(orderWork);

			com.backend.service.domain.Service service = new com.backend.service.domain.Service();
			service.setId(detailsOrderWork.getServiceAssignedId());
			detailsOrderWorkDb.setServiceAssigned(service);

			StatusOrderService statusOrderService = new StatusOrderService();
			statusOrderService.setId(detailsOrderWork.getStatusOrderServiceId());
			detailsOrderWorkDb.setStatusOrderService(statusOrderService);

			return Optional.of(repository.save(detailsOrderWorkDb));
		}
		return Optional.empty();
	}
}
