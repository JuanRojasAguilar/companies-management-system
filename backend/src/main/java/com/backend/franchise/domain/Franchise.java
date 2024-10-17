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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Column(length = 50)
    @Size(max = 50, message = "the name is too large")
    @NotNull(message = "name shouldn't be null")
    @NotBlank(message = "the field is blank")
    private String name;

    @Column(length = 21)
    @Size(max = 21, message = "the nit is too large")
    @NotNull(message = "nit shouldn't be null")
    @NotBlank(message = "the field is blank")
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
