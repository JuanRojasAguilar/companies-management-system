package com.backend.user.domain.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RegisterUser implements Serializable {
    private String id;
    private String username;
    private String role;
    private String jwt;
    private String name;
}
