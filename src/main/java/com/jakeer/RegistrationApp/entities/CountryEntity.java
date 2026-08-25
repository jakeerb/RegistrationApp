package com.jakeer.RegistrationApp.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="COUNTRY_MASTER")
public class CountryEntity {

    @Id
    @Column(name="COUNTRY_ID")
    private Integer countyId;

    @Column(name="COUNTRY_NAME")
    private String countryName;
}
