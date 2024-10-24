package com.backend.statusorderservice.domain;

import java.util.List;

import com.backend.detailsorderwork.domain.DetailsOrderWork;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "status_order_services")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StatusOrderService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "status_order_service_id")
	private Long id;

	@Column(length = 20)
	private String name;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "statusOrderService") 
	@JsonIgnore
	private List<DetailsOrderWork> detailsOrderWorks;

}
