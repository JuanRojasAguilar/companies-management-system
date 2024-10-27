package com.backend.franchise.domain;

import lombok.Data;
import java.sql.Date;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class FranchiseDto implements Serializable {
    
    @Size(max = 50, message = "the name is too large")
    @NotNull(message = "name shouldn't be null")
    @NotBlank(message = "the field is blank")
    private String name;
    
    @Size(max = 21, message = "the nit is too large")
    @NotNull(message = "nit shouldn't be null")
    @NotBlank(message = "the field is blank")
    private String nit;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date foundingDate;

    @Min(value = 1)
    private Long cityId;

    @Min(value = 1)
    private Long companyId;
}
