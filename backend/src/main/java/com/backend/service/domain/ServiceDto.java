package com.backend.service.domain;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ServiceDto implements Serializable {
    @Size(max = 50, message= "the size is too big")
    @NotBlank
    @NotNull
    private String name;   

    private boolean reagentNeeded;
}
