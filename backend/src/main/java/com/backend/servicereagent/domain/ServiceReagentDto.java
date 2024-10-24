package com.backend.servicereagent.domain;

import java.io.Serializable;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServiceReagentDto implements Serializable {
    @Column(name = "unit_value", precision = 10, scale = 2)
    @NotNull(message = "unitValue couldn't be null")
    @DecimalMin(value = "0.00", message = "unitValue must be a positive number")
    private BigDecimal unitValue;

    @NotNull(message = "Stock value couldn't be null")
    @Min(value = 0, message = "Stock cannot be negative")
    private Long stock;

    @NotNull(message = "stockMax couldn't be null")
    @Min(value = 0, message = "stockMax cannot be negative")
    private Long stockMax;

    @NotNull(message = "stockMin couldn't be null")
    @Min(value = 0, message = "stockMin cannot be negative")
    private Long stockMin;

    @Min(value = 1)
    private Long reagentId;

    @Min(value = 1)
    private Long serviceId;
}
