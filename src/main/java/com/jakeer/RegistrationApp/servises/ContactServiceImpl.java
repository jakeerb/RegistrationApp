package com.jakeer.RegistrationApp.servises;

import com.jakeer.RegistrationApp.bindings.ContactForm;
import com.jakeer.RegistrationApp.entities.Contact;
import com.jakeer.RegistrationApp.repositories.ContactRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepo;


    @Override
    public String saveContact(ContactForm form) {
        Contact entity = new Contact();
        BeanUtils.copyProperties(form,entity);

       entity= contactRepo.save(entity);
if(entity.getContactId()!=null){
    return "Contact save Successfully";
   }
        return "Contact save Failed";
    }

    @Override
    public List<ContactForm> viewContacts() {
List<ContactForm> dataList = new ArrayList<>();
        List<Contact> findAll = contactRepo.findAll();
        for(Contact entity:findAll){
            ContactForm form = new ContactForm();
    BeanUtils.copyProperties(entity,form);
            dataList.add(form);
}
        return dataList;
    }

    @Override
    public ContactForm editContact(Integer contactId) {

        Optional<Contact> findById = contactRepo.findById(contactId);

        if(findById.isPresent()){
            Contact entity = findById.get();
            ContactForm form = new ContactForm();
            BeanUtils.copyProperties(entity,form);
            return form;
        }
        return null;
    }

    @Override
    public List<ContactForm> deleteContact(Integer contactId) {
        contactRepo.deleteById(contactId);
        return viewContacts();
    }
}
