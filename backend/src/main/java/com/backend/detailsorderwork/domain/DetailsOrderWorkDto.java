package com.backend.detailsorderwork.domain;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DetailsOrderWorkDto {
    @Min(value = 1)
    private Long orderWorkId;  

    @Min(value = 1)
    private Long serviceAssignedId;

    @Min(value = 1)
    private Long statusOrderServiceId;
}
