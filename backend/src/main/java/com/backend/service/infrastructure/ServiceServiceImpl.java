package com.backend.service.infrastructure;

import java.util.List;
import java.util.Optional;

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
    public Service save(Service service) {
        return repository.save(service);
    }

    @Override
    public Optional<Service> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Service> update(Long id, Service service) {
        Optional<Service> serviceInstance = this.findById(id);
        if (serviceInstance.isPresent()) {
            Service newService = serviceInstance.get();
            if (service.getName() != null) newService.setName(service.getName());

            repository.save(newService);
            return Optional.of(newService);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


}
