package com.backend.detailsorderwork.domain;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.io.Serializable;

@Data
public class DetailsOrderWorkDto implements Serializable{
    @Min(value = 1)
    private Long orderWorkId;  

    @Min(value = 1)
    private Long serviceAssignedId;

    @Min(value = 1)
    private Long statusOrderServiceId;
}
