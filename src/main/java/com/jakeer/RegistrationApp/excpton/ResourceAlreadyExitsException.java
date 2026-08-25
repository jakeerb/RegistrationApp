package com.jakeer.RegistrationApp.excpton;

public class ResourceAlreadyExitsException extends RuntimeException{
    public ResourceAlreadyExitsException(String message){
        super(message);
    }
}
