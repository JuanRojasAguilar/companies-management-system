package com.backend.companyservice.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CompanyServiceId implements Serializable {
	@Column(name = "franchise_id")
	private Long franchiseId;
	@Column(name = "service_id")
	private Long serviceId;
}
