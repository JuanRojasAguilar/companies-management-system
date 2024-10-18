package com.backend.statusapproval.domain;

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
@Table(name = "status_approvals")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StatusApproval {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "status_approval_id")
	private Long id;

	@Column(length = 20)
	private String name;
	
	//TODO OneToMany implementation

}
