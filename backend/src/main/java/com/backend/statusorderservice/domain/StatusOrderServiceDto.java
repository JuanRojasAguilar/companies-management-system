package com.backend.statusorderservice.domain;

import lombok.Data;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class StatusOrderServiceDto implements Serializable {
    @Size(max = 20)
    @NotNull
    @NotBlank
    private String name;
}
