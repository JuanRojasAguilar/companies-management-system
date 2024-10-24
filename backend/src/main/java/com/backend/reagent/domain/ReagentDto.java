package com.backend.reagent.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class ReagentDto implements Serializable {
    @NotNull(message = "serial shouldn't be null")
    @NotBlank(message = "serial is blank")
    @Size(max = 100, message = "serial size exceeded (100)")
    private String serial;
    
    @Size(max = 100, message = "the name is too large")
    @NotNull(message = "name shouldn't be null")
    @NotBlank(message = "the field is blank")
    private String name;
}
