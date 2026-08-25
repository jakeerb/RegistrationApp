package com.jakeer.RegistrationApp.bindings;


import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String pwd;

}
