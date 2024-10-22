package com.backend.companyservice.domain;

import java.math.BigDecimal;

import com.backend.franchise.domain.Franchise;
import com.backend.service.domain.Service;
import com.backend.utils.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "companies_services")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CompanyService {

	@EmbeddedId
	@Column(name = "company_service_id")
	@EqualsAndHashCode.Include
	private CompanyServiceId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("franchiseId")
	@JoinColumn(name = "franchise_id")
	private Franchise franchise;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("serviceId")
	@JoinColumn(name = "service_id")
	private Service service;

	@Column(length = 25)
	private BigDecimal valueService;

	@Enumerated(EnumType.STRING)
	private Status status;
}
