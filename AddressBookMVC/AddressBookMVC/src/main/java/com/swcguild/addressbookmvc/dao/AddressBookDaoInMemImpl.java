/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

import com.swcguild.addressbookmvc.model.Contact;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author DanKranefuss
 */
public class AddressBookDaoInMemImpl implements AddressBookDao {
    
 

    // holds all of our Contact objects - simulates the database
    private Map<Integer, Contact> contactMap = new HashMap<>();
    // used to assign ids to Contacts - simulates the auto increment
    // primary key for Contacts in a database
    private static int contactIdCounter = 0;

    @Override
    public Contact addContact(Contact contact) {
        // assign the current counter values as the contactid
        contact.setContactId(contactIdCounter);
        // increment the counter so it is ready for use for the next contact
        contactIdCounter++;
        contactMap.put(contact.getContactId(), contact);
        return contact;
    }

    @Override
    public void removeContact(int contactId) {
        contactMap.remove(contactId);
    }

    @Override
    public void updateContact(Contact contact) {
        contactMap.put(contact.getContactId(), contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        Collection<Contact> c = contactMap.values();
        return new ArrayList(c);
    }

    @Override
    public Contact getContactById(int contactId) {
        return contactMap.get(contactId);
    }

    @Override
    public List<Contact> searchContacts(Map<SearchTerm, String> criteria) {
        // Get all the search terms from the map
        String firstNameCriteria = criteria.get(SearchTerm.FIRST_NAME);
        String lastNameCriteria = criteria.get(SearchTerm.LAST_NAME);
        String streetCriteria = criteria.get(SearchTerm.STREET);
        String cityCriteria = criteria.get(SearchTerm.CITY);
        String stateCriteria = criteria.get(SearchTerm.STATE);
        String zipCriteria = criteria.get(SearchTerm.ZIP);
        
        // Declare all the predicate conditions
        Predicate<Contact> firstNameMatches;
        Predicate<Contact> lastNameMatches;
        Predicate<Contact> streetMatches;
        Predicate<Contact> cityMatches;
        Predicate<Contact> stateMatches;
        Predicate<Contact> zipMatches;
        
        // Placeholder predicate - always returns true.  Used for search terms
        // that are empty
        Predicate<Contact> truePredicate = (c) -> {return true;};

        // Assign values to predicates.  If a given search term is empty, just
        // assign the default truePredicate, otherwise assign the predicate that
        // properly filters for the given term.
        firstNameMatches = (firstNameCriteria == null || firstNameCriteria.isEmpty())
                ? truePredicate 
                : (c) -> c.getFirstName().equalsIgnoreCase(firstNameCriteria);
        
        lastNameMatches = (lastNameCriteria == null || lastNameCriteria.isEmpty())
                ? truePredicate
                :(c) -> c.getLastName().equalsIgnoreCase(lastNameCriteria);
        
        streetMatches = (streetCriteria == null || streetCriteria.isEmpty())
                ? truePredicate
                :(c) -> c.getStreet().equalsIgnoreCase(streetCriteria);
        
        cityMatches = (cityCriteria == null || cityCriteria.isEmpty())
                ? truePredicate
                :(c) -> c.getCity().equalsIgnoreCase(cityCriteria);
        
        stateMatches = (stateCriteria == null || stateCriteria.isEmpty())
                ? truePredicate
                :(c) -> c.getState().equalsIgnoreCase(stateCriteria);
        
        zipMatches = (zipCriteria == null || zipCriteria.isEmpty())
                ? truePredicate
                :(c) -> c.getZip().equalsIgnoreCase(zipCriteria);

        // Return the list of Contacts that match the given criteria.  To do this we
        // just AND all the predicates together in a filter operation.
        
        List<Contact> result = contactMap.values().stream()
                .filter(firstNameMatches
                            .and(lastNameMatches)
                            .and(streetMatches)
                            .and(cityMatches)
                            .and(stateMatches)
                            .and(zipMatches))
                .collect(Collectors.toList());
        
        return result;
    }
}
