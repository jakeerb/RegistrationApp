package com.jakeer.RegistrationApp.excpton;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandeler {
private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandeler.class);

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<Map<String,String>> handleValidationExceptio(ResourceNotFoundException ex){

    log.error("Resource not found: {}",ex.getMessage());


   Map<String,String> error = new HashMap<>();
    error.put("message",ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}

    @ExceptionHandler(ResourceAlreadyExitsException.class)
    public ResponseEntity<Map<String, String>> handleResourceAlreadyExists(
            ResourceAlreadyExitsException ex) {

    log.error("Resource already exists: {}",ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidCreditilasException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentials(
            InvalidCreditilasException ex) {

    log.warn("Invalid login credntils: {}",ex.getMessage());


        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountLockedException.class)
    public ResponseEntity<Map<String, String>> handleAccountLocked(
            AccountLockedException ex) {

    log.warn("Account Locked: {}",ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.LOCKED);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {
     log.warn("validation failed:{}",ex.getMessage());
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
