package com.jakeer.RegistrationApp.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name= "STATE_ENTITY")
public class StateEntitay {

    @Id
    @Column(name="STATE_ID")
    private Integer stateId;

    @Column(name="STATE_NAME")
    private String stateName;

    @Column(name="COUNTRY_ID")
    private Integer countyId;

}
