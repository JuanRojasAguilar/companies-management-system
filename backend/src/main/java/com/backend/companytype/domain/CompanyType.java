package com.backend.companytype.domain;

import java.util.List;

import com.backend.company.domain.Company;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="companies_types")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CompanyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "company_type_id")
    private Long id;

    @Column(length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companyType")
    @JsonIgnore
	private List<Company> companys;
}
