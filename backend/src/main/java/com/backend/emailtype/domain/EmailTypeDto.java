package com.backend.emailtype.domain;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmailTypeDto implements Serializable{
	@Size(max = 25, message = "the name is too large")
	@NotNull(message = "name shouldn't be null")
	@NotBlank(message = "the field is blank")
	private String name;
}
