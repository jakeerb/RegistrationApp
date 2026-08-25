package com.jakeer.RegistrationApp.repositories;

import com.jakeer.RegistrationApp.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity,Integer> {
}
