package com.jakeer.RegistrationApp.repositories;

import com.jakeer.RegistrationApp.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
}
