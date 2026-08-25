package com.jakeer.RegistrationApp.excpton;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandeler {

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<Map<String,String>> handleValidationExceptio(ResourceNotFoundException ex){

   Map<String,String> error = new HashMap<>();
    error.put("message",ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}



    @ExceptionHandler(ResourceAlreadyExitsException.class)
    public ResponseEntity<Map<String, String>> handleResourceAlreadyExists(
            ResourceAlreadyExitsException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
