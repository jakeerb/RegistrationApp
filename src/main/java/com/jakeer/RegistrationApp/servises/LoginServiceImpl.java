package com.jakeer.RegistrationApp.servises;

import com.jakeer.RegistrationApp.bindings.LoginRequest;
import com.jakeer.RegistrationApp.entities.Userentity;
import com.jakeer.RegistrationApp.excpton.AccountLockedException;
import com.jakeer.RegistrationApp.excpton.InvalidCreditilasException;
import com.jakeer.RegistrationApp.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.LoggerFactory;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    private static final Logger log = (Logger) LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public String login(LoginRequest request) {

        // 1. Find user using email
        Userentity user = userRepo.findByUserEmail(request.getEmail());

        log.info("user lookup completed for email: {}",request.getEmail());

        // 2. User not found
        if (user == null) {
            log.warn("Login failed - user not found for email: {}",request.getEmail());
           // return "Invalid Credentials";
            throw new InvalidCreditilasException("Inavalid email or password");
        }

        // 3. Check account status
        if("Locked".equalsIgnoreCase(user.getUserAccStatus())){
            log.warn("Login blocked-account is locked for email: {},",request.getEmail());
            throw new AccountLockedException("Account is Locked");
        }

        // 4. Check password
        if (!passwordEncoder.matches(
                request.getPwd(),
                user.getUserPassword())) {

            log.warn("Login failed-invalid password for email:{}",request.getEmail());
            int attempts = user.getLoginAttempts() + 1;
                user.setLoginAttempts(attempts);

                if(attempts>=3){
                    user.setUserAccStatus("Locked");
                    userRepo.save(user);
                    log.warn("Account locked after 3 failed login attempts for email: {}",request.getEmail());

                   // return "Account is Locked";
                    throw new AccountLockedException("Account is Locked");
                }
                userRepo.save(user);
               // return "Invalid Credentials";
            throw new InvalidCreditilasException("Invalid email or  password");
            }
            user.setLoginAttempts(0);
        userRepo.save(user);

        log.info("Login successful for email{} ",request.getEmail());




             // return "success";
        String token = jwtService.generateToken(request.getEmail());
        log.info("JWT token generated successfully for email:{}",request.getEmail());
        return token;
    }
}