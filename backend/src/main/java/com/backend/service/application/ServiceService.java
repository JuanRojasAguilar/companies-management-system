package com.backend.service.application;

import java.util.List;
import java.util.Optional;

import com.backend.service.domain.Service;

public interface ServiceService {
    public List<Service> findAll();
    public Service save(Service service); 
    public Optional<Service> findById(Long id);
    public Optional<Service> update(Long id, Service service);
    public Optional<Service> delete(Long id);
}
