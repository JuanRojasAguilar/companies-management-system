package com.backend.servicereagent.domain;

import com.backend.reagent.domain.Reagent;
import com.backend.service.domain.Service;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ServiceReagent {

	@EmbeddedId
	@Column(name = "service_reagent_id")
	@EqualsAndHashCode.Include
	private ServiceReagentId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("reagentId")
	@JoinColumn(name = "reagent_id")
	private Reagent reagentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("serviceId")
	@JoinColumn(name = "service_id")
	private Service serviceId;

	@Column(length = 25)
	@Size(max = 25, message = "the value is too large")
	@NotNull(message = "value shouldn't be null")
	@NotBlank(message = "the field is blank")
	private Float valueUnit;

	@Column(length = 13)
	@Size(max = 13, message = "the stock is too large")
	@NotNull(message = "stock shouldn't be null")
	@NotBlank(message = "the field is blank")
	private Long stock;

	@Column(length = 10)
	@Size(max = 10, message = "the minimun stock is too large")
	@NotNull(message = "minimun stock shouldn't be null")
	@NotBlank(message = "the field is blank")
	private Long stockMin;

	@Column(length = 10)
	@Size(max = 10, message = "the maximun stock is too large")
	@NotNull(message = "maximun stock shouldn't be null")
	@NotBlank(message = "the field is blank")
	private Long stockMax;
}
