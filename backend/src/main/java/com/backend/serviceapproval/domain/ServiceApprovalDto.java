package com.backend.serviceapproval.domain;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ServiceApprovalDto implements Serializable{
	@NotNull
	@NotBlank
	private String findings;

    @NotNull
	@NotBlank
    private String solutions;

    @Max(value = 1)
    private Long serviceId;

    @Max(value = 1)
    private Long orderWorkId;

    @NotBlank
    @NotNull
    @Size(max = 40)
    private String clientId;

    @Min(value = 1)
    private Long statusApprovalId;
}
