package com.backend.servicereagent.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.reagent.domain.Reagent;
import com.backend.servicereagent.application.ServiceReagentService;
import com.backend.servicereagent.domain.ServiceReagent;
import com.backend.servicereagent.domain.ServiceReagentDto;
import com.backend.servicereagent.domain.ServiceReagentPk;
import com.backend.utils.enums.Status;

@Service
public class ServiceReagentImpl implements ServiceReagentService {

    @Autowired
    private ServiceReagentRepository serviceReagentRepository;

    @Override
    public ServiceReagent save(ServiceReagentDto serviceReagent) {
        ServiceReagent serviceReagentDb = new ServiceReagent();
        serviceReagentDb.setUnitValue(serviceReagent.getUnitValue());
        serviceReagentDb.setStock(serviceReagent.getStock());
        serviceReagentDb.setStockMax(serviceReagent.getStockMax());
        serviceReagentDb.setStockMin(serviceReagent.getStockMin());
        serviceReagentDb.setStatus(Status.ENABLED);

        com.backend.service.domain.Service service = new com.backend.service.domain.Service();
        service.setId(serviceReagent.getServiceId());
        serviceReagentDb.setService(service);

        Reagent reagent = new Reagent();
        reagent.setId(serviceReagent.getReagentId());
        serviceReagentDb.setReagent(reagent);

        return serviceReagentRepository.save(serviceReagentDb);
    }

    @Override
    public Optional<ServiceReagent> update(ServiceReagentPk id, ServiceReagentDto serviceReagent) {
        Optional<ServiceReagent> serviceReagentDB = serviceReagentRepository.findById(id);
        if (serviceReagentDB.isPresent()) {
            ServiceReagent serviceReagentDb = new ServiceReagent();
            serviceReagentDb.setUnitValue(serviceReagent.getUnitValue());
            serviceReagentDb.setStock(serviceReagent.getStock());
            serviceReagentDb.setStockMax(serviceReagent.getStockMax());
            serviceReagentDb.setStockMin(serviceReagent.getStockMin());

            com.backend.service.domain.Service service = new com.backend.service.domain.Service();
            service.setId(serviceReagent.getServiceId());
            serviceReagentDb.setService(service);

            Reagent reagent = new Reagent();
            reagent.setId(serviceReagent.getReagentId());
            serviceReagentDb.setReagent(reagent);

            return Optional.of(serviceReagentRepository.save(serviceReagentDb));
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
        Optional<ServiceReagent> serviceReagentInstance = this.findById(id);
        if (serviceReagentInstance.isPresent()) {
            serviceReagentInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(serviceReagentRepository.save(serviceReagentInstance.orElseThrow()));
        }
            return Optional.empty();
    }
    
}
