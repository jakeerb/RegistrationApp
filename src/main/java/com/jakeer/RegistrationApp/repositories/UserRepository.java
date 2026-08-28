package com.jakeer.RegistrationApp.repositories;

import com.jakeer.RegistrationApp.entities.CityEntity;
import com.jakeer.RegistrationApp.entities.Userentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Userentity,Integer> {

    public Userentity findByUserEmail(String userEmail);

}