package com.backend.servicereagent.application;

import java.util.List;
import java.util.Optional;

import com.backend.servicereagent.domain.ServiceReagent;
import com.backend.servicereagent.domain.ServiceReagentDto;
import com.backend.servicereagent.domain.ServiceReagentPk;

public interface ServiceReagentService {
    public ServiceReagent save(ServiceReagentDto serviceReagent);

    public Optional<ServiceReagent> update(ServiceReagentPk id, ServiceReagentDto serviceReagent);

    public Optional<ServiceReagent> findById(ServiceReagentPk id);
    
    public List<ServiceReagent> findAll();

    public Optional<ServiceReagent> delete(ServiceReagentPk id);
}
