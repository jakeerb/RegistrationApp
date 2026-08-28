package com.jakeer.RegistrationApp.rest;


import com.jakeer.RegistrationApp.bindings.LoginRequest;
import com.jakeer.RegistrationApp.servises.LoginService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    private static final Logger log= LoggerFactory.getLogger(LoginController.class);
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request)
    {

        log.info("Login request received for email: {}",request.getEmail());
        String token = loginService.login(request);

        log.info("Login successful for email: {}",request.getEmail());
        return token;
    }

}
