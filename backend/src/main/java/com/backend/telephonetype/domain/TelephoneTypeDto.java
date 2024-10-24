package com.backend.telephonetype.domain;

import lombok.Data;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class TelephoneTypeDto implements Serializable {
    @Size(max = 25)
    @NotNull
    @NotBlank
    private String name;
}