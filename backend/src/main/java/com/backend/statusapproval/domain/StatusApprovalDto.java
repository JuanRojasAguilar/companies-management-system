package com.backend.statusapproval.domain;
import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StatusApprovalDto implements Serializable {
	@NotNull
	@NotBlank
	@Size(max = 40)
	private String name;
}
