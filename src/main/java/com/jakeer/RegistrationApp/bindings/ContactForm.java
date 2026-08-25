package com.jakeer.RegistrationApp.bindings;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
@Data
public class ContactForm {
    private Integer contactId;
    private String contactName;
    private String contactEmail;
    private Long contactNumber;
    private String activeSw;
    private LocalDate createDate;
    private LocalDate UpdateDate;
}
