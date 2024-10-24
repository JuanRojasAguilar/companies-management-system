package com.backend.detailsorderwork.domain;

import java.time.LocalDate;

import com.backend.orderwork.domain.OrderWork;
import com.backend.service.domain.Service;
import com.backend.statusorderservice.domain.StatusOrderService;
import com.backend.utils.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "details_order_works")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class DetailsOrderWork {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "detail_order_work_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_work_id")
	private OrderWork orderWork;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service serviceAssigned;

	@Column(name = "date_asignation")
	private LocalDate dateAsignation;

	@ManyToOne
	@JoinColumn(name = "status_order_service_id")
	private StatusOrderService statusOrderService;

	@Enumerated(EnumType.STRING)
	Status status;

	@PrePersist
	protected void onCreate() {
			this.dateAsignation = LocalDate.now();
	}
}
