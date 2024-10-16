package com.backend.region.domain;

import com.backend.country.domain.Country;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "regions")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Region {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;
    
    @Size(max = 100, message = "El nombre excede la capacidad")
    @NotBlank(message = "El campo nombre no puede estar vacio")
    @NotNull(message = "El campo nombre no puede ser nulo")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @NotBlank(message = "El campo pais no puede estar vacio")
    @NotNull(message = "El campo pais no puede ser nulo")
    private Country country;
}
