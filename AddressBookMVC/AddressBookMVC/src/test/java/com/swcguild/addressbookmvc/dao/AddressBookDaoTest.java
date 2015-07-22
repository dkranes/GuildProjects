/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

import com.swcguild.addressbookmvc.model.Contact;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author DanKranefuss
 */
public class AddressBookDaoTest {

    private AddressBookDao dao;

    public AddressBookDaoTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("addressBookDao", AddressBookDao.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteContact() {

        Contact nc = new Contact();
        nc.setFirstName("John");
        nc.setLastName("Doe");
        nc.setStreet("Main");
        nc.setCity("Akron");
        nc.setState("OH");
        nc.setZip("44311");

        dao.addContact(nc);

        Contact fromDb = dao.getContactById(nc.getContactId());

        assertEquals(fromDb, nc);

        dao.removeContact(nc.getContactId());

        assertNull(dao.getContactById(nc.getContactId()));

    }

    @Test
    public void addUpdateContact() {

        Contact nc = new Contact();
        nc.setFirstName("Valentino");
        nc.setLastName("Rossi");
        nc.setStreet("Main");
        nc.setCity("Akron");
        nc.setState("OH");
        nc.setZip("44311");

        dao.addContact(nc);

        nc.setZip("99999");

        dao.updateContact(nc);

        Contact fromDb = dao.getContactById(nc.getContactId());

        assertEquals(fromDb, nc);
    }

    @Test
    public void getAllContacts() {

        Contact nc = new Contact();
        nc.setFirstName("Marc");
        nc.setLastName("Marquez");
        nc.setStreet("Woodlawn");
        nc.setCity("Detroit");
        nc.setState("MI");
        nc.setZip("48013");

        dao.addContact(nc);

        Contact nc2 = new Contact();
        nc2.setFirstName("Valentino");
        nc2.setLastName("Rossi");
        nc2.setStreet("Main");
        nc2.setCity("Akron");
        nc2.setState("OH");
        nc2.setZip("44311");

        dao.addContact(nc2);

        List<Contact> cList = dao.getAllContacts();
        assertEquals(cList.size(), 2);
    }

    @Test
    public void searchContacts() {

        Contact nc = new Contact();
        nc.setFirstName("Marc");
        nc.setLastName("Lorenzo");
        nc.setStreet("Woodlawn");
        nc.setCity("Detroit");
        nc.setState("MI");
        nc.setZip("48013");

        dao.addContact(nc);

        Contact nc2 = new Contact();
        nc2.setFirstName("Valentino");
        nc2.setLastName("Rossi");
        nc2.setStreet("Main");
        nc2.setCity("Akron");
        nc2.setState("OH");
        nc2.setZip("44311");

        dao.addContact(nc2);

        Contact nc3 = new Contact();
        nc3.setFirstName("Jorge");
        nc3.setLastName("Lorenzo");
        nc3.setStreet("Griffith");
        nc3.setCity("Davidson");
        nc3.setState("NC");
        nc3.setZip("28036");

        dao.addContact(nc3);

        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.FIRST_NAME, "Valentino");
        List<Contact> cList = dao.searchContacts(criteria);
        assertEquals(1, cList.size());
        assertEquals(nc2, cList.get(0));
        criteria.clear();

        criteria.put(SearchTerm.LAST_NAME, "Lorenzo");
        cList = dao.searchContacts(criteria);
        assertEquals(2, cList.size());
        criteria.clear();
        
        criteria.put(SearchTerm.STREET, "Woodlawn");
        cList = dao.searchContacts(criteria);
        assertEquals(1, cList.size());
        assertEquals(nc, cList.get(0));
        criteria.clear();
        
        criteria.put(SearchTerm.CITY, "Davidson");
        cList = dao.searchContacts(criteria);
        assertEquals(1, cList.size());
        assertEquals(nc3, cList.get(0));
        criteria.clear();
        
        criteria.put(SearchTerm.STATE, "GA");
        cList = dao.searchContacts(criteria);
        assertEquals(0, cList.size());
        criteria.clear();
        
        criteria.put(SearchTerm.ZIP, "28036");
        cList = dao.searchContacts(criteria);
        assertEquals(1, cList.size());
        assertEquals(nc3, cList.get(0));

    }

}
