package com.jakeer.RegistrationApp.repositories;

import com.jakeer.RegistrationApp.entities.StateEntitay;
import com.jakeer.RegistrationApp.entities.Userentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<StateEntitay , Integer> {

    public List<StateEntitay> findByCountyId(Integer countyId);

}
