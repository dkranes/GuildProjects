/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.contactlistmvc.controller;

import com.swcguild.contactlistmvc.dao.ContactListDao;
import com.swcguild.contactlistmvc.model.Contact;
import com.swcguild.contactlistmvc.model.SearchTerm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class SearchController {
    
    private ContactListDao dao;
    
    @Inject
    public SearchController(ContactListDao dao){
        this.dao = dao;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySearchPage() {

        return "search";
    }

    @RequestMapping(value = "search/contacts", method = RequestMethod.POST)
    @ResponseBody
    public List<Contact> searchContacts(@RequestBody Map<String, String> searchMap) {

        Map<SearchTerm, String> criteriaMap = new HashMap<>();
        
        String currentTerm = searchMap.get("firstName");
        if(!currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.FIRST_NAME, currentTerm);
        }
        
        currentTerm = searchMap.get("lastName");
        if(!currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.LAST_NAME, currentTerm);
        }
        
        currentTerm = searchMap.get("company");
        if(!currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.COMPANY, currentTerm);
        }
        
        currentTerm = searchMap.get("email");
        if(!currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.EMAIL, currentTerm);
        }
        currentTerm = searchMap.get("phone");
        if(!currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.PHONE, currentTerm);
        }
        
        return dao.searchContacts(criteriaMap);
    }
}
