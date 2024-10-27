package com.backend.usertype.domain;

import java.util.List;

import com.backend.user.domain.User;
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
@Table(name = "user_types") 
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "user_type_id")
    private Long id;

    @Column(length = 40, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userType")
	private List<User> userList;

    @JsonIgnore
    @OneToMany(mappedBy = "userType", fetch = FetchType.EAGER)
    private List<GrantedPermission> permissions;
    
}
