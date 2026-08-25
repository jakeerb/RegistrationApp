package com.jakeer.RegistrationApp.repositories;

import com.jakeer.RegistrationApp.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity,Integer> {


    public List<CityEntity> findByStateId(Integer stateId);
}
