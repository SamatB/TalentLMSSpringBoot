package com.peaksoft.spring_boot.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
