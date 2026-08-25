package com.jakeer.RegistrationApp.bindings;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer userId;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private Long userPhno;

    private Date userDOB;

    private String userGender;

    private Integer userCountry;

    private Integer userState;

    private Integer userCity;

    private String userPassword;

    private String userAccStatus;

    private Date createdDate;

    private Date updatedDate;
}