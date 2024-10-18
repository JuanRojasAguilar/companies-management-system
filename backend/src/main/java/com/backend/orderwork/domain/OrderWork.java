package com.backend.orderwork.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import com.backend.orderservice.domain.OrderService;
import com.backend.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 20, message = "the number order work is too large")
    @NotNull(message = "number order work shouldn't be null")
    @NotBlank(message = "the field is blank")
	private Long numberOrderWork;

	@Column
    @NotNull(message = "date assignation shouldn't be null")
    @NotBlank(message = "the field is blank")
	private LocalDate dateAsignation;

	@Column
    @NotNull(message = "time assignation shouldn't be null")
    @NotBlank(message = "the field is blank")
	private LocalTime timeAsignation;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User employeeId;
	
	@ManyToOne
	@JoinColumn(name = "order_service_id")
	private OrderService orderServiceId;
}
