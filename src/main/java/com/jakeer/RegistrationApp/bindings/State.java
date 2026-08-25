package com.jakeer.RegistrationApp.bindings;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class State {

    private Integer stateId;
    private String stateName;
    private Integer countyId;

}
