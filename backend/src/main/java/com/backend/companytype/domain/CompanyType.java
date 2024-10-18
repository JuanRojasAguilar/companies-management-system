package com.backend.companytype.domain;

import java.util.List;

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
@Table(name="companies_types")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CompanyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "company_type_id")
    private Long id;

    @Column(length = 255)
    @Size(max = 255, message = "the description is too large")
    @NotNull(message = "description shouldn't be null")
    @NotBlank(message = "the field is blank")
    private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companyType")
	private List<CompanyType> companyTypeList;
}
