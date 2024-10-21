package com.backend.companytype.domain;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyTypeDto implements Serializable {
    @Size(max = 255, message = "the description is too large")
    @NotNull(message = "description shouldn't be null")
    @NotBlank(message = "the field is blank")
    private String description;
}
