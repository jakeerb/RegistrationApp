package com.jakeer.RegistrationApp.rest;


import com.jakeer.RegistrationApp.bindings.User;
import com.jakeer.RegistrationApp.constants.AppConstants;
import com.jakeer.RegistrationApp.servises.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService regService;


    @GetMapping("/email/{email}")
    public String checkEmail( @PathVariable String email){
        boolean uniqEmail = regService.uniqEmail(email);
        if(uniqEmail){
            return AppConstants.UNIQUE;
        }else{
            return AppConstants.DUPLICATE;
        }
    }
    @GetMapping("/contries")
    public Map<Integer,String> getContries(){
       return regService.getCountries();

    }
    @GetMapping("/states/{countyId}")
    public Map<Integer,String> getStates(@PathVariable Integer countyId){
      return regService.getStates(countyId);
    }
    @GetMapping("/cities/{stateId}")
    public Map<Integer,String > getcities(@PathVariable Integer stateId){
        return regService.getCities(stateId);
    }


    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@Valid @RequestBody User user){
        boolean registerUser = regService.registerUser(user);
        if(registerUser){
            return new ResponseEntity<>(AppConstants.SUCCESS,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(AppConstants.FAIL, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/unlock-account/{userId}")
    public ResponseEntity<String> unlockAccount(@PathVariable Integer userId) {

        boolean unlocked = regService.unLockAccount(userId);

        if (unlocked) {
            return new ResponseEntity<>("Account unlocked successfully",
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found",
                    HttpStatus.NOT_FOUND);
        }
    }

}
