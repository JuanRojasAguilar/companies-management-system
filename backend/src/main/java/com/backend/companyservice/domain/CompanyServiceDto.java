package com.backend.companyservice.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyServiceDto implements Serializable {
    @Size(max = 25, message = "the value is too large")
	@NotNull(message = "value shouldn't be null")
	@NotBlank(message = "the field is blank")
	private BigDecimal valueService;

    @Min(value = 1)
    private Long franchiseId;

    @Min(value = 1)
    private Long serviceId;
}
