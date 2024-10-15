package com.backend.orderwork.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
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

	@Column
	private LocalDate dateAsignation;

	@Column
	private LocalTime timeAsignation;

	@ManyToOne
	@JoinCoulmn(name = "user_id")
	private User employeeId;
	
	@ManyToOne
	@JoinColumn(name = "order_service_id")
	private OrderService orderServiceId;

	// TODO import person and order service Entities

}
