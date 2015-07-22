/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.contactlistmvc.controller;

import com.swcguild.contactlistmvc.dao.ContactListDao;
import com.swcguild.contactlistmvc.model.Contact;
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
 * @author apprentice
 */
@Controller
public class HomeControllerNoAjax {

    private ContactListDao dao;

    @Inject //this signals to Spring to go to spring-persistence.xml, seek out the bean
    //and inject it.
    public HomeControllerNoAjax(ContactListDao dao) {

        this.dao = dao;
    }
    
    @RequestMapping(value="/displayContactListNoAjax", method=RequestMethod.GET)
    public String displayContactListNoAjax(Model model){
        
        List<Contact> cList=dao.getAllContacts();
        model.addAttribute("contactList", cList);
        
        return "displayContactListNoAjax";
    }
    
    @RequestMapping(value="/displayNewContactFormNoAjax", method=RequestMethod.GET)
    public String displayNewContactFormNoAjax(){
        return "newContactFormNoAjax";
    }
    
    @RequestMapping(value="/addNewContactNoAjax", method=RequestMethod.POST)
    public String addNewContactNoAjax(HttpServletRequest req){
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String company = req.getParameter("company");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setCompany(company);
        contact.setPhone(phone);
        contact.setEmail(email);
        
        dao.addContact(contact);
        
        return "redirect:displayContactListNoAjax";
    }
    
    
    @RequestMapping(value="/deleteContactNoAjax", method=RequestMethod.GET)
    public String deleteContactNoAjax(HttpServletRequest req){
        int contactId = Integer.parseInt(req.getParameter("contactId"));
        dao.removeContact(contactId);
        
        return "redirect:displayContactListNoAjax";
    }
    
    @RequestMapping(value="/displayEditContactFormNoAjax", method=RequestMethod.GET)
    public String displayEditContactFormNoAjax(HttpServletRequest req, Model model){
        int contactId = Integer.parseInt(req.getParameter("contactId"));
        Contact contact = dao.getContactById(contactId);
        
        model.addAttribute("contact",contact);
        
        return "editContactFormNoAjax";
    }
    
    @RequestMapping(value="/editContactNoAjax", method=RequestMethod.POST)
    public String editContactNoAjax(@Valid @ModelAttribute("contact") Contact contact,
            BindingResult result){
        if(result.hasErrors()){
            return "editContactFormNoAjax";
        }
        
        dao.updateContact(contact);
        
        return "redirect:displayContactListNoAjax";
    }
}
