package com.jakeer.RegistrationApp.servises;

import com.jakeer.RegistrationApp.bindings.ContactForm;

import java.util.List;

public interface ContactService {


    public String saveContact(ContactForm form);

    public List<ContactForm> viewContacts();

    public ContactForm editContact(Integer contactId);

    public List<ContactForm> deleteContact(Integer contactId);
}
