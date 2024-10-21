package com.backend.servicereagent.application;

import java.util.List;
import java.util.Optional;

import com.backend.servicereagent.domain.entity.ServiceReagent;
import com.backend.servicereagent.domain.entity.ServiceReagentPk;

public interface ServiceReagentService {
    public ServiceReagent save(ServiceReagent serviceReagent);

    public Optional<ServiceReagent> update(ServiceReagentPk id, ServiceReagent serviceReagent);

    public Optional<ServiceReagent> findById(ServiceReagentPk id);
    
    public List<ServiceReagent> findAll();

    public Optional<ServiceReagent> delete(ServiceReagentPk id);
}
