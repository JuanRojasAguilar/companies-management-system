package com.backend.servicereagent.application;

import java.util.List;
import java.util.Optional;

import com.backend.servicereagent.domain.ServiceReagent;
import com.backend.servicereagent.domain.ServiceReagentId;

public interface ServiceReagentService {
    public ServiceReagent save(ServiceReagent serviceReagent);

    public Optional<ServiceReagent> update(ServiceReagentId id, ServiceReagent serviceReagent);

    public Optional<ServiceReagent> findById(ServiceReagentId id);
    
    public List<ServiceReagent> findAll();

    public Optional<ServiceReagent> delete(ServiceReagentId id);
}
