package com.jakeer.RegistrationApp.servises;

import com.jakeer.RegistrationApp.bindings.LoginRequest;
import com.jakeer.RegistrationApp.entities.Userentity;
import com.jakeer.RegistrationApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public String login(LoginRequest request) {
        Userentity user = userRepo.findByUserEmailAndUserPassword(request.getEmail(), request.getPwd());
        if(user == null){
         return "Invalid Credentils";
        }
        return "Success";
    }
}
