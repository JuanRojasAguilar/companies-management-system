package com.backend.service.application;

import java.util.List;
import java.util.Optional;

import com.backend.service.domain.Service;
import com.backend.service.domain.ServiceDto;

public interface ServiceService {
    public List<Service> findAll();
    public Service save(ServiceDto service); 
    public Optional<Service> findById(Long id);
    public Optional<Service> update(Long id, ServiceDto service);
    public Optional<Service> delete(Long id);
}
