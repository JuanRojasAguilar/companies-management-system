package com.backend.city.domain;

import java.util.List;

import com.backend.franchise.domain.Franchise;
import com.backend.region.domain.Region;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Table(name = "cities")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 30)
    @Size(max = 30, message = "the name is too large")
    @NotNull(message = "name shouldn't be null")
    @NotBlank(message = "the field is blank")
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
	private List<Franchise> franchiseList;
}
