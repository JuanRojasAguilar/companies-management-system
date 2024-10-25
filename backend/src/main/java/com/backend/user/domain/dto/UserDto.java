package com.backend.user.domain.dto;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto implements Serializable{
    @Column(length = 45, nullable = false)
    @NotEmpty(message = "name cannot be null")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El campo solo puede contener letras y espacios, no caracteres especiales")
    private String name;

    @Column(length = 45, nullable = false)
    @NotEmpty(message = "lastname cannot be null")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El campo solo puede contener letras y espacios, no caracteres especiales")
    private String lastname;
    
    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    private String username;

    @NotEmpty(message = "la contraseña no puede ser nula")
    @Size(min = 0)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$", message = "The password must contain at least one uppercase character, one number and one special character, with a minimum length of 8 characters.")
    private String password;

    @NotEmpty(message = "la contraseña no puede ser nula")
    @Size(min = 0)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$", message = "The password must contain at least one uppercase character, one number and one special character, with a minimum length of 8 characters.")
    private String repeatedPassword;

    @Min(value = 1)
    private Long usertypeId;

    @Min(value = 1)
    private Long franchiseId;
}
