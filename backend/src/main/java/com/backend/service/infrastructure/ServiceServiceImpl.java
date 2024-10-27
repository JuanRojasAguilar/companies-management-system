package com.backend.service.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.backend.service.application.ServiceService;
import com.backend.service.domain.Service;
import com.backend.service.domain.ServiceDto;
import com.backend.utils.enums.Status;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository repository;

    @Override
    @Transactional(readOnly=true)
    public List<Service> findAll() {
        return (List<Service>) repository.findAll();
    }

    @Override
    @Transactional
    public Service save(ServiceDto service) {
        Service serviceDb = new Service();
        serviceDb.setName(service.getName());
        serviceDb.setReagentNeeded(service.isReagentNeeded());
        serviceDb.setStatus(Status.ENABLED);

        return repository.save(serviceDb);
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Service> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Service> update(Long id, ServiceDto service) {
        Optional<Service> serviceInstance = repository.findById(id);
        if (serviceInstance.isPresent()) {
            Service serviceDb = new Service();
            serviceDb.setName(service.getName());
            serviceDb.setReagentNeeded(service.isReagentNeeded());

            return Optional.of(repository.save(serviceDb));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Service> delete(Long id) {
        Optional<Service> serviceInstance = this.findById(id);
        if (serviceInstance.isPresent()) {
            serviceInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(repository.save(serviceInstance.orElseThrow()));
        }
        return Optional.empty();
    }
}
