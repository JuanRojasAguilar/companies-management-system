package com.backend.servicereagent.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.servicereagent.application.ServiceReagentService;
import com.backend.servicereagent.domain.entity.ServiceReagent;
import com.backend.servicereagent.domain.entity.ServiceReagentPk;

@Service
public class ServiceReagentImpl implements ServiceReagentService {

    @Autowired
    private ServiceReagentRepository serviceReagentRepository;

    @Override
    public ServiceReagent save(ServiceReagent serviceReagent) {
        return serviceReagentRepository.save(serviceReagent);
    }

    @Override
    public Optional<ServiceReagent> update(ServiceReagentPk id, ServiceReagent serviceReagent) {
        Optional<ServiceReagent> serviceReagentDB = serviceReagentRepository.findById(id);
        if (serviceReagentDB.isPresent()) {
            ServiceReagent serviceReagentToUpload = serviceReagentDB.orElseThrow();
            BeanUtils.copyProperties(serviceReagent, serviceReagentToUpload, "id");
            return Optional.of(serviceReagentRepository.save(serviceReagentToUpload));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ServiceReagent> findById(ServiceReagentPk id) {
        return serviceReagentRepository.findById(id);
    }

    @Override
    public List<ServiceReagent> findAll() {
        return serviceReagentRepository.findAll();
    }

    @Override
    public Optional<ServiceReagent> delete(ServiceReagentPk id) {
        Optional<ServiceReagent> serviceReagent = serviceReagentRepository.findById(id);
        serviceReagent.ifPresent(serviceReagentDb -> {
           serviceReagentRepository.delete(serviceReagentDb);
        });
        return serviceReagent;
    }
    
}
