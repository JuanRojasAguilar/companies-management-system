package com.backend.emailtype.domain;

import java.util.List;

import com.backend.emailuser.domain.EmailUser;
import com.backend.utils.enums.Status;

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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "emailType")
	private List<EmailUser> emailUser;

	@Enumerated(EnumType.STRING)
	Status status;
}
