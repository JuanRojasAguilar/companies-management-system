package com.backend.servicereagent.domain;

import java.math.BigDecimal;

import com.backend.reagent.domain.Reagent;
import com.backend.service.domain.Service;
import com.backend.utils.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

    private BigDecimal unitValue;

    private Long stock;

    private Long stockMax;

    private Long stockMin;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("reagentId")
    private Reagent reagent;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("serviceId")
    private Service service;
}

