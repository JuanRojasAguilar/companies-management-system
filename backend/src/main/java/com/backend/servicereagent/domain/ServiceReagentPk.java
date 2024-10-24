package com.backend.servicereagent.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ServiceReagentPk {
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "reagent_id")
    private Long reagentId;
}
