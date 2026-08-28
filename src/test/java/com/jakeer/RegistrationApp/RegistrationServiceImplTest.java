package com.jakeer.RegistrationApp;

import com.jakeer.RegistrationApp.servises.EmailService;
import com.jakeer.RegistrationApp.servises.RegistrationServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jakeer.RegistrationApp.bindings.User;
import com.jakeer.RegistrationApp.entities.Userentity;

import com.jakeer.RegistrationApp.repositories.UserRepository;
import com.jakeer.RegistrationApp.repositories.CountryRepository;
import com.jakeer.RegistrationApp.repositories.StateRepository;
import com.jakeer.RegistrationApp.repositories.CityRepository;

import com.jakeer.RegistrationApp.excpton.ResourceAlreadyExitsException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceImplTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private CountryRepository countryRepo;

    @Mock
    private StateRepository stateRepo;

    @Mock
    private CityRepository cityRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private RegistrationServiceImpl registrationService;


    // =========================
    // SUCCESS TEST
    // =========================

    @Test
    void registerUser_success() {

        User user = new User();
        user.setUserEmail("test@gmail.com");

        when(userRepo.findByUserEmail("test@gmail.com"))
                .thenReturn(null);

        when(passwordEncoder.encode(any(String.class)))
                .thenReturn("encodedPassword");

        when(userRepo.save(any(Userentity.class)))
                .thenReturn(new Userentity());

        boolean result = registrationService.registerUser(user);

        assertTrue(result);

        verify(userRepo).save(any(Userentity.class));
    }


    // =========================
    // ALREADY EXISTS TEST
    // =========================

    @Test
    void registerUser_AlreadyExits() {

        User user = new User();
        user.setUserEmail("test@gmail.com");

        Userentity existingUser = new Userentity();

        when(userRepo.findByUserEmail("test@gmail.com"))
                .thenReturn(existingUser);

        org.junit.jupiter.api.Assertions.assertThrows(
                ResourceAlreadyExitsException.class,
                () -> registrationService.registerUser(user)
        );

        verify(userRepo, never()).save(any(Userentity.class));
    }


}