package com.backend.serviceapproval.domain;

import com.backend.orderwork.domain.OrderWork;
import com.backend.service.domain.Service;
import com.backend.statusapproval.domain.StatusApproval;
import com.backend.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "service_approvals")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServiceApproval {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "service_approval_id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "order_work_id")
	private OrderWork orderWorkId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User idClient;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;

	@Column(length = 255)
	private String findings;

	@Column(length = 255)
	private String solutions;

	@ManyToOne
	@JoinColumn(name = "status_approval_id")
	private StatusApproval statusApproval;

}
