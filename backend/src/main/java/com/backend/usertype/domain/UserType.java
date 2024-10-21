package com.backend.usertype.domain;

import java.util.List;

import com.backend.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "user_types")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "company_type_id")
    private Long id;

    @Column(length = 40)
    private String name;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userTypeId")
	private List<User> userList;
}
