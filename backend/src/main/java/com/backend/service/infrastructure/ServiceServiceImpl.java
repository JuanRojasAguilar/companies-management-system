package com.backend.service.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.backend.service.application.ServiceService;
import com.backend.service.domain.Service;

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
    public Service save(Service service) {
        return repository.save(service);
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Service> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Service> update(Long id, Service service) {
        Optional<Service> serviceInstance = this.findById(id);
        if (serviceInstance.isPresent()) {
            Service newService = serviceInstance.get();
            BeanUtils.copyProperties(service, newService);

            repository.save(newService);
            return Optional.of(newService);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Service> delete(Long id) {
        Optional<Service> serviceInstance = this.findById(id);
        if (serviceInstance.isPresent()) {
            repository.delete(serviceInstance.get());
            return Optional.of(serviceInstance.get());
        }
        return Optional.empty();
    }
}
