package com.backend.country.domain;

import java.util.List;

import com.backend.region.domain.Region;

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
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="countries")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "country_id")
	private Long id;

	@Column(length = 25)
    @Size(max = 25, message = "the name is too large")
    @NotNull(message = "name shouldn't be null")
    @NotBlank(message = "the field is blank")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	private List<Region> regionList;
}
