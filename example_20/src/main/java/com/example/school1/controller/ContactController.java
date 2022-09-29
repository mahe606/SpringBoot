package com.example.school1.controller;


import com.example.school1.model.Contact;
import com.example.school1.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/contact")
    public String displayContactPage(Model model){
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }


/*    @RequestMapping(value = "/saveMsg", method = POST)
    public ModelAndView getUserInfo(@RequestParam String name,
                                    @RequestParam String mobileNum,
                                    @RequestParam String email,
                                    @RequestParam String subject,
                                    @RequestParam String message){

        log.info("Name :" + name);
        log.info("mobileNum :" + mobileNum);
        log.info("email :" + email);
        log.info("subject :" + subject);
        log.info("message :" + message);


        return new ModelAndView("redirect:/contact");
    }*/

    @RequestMapping(value = "/saveMsg", method = POST)
    public String getUserInfo(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        if(errors.hasErrors()){
            log.error("Contact form validation failed due to : " + errors.toString());
            return "contact.html";
        }
        contactService.saveContact(contact);
        contactService.setCounter(contactService.getCounter()+1);
        log.info("Number of times the Contact form submitted is " + contactService.getCounter());
        return "redirect:/contact";
    }

}
