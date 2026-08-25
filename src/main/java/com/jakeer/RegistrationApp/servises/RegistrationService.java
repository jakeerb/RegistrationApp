package com.jakeer.RegistrationApp.servises;

import com.jakeer.RegistrationApp.bindings.User;
import com.jakeer.RegistrationApp.entities.Userentity;

import java.util.Map;

public interface RegistrationService {

    public boolean uniqEmail(String email);

    public Map<Integer,String> getCountries();

    public Map<Integer,String> getStates(Integer countyId);

    public Map<Integer,String> getCities(Integer stateId);

    public boolean registerUser(User user);

}
