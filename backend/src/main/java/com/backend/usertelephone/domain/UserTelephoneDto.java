package com.backend.usertelephone.domain;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserTelephoneDto implements Serializable {
    @Pattern(regexp = "[0-9]+", message = "number only could have digits")
    @NotBlank
    @NotNull
    private String number;

    @Min(value = 1)
    private String userId;

    @Min(value = 1)
    private Long telephoneTypeId;
}
