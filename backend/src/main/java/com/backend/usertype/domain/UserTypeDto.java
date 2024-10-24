package com.backend.usertype.domain;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserTypeDto implements Serializable {
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String name;
}
