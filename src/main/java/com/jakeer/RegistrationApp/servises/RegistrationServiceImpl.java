package com.jakeer.RegistrationApp.servises;

import com.jakeer.RegistrationApp.bindings.User;
import com.jakeer.RegistrationApp.entities.CityEntity;
import com.jakeer.RegistrationApp.entities.CountryEntity;
import com.jakeer.RegistrationApp.entities.StateEntitay;
import com.jakeer.RegistrationApp.entities.Userentity;
import com.jakeer.RegistrationApp.excpton.ResourceAlreadyExitsException;
import com.jakeer.RegistrationApp.excpton.ResourceNotFoundException;
import com.jakeer.RegistrationApp.kafka.producer.RegistrationKafkaProducer;
import com.jakeer.RegistrationApp.repositories.CityRepository;
import com.jakeer.RegistrationApp.repositories.CountryRepository;
import com.jakeer.RegistrationApp.repositories.StateRepository;
import com.jakeer.RegistrationApp.repositories.UserRepository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger log =
            LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CountryRepository countryRepo;

    @Autowired
    private StateRepository satateRepo;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RegistrationKafkaProducer kafkaProducer;

    @Override
    public boolean uniqEmail(String email) {

        log.info("Checking email uniqueness");

        Userentity userEntity = userRepo.findByUserEmail(email);

        if (userEntity != null) {
            log.info("Email already exists");
            return false;
        } else {
            log.info("Email is available");
            return true;
        }
    }

    @Override
    public Map<Integer, String> getCountries() {

        log.info("Fetching countries");

        List<CountryEntity> findAll = countryRepo.findAll();

        if (findAll.isEmpty()) {
            log.warn("No countries found");
            throw new ResourceNotFoundException("No countries found");
        }

        Map<Integer, String> countryMap = new HashMap<>();

        for (CountryEntity entity : findAll) {
            countryMap.put(
                    entity.getCountyId(),
                    entity.getCountryName()
            );
        }

        return countryMap;
    }

    @Override
    public Map<Integer, String> getStates(Integer countyId) {

        log.info("Fetching states for countryId: {}", countyId);

        List<StateEntitay> stateList =
                satateRepo.findByCountyId(countyId);

        if (stateList.isEmpty()) {
            log.warn("No states found for countryId: {}", countyId);
            throw new ResourceNotFoundException(
                    "No states found for countryId: " + countyId
            );
        }

        Map<Integer, String> stateMap = new HashMap<>();

        for (StateEntitay state : stateList) {
            stateMap.put(
                    state.getStateId(),
                    state.getStateName()
            );
        }

        return stateMap;
    }

    @Override
    public Map<Integer, String> getCities(Integer stateId) {

        log.info("Fetching cities for stateId: {}", stateId);

        List<CityEntity> cityList =
                cityRepo.findByStateId(stateId);

        if (cityList.isEmpty()) {
            log.warn("No cities found for stateId: {}", stateId);
            throw new ResourceNotFoundException(
                    "No cities found for stateId: " + stateId
            );
        }

        Map<Integer, String> cityMap = new HashMap<>();

        for (CityEntity city : cityList) {
            cityMap.put(
                    city.getCityID(),
                    city.getCityName()
            );
        }

        return cityMap;
    }

    @Override
    public boolean registerUser(User user) {

        log.info("Registering user with email: {}",
                user.getUserEmail());

        if (!uniqEmail(user.getUserEmail())) {

            log.warn(
                    "Registration failed. Email already exists"
            );

            throw new ResourceAlreadyExitsException(
                    "Email already exists"
            );
        }

        // Generate temporary password
        String tempPassword = generateTempPwd();

        user.setUserPassword(tempPassword);
        user.setUserAccStatus("Locked");

        // Convert binding object to entity
        Userentity entity = new Userentity();

        BeanUtils.copyProperties(user, entity);

        // Save user
        userRepo.save(entity);

        String  message="User registrtated successflly with email: "+user.getUserEmail();

        kafkaProducer.sendRegistration(message);

        // Send registration email
        emailService.sendEmail(
                user.getUserEmail(),
                "Registration Successful",
                "Your registration was successful. Welcome to RegistrationApp."
        );

        log.info(
                "User registered successfully with email: {}",
                user.getUserEmail()
        );

        return true;
    }

    @Override
    public boolean unLockAccount(Integer userId) {

        Userentity user = userRepo.findById(userId).orElse(null);
        if(user==null){
            return false;
        }
        user.setUserAccStatus("Unlocked");
        user.setUpdatedDate(new Date());
        userRepo.save(user);
        return true;
    }

    private String generateTempPwd() {

        return "Temp@" + (1000 + new Random().nextInt(9000));
    }
}