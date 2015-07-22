/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.contactlistmvc.dao;

import com.swcguild.contactlistmvc.model.Contact;
import com.swcguild.contactlistmvc.model.SearchTerm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.Assert;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class ContactListDaoTest {

    private ContactListDao dao;

    public ContactListDaoTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("contactListDao", ContactListDao.class);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void addGetDeleteContact() {
        Contact nc = new Contact();
        nc.setFirstName("John");
        nc.setLastName("Doe");
        nc.setCompany("Oracle");
        nc.setEmail("john@doe.com");
        nc.setPhone("1234445678");

        dao.addContact(nc);

        Contact fromDb = dao.getContactById(nc.getContactId());

        Assert.assertEquals(fromDb, nc);

        dao.removeContact(nc.getContactId());

        Assert.assertNull(dao.getContactById(nc.getContactId()));

    }

    @Test
    public void addUpdateContact() {
        Contact nc = new Contact();
        nc.setFirstName("Jimmy");
        nc.setLastName("Smith");
        nc.setCompany("Sun");
        nc.setEmail("jimmy@smith.com");
        nc.setPhone("1112223333");

        dao.addContact(nc);
        
        nc.setPhone("9999999999");
        dao.updateContact(nc);
        
        Contact fromDb = dao.getContactById(nc.getContactId());
        
        Assert.assertEquals(fromDb, nc);

    }
    
    @Test
    public void getAllContacts() {
        Contact nc = new Contact();
        nc.setFirstName("Jimmy");
        nc.setLastName("Smith");
        nc.setCompany("Sun");
        nc.setEmail("jimmy@smith.com");
        nc.setPhone("1112223333");

        dao.addContact(nc);
        
        Contact nc2 = new Contact();
        nc2.setFirstName("John");
        nc2.setLastName("Jones");
        nc2.setCompany("Apple");
        nc2.setEmail("john@jones.com");
        nc2.setPhone("5556667777");

        dao.addContact(nc2);
        
        List<Contact> cList = dao.getAllContacts();
        
        Assert.assertEquals(2, cList.size());
    
    }
    
    @Test
    public void searchContacts() {
        Contact nc = new Contact();
        nc.setFirstName("Jimmy");
        nc.setLastName("Smith");
        nc.setCompany("Sun");
        nc.setEmail("jimmy@smith.com");
        nc.setPhone("1112223333");

        dao.addContact(nc);

        Contact nc2 = new Contact();
        nc2.setFirstName("John");
        nc2.setLastName("Jones");
        nc2.setCompany("Apple");
        nc2.setEmail("john@jones.com");
        nc2.setPhone("5556667777");

        dao.addContact(nc2);
        
        Contact nc3 = new Contact();
        nc3.setFirstName("Steve");
        nc3.setLastName("Smith");
        nc3.setCompany("Microsoft");
        nc3.setEmail("steve@msft.com");
        nc3.setPhone("5552221234");
        
        dao.addContact(nc3);
        
        Map<SearchTerm, String> criteria = new HashMap<>();
        
        criteria.put(SearchTerm.LAST_NAME, "Jones");
        List<Contact> cList = dao.searchContacts(criteria);
        
        Assert.assertEquals(1, cList.size());
        assertEquals(nc2, cList.get(0));
        
        criteria.put(SearchTerm.LAST_NAME, "Smith");
        cList = dao.searchContacts(criteria);
        assertEquals(2, cList.size());
        
        criteria.put(SearchTerm.COMPANY, "Sun");
        cList = dao.searchContacts(criteria);
        assertEquals(1, cList.size());
        assertEquals(nc, cList.get(0));
        
        criteria.put(SearchTerm.COMPANY, "Microsoft");
        cList = dao.searchContacts(criteria);
        assertEquals(1, cList.size());
        assertEquals(nc3, cList.get(0));
        
        criteria.put(SearchTerm.COMPANY, "Apple");
        cList = dao.searchContacts(criteria);
        assertEquals(0, cList.size());
        
    
    }
}
