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
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author DanKranefuss
 */
@Controller
public class HomeControllerNoAjax {

    private AddressBookDao dao;

    @Inject
    public HomeControllerNoAjax(AddressBookDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayAddressBookNoAjax", method = RequestMethod.GET)
    public String displayAddressBookNoAjax(Model model) {

        List<Contact> cList = dao.getAllContacts();

        model.addAttribute("addressBook", cList);

        return "displayAddressBookNoAjax";
    }

    @RequestMapping(value = "displayNewContactFormNoAjax", method = RequestMethod.GET)
    public String displayNewContactFormNoAjax() {
        return "newContactFormNoAjax";
    }

    @RequestMapping(value = "/editContactNoAjax", method = RequestMethod.POST)
    public String editContactNoAjax(@Valid @ModelAttribute("contact") Contact contact,
            BindingResult result) {
        if (result.hasErrors()) {
            return "editContactFormNoAjax";
        }

        dao.updateContact(contact);

        return "redirect:displayAddressBookNoAjax";
    }

    @RequestMapping(value = "/addNewContactNoAjax", method = RequestMethod.POST)
    public String addNewContactNoAjax(@Valid Contact contact, BindingResult result, HttpServletRequest req) {

        if (result.hasErrors()) {
            return "newContactFormNoAjax";
        }
        
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");

        contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setStreet(street);
        contact.setCity(city);
        contact.setState(state);
        contact.setZip(zip);

        dao.addContact(contact);

        return "redirect:displayAddressBookNoAjax";
    }

    
    
//    @RequestMapping(value = "/addNewContactNoAjax", method = RequestMethod.POST)
//    public String addNewContactNoAjax(@Valid HttpServletRequest req) {
//
//        String firstName = req.getParameter("firstName");
//        String lastName = req.getParameter("lastName");
//        String street = req.getParameter("street");
//        String city = req.getParameter("city");
//        String state = req.getParameter("state");
//        String zip = req.getParameter("zip");
//
//        Contact contact = new Contact();
//        contact.setFirstName(firstName);
//        contact.setLastName(lastName);
//        contact.setStreet(street);
//        contact.setCity(city);
//        contact.setState(state);
//        contact.setZip(zip);
//
//        dao.addContact(contact);
//
//        return "redirect:displayAddressBookNoAjax";
//    }

    @RequestMapping(value = "/deleteContactNoAjax", method = RequestMethod.GET)
    public String deleteContactNoAjax(HttpServletRequest req) {

        int contactId = Integer.parseInt(req.getParameter("contactId"));

        dao.removeContact(contactId);

        return "redirect:displayAddressBookNoAjax";
    }

    @RequestMapping(value = "/displayEditContactFormNoAjax", method = RequestMethod.GET)
    public String displayEditContactFormNoAjax(HttpServletRequest req, Model model) {

        int contactId = Integer.parseInt(req.getParameter("contactId"));

        Contact contact = dao.getContactById(contactId);

        model.addAttribute("contact", contact);

        return "editContactFormNoAjax";
    }

}
