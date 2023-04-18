package com.example.school1.service;


import com.example.school1.controller.ContactController;
import com.example.school1.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Service
//@RequestScope
@SessionScope
public class ContactService {
    private static Logger log = LoggerFactory.getLogger(ContactService.class);

    private int counter = 0;

    public ContactService() {
        log.info("Contact service initialized...");
    }

    public void saveContact(Contact contact){
        log.info(contact.toString());
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
