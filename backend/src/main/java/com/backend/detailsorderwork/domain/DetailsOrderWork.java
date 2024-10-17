package com.backend.detailsorderwork.domain;

import java.time.LocalDate;

import com.backend.orderwork.domain.OrderWork;
import com.backend.service.domain.Service;
import com.backend.statusorderservice.domain.StatusOrderService;

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
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "details_order_works")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetailsOrderWork {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "detail_order_work_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_work_id")
	private OrderWork orderWorkId;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service serviceAssignedId;

	@Column
    @NotNull(message = "date assignation shouldn't be null")
    @NotBlank(message = "the field is blank")
	private LocalDate dateAsignation;

	@ManyToOne
	@JoinColumn(name = "status_order_service_id")
	private StatusOrderService statusOrderServiceId;
}
