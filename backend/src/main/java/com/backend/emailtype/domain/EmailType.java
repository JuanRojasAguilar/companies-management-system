package com.backend.emailtype.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "email_types")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmailType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "email_type_id")
	private Long id;

	@Column(length = 25)
	@Size(max = 25, message = "the name is too large")
	@NotNull(message = "name shouldn't be null")
	@NotBlank(message = "the field is blank")
	private String name;
}
