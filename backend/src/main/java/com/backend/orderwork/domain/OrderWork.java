package com.backend.orderwork.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.backend.detailsorderwork.domain.DetailsOrderWork;
import com.backend.orderservice.domain.OrderService;
import com.backend.serviceapproval.domain.ServiceApproval;
import com.backend.user.domain.User;
import com.backend.utils.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_works")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderWork {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "order_work_id")
	private Long id;

	@Column(length = 20)
	private Long numberOrderWork;

	@Column(name = "date_assignation")
	private LocalDate dateAsignation;

	@Column(name = "time_assignation")
	private LocalTime timeAsignation;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User employee;
	
	@ManyToOne
	@JoinColumn(name = "order_service_id")
	private OrderService orderService;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderWork")
	private List<DetailsOrderWork> detailOrderWorkList;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderWork")
	private List<ServiceApproval> serviceApprovalList;

	@PrePersist
	protected void onCreate(){
		this.dateAsignation = LocalDate.now();
		this.timeAsignation = LocalTime.now();
	}
}
