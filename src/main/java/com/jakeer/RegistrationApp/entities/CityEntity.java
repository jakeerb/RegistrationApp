package com.jakeer.RegistrationApp.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="CITY_MASTER")
@Data
public class CityEntity {

    @Id
    @Column(name="CITY_ID")
    private Integer cityID;

    @Column(name="CITY_NAME")
    private String cityName;

    @Column(name="STATE_ID")
    private Integer stateId;

}
