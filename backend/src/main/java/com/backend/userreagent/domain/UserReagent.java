package com.backend.userreagent.domain;

import com.backend.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.backend.reagent.domain.Reagent;
import com.backend.service.domain.Service;
import com.backend.user.domain.User;

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
	private User userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("reagentId")
	@JoinColumn(name = "reagent_id")
	private Reagent reagentId;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service serviceId;
}
