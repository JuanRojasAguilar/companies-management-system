package com.backend.userreagent.domain;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserReagentDto implements Serializable {
    @Size(max = 40)
    @NotBlank
    @NotNull
    private String userId;

    @Max(value = 1)
    private Long reagentId;

    @Max(value = 1)
    private Long serviceId;
}
