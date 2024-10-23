package com.backend.detailorder.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class DetailOrderDto {
    @DecimalMin(value = "0.00", message= "Couldn't be negative numbers")
	@NotNull(message = "serviceValue couldn't be null")
	private BigDecimal serviceValue;

    @Min(value = 1)
    private Long serviceId;

    @Min(value = 1)
    private Long orderServiceId;
}
