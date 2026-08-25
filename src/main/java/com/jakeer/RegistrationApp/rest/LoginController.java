package com.jakeer.RegistrationApp.rest;


import com.jakeer.RegistrationApp.bindings.LoginRequest;
import com.jakeer.RegistrationApp.servises.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return loginService.login(request);
    }

}
