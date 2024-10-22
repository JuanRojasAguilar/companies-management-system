package com.backend.userreagent.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data	
public class UserReagentId implements Serializable {
	@Column(name = "user_id")
	private String userId;
	@Column(name = "reagent_id")
	private Long reagentId;
}
