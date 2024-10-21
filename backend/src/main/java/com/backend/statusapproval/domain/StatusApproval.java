package com.backend.statusapproval.domain;

import java.util.List;

import com.backend.serviceapproval.domain.ServiceApproval;
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

@Data
@Entity
@Table(name = "status_approvals")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StatusApproval {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "status_approval_id")
	private Long id;

	@Column(length = 20)
	private String name;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "statusApprovalId")
	private List<ServiceApproval> serviceApprovalList;

}
