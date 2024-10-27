package com.backend.orderwork.domain;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderWorkDto implements Serializable {
    
    @Size(max = 20, message = "the number order work is too large")
    @NotNull(message = "number order work shouldn't be null")
    @NotBlank(message = "the field is blank")
	private Long numberOrderWork;

    @Size(max = 40)
    @NotNull
    @NotBlank
    private String EmployeeId; 

    @Min(value = 1)
    private Long orderServiceId;
}
