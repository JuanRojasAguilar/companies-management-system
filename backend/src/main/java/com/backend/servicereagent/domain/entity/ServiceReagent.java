package com.backend.servicereagent.domain.entity;

import java.math.BigDecimal;

import com.backend.reagent.domain.Reagent;
import com.backend.service.domain.Service;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "services_reagents")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class ServiceReagent {
    @EmbeddedId
    @Column(name = "service_reagent_id")
    @EqualsAndHashCode.Include
    private ServiceReagentPk serviceReagentPk;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("reagentId")
    private Reagent reagent;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("serviceId")
    private Service service;
}

