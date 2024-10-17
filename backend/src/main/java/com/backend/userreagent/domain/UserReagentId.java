package com.backend.userreagent.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserReagentId implements Serializable {
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "reagent_id")
	private Long reagentId;
}
