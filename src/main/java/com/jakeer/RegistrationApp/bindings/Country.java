package com.jakeer.RegistrationApp.bindings;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Country {

    private Integer countyId;
    private String countryName;
}
