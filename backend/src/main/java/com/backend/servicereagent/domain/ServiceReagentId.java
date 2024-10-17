package com.backend.servicereagent.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ServiceReagentId implements Serializable {
	@Column(name = "reagent_id")
	private Long franchiseId;
	@Column(name = "service_id")
	private Long serviceId;
}
