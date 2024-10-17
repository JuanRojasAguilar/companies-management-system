package com.backend.companyservice.domain;

import com.backend.franchise.domain.Franchise;
import com.backend.service.domain.Service;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CompanyService {

	@EmbeddedId
	@Column(name = "company_service_id")
	@EqualsAndHashCode.Include
	private CompanyServiceId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("franchiseId")
	@JoinColumn(name = "franchise_id")
	private Franchise franchiseId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("serviceId")
	@JoinColumn(name = "service_id")
	private Service serviceId;

	@Column(length = 25)
	@Size(max = 25, message = "the value is too large")
	@NotNull(message = "value shouldn't be null")
	@NotBlank(message = "the field is blank")
	private Float valueService;
}
