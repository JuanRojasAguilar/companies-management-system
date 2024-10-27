package com.backend.orderservice.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderServiceDto implements Serializable {
    @Size(max = 100, message = "the name is too large")
    @NotNull(message = "name shouldn't be null")
    @NotBlank(message = "the field is blank")
    private String name;
   
    @Size(max = 40)
    @NotNull
    @NotBlank
    private String clientId;

    @Size(max = 40)
    @NotNull
    @NotBlank
    private String employeeId;

    @Min(value = 1)
    private Long orderStateId;
}
