package com.backend.franchise.domain;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.backend.city.domain.City;
import com.backend.company.domain.Company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "franchises")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "franchise_id")
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String nit;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "founding_date")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date founding_date;
}
