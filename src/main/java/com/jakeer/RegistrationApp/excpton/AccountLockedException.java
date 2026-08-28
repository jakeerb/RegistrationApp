package com.jakeer.RegistrationApp.excpton;

public class AccountLockedException extends RuntimeException{
    public AccountLockedException(String message){
        super(message);
    }
}
