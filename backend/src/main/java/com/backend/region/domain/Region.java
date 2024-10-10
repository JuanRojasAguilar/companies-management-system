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
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "regions")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Region {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;
    
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
