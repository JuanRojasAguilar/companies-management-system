package com.backend.statusorderservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "status_order_service")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StatusOrderService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "status_order_service_id")
	private Long id;

	@Column(length = 20)
	private String name;
	
	//TODO OneToMany implementation

}
