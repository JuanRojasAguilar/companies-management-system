package com.backend.reagent.domain;

import java.util.List;

import com.backend.servicereagent.domain.entity.ServiceReagent;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
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

    @NotNull(message = "serial shouldn't be null")
    @NotBlank(message = "serial is blank")
    @Size(max = 100, message = "serial size exceeded (100)")
    @Column(unique = true)
    private String serial;

    @Column(length = 100)
    @Size(max = 100, message = "the name is too large")
    @NotNull(message = "name shouldn't be null")
    @NotBlank(message = "the field is blank")
    private String name;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reagent")
	private List<ServiceReagent> serviceReagentList;
}
