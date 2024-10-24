package com.backend.userreagent.domain;

import com.backend.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import com.backend.utils.enums.Status;
import jakarta.persistence.JoinColumn;
import com.backend.reagent.domain.Reagent;
import com.backend.service.domain.Service;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UserReagent {

	@EmbeddedId
	@Column(name = "user_reagent_id")
	@EqualsAndHashCode.Include
	private UserReagentId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("reagentId")
	@JoinColumn(name = "reagent_id")
	private Reagent reagent;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;
}
