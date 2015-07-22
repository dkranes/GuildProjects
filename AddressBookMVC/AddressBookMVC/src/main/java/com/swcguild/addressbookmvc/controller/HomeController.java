/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.controller;

import com.swcguild.addressbookmvc.dao.AddressBookDao;
import com.swcguild.addressbookmvc.model.Contact;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author DanKranefuss
 */
@Controller
public class HomeController {

    private AddressBookDao dao;

    @Inject
    public HomeController(AddressBookDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Contact getContact(@PathVariable("id") int id) {

        return dao.getContactById(id);
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Contact createContact(@Valid @RequestBody Contact contact) {

        dao.addContact(contact);

        return contact;
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable("id") int id) {
        
        dao.removeContact(id);
    }
    
    
    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putContact(@PathVariable("id") int id, @RequestBody Contact contact) {
        
        contact.setContactId(id);
       
        dao.updateContact(contact);
    }
    
    @RequestMapping(value = "/addresses", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getAllContacts() {
        
        return dao.getAllContacts();
    }
    
    

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }
}
