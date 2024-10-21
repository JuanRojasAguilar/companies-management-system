package com.backend.service.domain;

import java.util.List;

import com.backend.companyservice.domain.CompanyService;
import com.backend.detailorder.domain.DetailOrder;
import com.backend.detailsorderwork.domain.DetailsOrderWork;
import com.backend.serviceapproval.domain.ServiceApproval;
import com.backend.servicereagent.domain.entity.ServiceReagent;
import com.backend.userreagent.domain.UserReagent;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "services")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    @Column(name = "is_reagent_needed")
    private boolean reagentNeeded;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceId")
	private List<CompanyService> companyServiceId;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
	private List<ServiceReagent> serviceReagents;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
	private List<UserReagent> userReagentList;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
	private List<DetailOrder> detailOrderList;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceAssignedId")
	private List<DetailsOrderWork> detailOrderWorkList;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
	private List<ServiceApproval> serviceApprovalList;

}
