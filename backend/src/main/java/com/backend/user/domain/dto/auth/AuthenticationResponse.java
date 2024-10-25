package com.backend.user.domain.dto.auth;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthenticationResponse implements Serializable {
    private String jwt;
}
