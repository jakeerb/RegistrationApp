package com.jakeer.RegistrationApp.bindings;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class City {


    private Integer cityID;
    private String cityName;
    private Integer stateId;
}
