package com.backend.telephonetype.domain;

import java.util.List;

import com.backend.usertelephone.domain.UserTelephone;
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

	@Enumerated(EnumType.STRING)
	private Status status;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "telephoneType")
	private List<UserTelephone> userTelephoneList;
}
