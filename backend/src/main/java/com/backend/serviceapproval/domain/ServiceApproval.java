package com.backend.serviceapproval.domain;

import com.backend.service.domain.Service;
import com.backend.statusapproval.domain.StatusApproval;
import com.backend.user.domain.User;
import com.backend.utils.enums.Status;
import com.backend.orderwork.domain.OrderWork;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_approvals")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServiceApproval {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "service_approval_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_work_id")
	private OrderWork orderWork;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User client;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;

	@Column(length = 255)
	private String findings;

	@Column(length = 255)
	private String solutions;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "status_approval_id")
	private StatusApproval statusApproval;

}
