package com.jakeer.RegistrationApp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;


@Entity
@Table(name="CONTACT_DTLS")
@Data
public class Contact {

    @Id
    @GeneratedValue
    @Column(name="CONTACT_ID")
    private Integer contactId;

    @Column(name="CONTACT_NAME")
    private String contactName;

    @Column(name="CONTACT_EMAIL")
    private String contactEmail;

    @Column(name="CONTACT_NUMBER")
    private Long contactNumber;

   @Column(name="ACTIVE_SW")
    private String activeSw;

    @CreationTimestamp
    @Column(name="CREATE_DATE")
    private LocalDate createDate;

    @UpdateTimestamp
    @Column(name="UPDATE_DATE")
    private LocalDate UpdateDate;

}
