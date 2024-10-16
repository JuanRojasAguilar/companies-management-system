package com.backend.telephonetype.domain;

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
@Table(name = "telephone_types")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TelephoneType {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "telephone_type_id")
	private Long id;

	@Column(length = 25)
	private String name;
}
