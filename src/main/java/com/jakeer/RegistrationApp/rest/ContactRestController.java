package com.jakeer.RegistrationApp.rest;


import com.jakeer.RegistrationApp.bindings.ContactForm;
import com.jakeer.RegistrationApp.servises.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactRestController {

    @Autowired
    private ContactService contactService;


    @PostMapping("/contactsave")
    public String savecontact(@RequestBody ContactForm form){
        return contactService.saveContact(form);
    }

    @GetMapping("/contacts")
    public List<ContactForm> viewContacts(){
        return contactService.viewContacts();
    }

    @GetMapping("/edit/{contactId}")
    public ContactForm editContact(@PathVariable Integer contactId){

      return contactService.editContact(contactId);
    }
@DeleteMapping("/delete/{contactId}")
    public List<ContactForm> delectContact(@PathVariable Integer contactId){
       return contactService.deleteContact(contactId);
    }
}
