package com.backend.reagent.domain;

import java.util.List;

import com.backend.servicereagent.domain.ServiceReagent;
import com.backend.userreagent.domain.UserReagent;
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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reagents")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reagent {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reagent_id")
    private Long id;

    @Column(unique = true)
    private String serial;

    @Column(length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reagent")
	private List<ServiceReagent> serviceReagentList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reagent")
    private List<UserReagent> userReagents;
}
