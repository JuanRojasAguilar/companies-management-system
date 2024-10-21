package com.backend.detailorder.domain;

import java.math.BigDecimal;

import com.backend.orderservice.domain.OrderService;
import com.backend.service.domain.Service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "detail_orders")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class DetailOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "detail_order_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_service_id")
	private OrderService orderService;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service serviceId;

	@DecimalMin(value = "0.00", message= "Couldn't be negative numbers")
	@Column(name = "service_value", precision = 10, scale = 2)
	@NotNull(message = "serviceValue couldn't be null")
	private BigDecimal serviceValue;
}
