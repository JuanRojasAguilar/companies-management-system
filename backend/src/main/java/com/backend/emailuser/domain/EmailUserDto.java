package com.backend.emailuser.domain;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmailUserDto implements Serializable {
    
    @NotNull(message = "name shouldn't be null")
    @NotBlank(message = "the field is blank")
    @Email
    private String email;

    @Min(value = 1)
    private Long emailTypeId;

    @Size(max = 40)
    @NotNull
    @NotBlank
    private String userId;
}
