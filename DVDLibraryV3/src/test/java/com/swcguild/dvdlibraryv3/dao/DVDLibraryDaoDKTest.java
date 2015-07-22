/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibraryv3.dao;

import com.swcguild.dvdlibrary.dao.DvdLibraryDao;
import com.swcguild.dvdlibrary.dto.Dvd;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class DVDLibraryDaoDKTest {

    ApplicationContext ctx;

    DvdLibraryDao dao;

    Dvd a;
    Dvd b;
    Dvd c;

    public DVDLibraryDaoDKTest() {
    }

    @Before
    public void setUp() throws ParseException, FileNotFoundException {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = ctx.getBean("dvdLibraryDao", DvdLibraryDao.class);

        a = new Dvd();
        b = new Dvd();
        c = new Dvd();

        a.setId(1);
        a.setTitle("Mad Max: Fury Road");
        a.setReleaseDate(LocalDate.parse("2015-05-15"));
        a.setMpaaRating("Test-R");
        a.setDirector("George Miller");
        a.setStudio("Test-Universal");
        a.setNote("okay");

        b.setId(2);
        b.setTitle("TestTitle");
        b.setReleaseDate(LocalDate.parse("2015-06-15"));
        b.setMpaaRating("Test-R");
        b.setDirector("testDir");
        b.setStudio("Test-Sony");
        b.setNote("notes b");

        c.setId(3);
        c.setTitle("AnotherTitle");
        c.setReleaseDate(LocalDate.parse("2015-07-15"));
        c.setMpaaRating("Test-R");
        c.setDirector("George Miller");
        c.setStudio("Test-Sony");
        c.setNote("notes c");

    }

    @After
    public void tearDown() {

        for (Dvd d : dao.listAll()) {
            int i = 0;
            dao.remove(i);
            i++;
        }
    }

    @Test
    public void add() {
        //set
        dao.add(a);
        dao.add(b);

        //act
        
        Dvd result = dao.getById(a.getId());
        
        //assert
        Assert.assertEquals(a, result);
        
        Dvd result2 = dao.getById(b.getId());
        Assert.assertEquals(b, result2);

    }

    @Test
    public void remove() {
        //set
        dao.add(a);
        dao.add(b);
        dao.add(c);

        //act
        dao.remove(a.getId());
        List<Dvd> result = dao.listAll();

        //assert
        Assert.assertEquals(2, result.size());
        
        dao.remove(b.getId());
        result = dao.listAll();
        
        Assert.assertEquals(1, result.size());

        Assert.assertEquals(null, dao.getById(b.getId()));
    }

    @Test
    public void listAll() {
        //set
        dao.add(a);
        dao.add(b);
        dao.add(c);
        //act
        List<Dvd> result = dao.listAll();
        //assert
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("George Miller", result.get(0).getDirector());
        Assert.assertEquals("testDir", result.get(1).getDirector());
        Assert.assertEquals("George Miller", result.get(2).getDirector());
    }

    @Test
    public void getById() {
        //set
        dao.add(a);
        dao.add(b);
        dao.add(c);
        //act
        //Dvd result1 = dao.getById(a.getId());
        Dvd result1 = dao.getById(a.getId());
        Dvd result2 = dao.getById(b.getId());
        Dvd result3 = dao.getById(c.getId());

        //assert
        Assert.assertEquals("George Miller", result1.getDirector());

    }

    @Test
    public void getByTitle() {
        //set
        dao.add(b);
        //act
        List<Dvd> result = dao.getByTitle("TestTitle");
        System.out.println(result);
        //assert
        Assert.assertEquals(b, result.get(0));
        Assert.assertEquals("testDir", result.get(0).getDirector());

    }

    @Test
    public void getByRating() {
        //set
        dao.add(a);

        //act
        List<Dvd> result = dao.getByRating("Test-R");

        //assert
        Assert.assertEquals(a, result.get(0));
        Assert.assertEquals("George Miller", result.get(0).getDirector());

    }

    @Test
    public void getByStudio() {
        //set
        dao.add(a);
        dao.add(b);
        dao.add(c);

        //act
        List<Dvd> result = dao.getByStudio("Test-Universal");

        //assert
        Assert.assertEquals(a, result.get(0));
        Assert.assertEquals("Test-Universal", result.get(0).getStudio());

    }

    /**
     * Test of loadDVDLibrary method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testLoadDVDLibrary() throws Exception {
//        Assert.assertEquals(7, dLib.librarySize());
//    }
    /**
     * Test of writeToDVDLibrary method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testWriteToDVDLibrary() throws Exception {
//        Assert.assertEquals(7, dLib.librarySize());
//
//        dLib.addDVD(a);
//        Assert.assertEquals(8, dLib.librarySize());
//        //should have 4 now
//        dLib.writeToDVDLibrary();
//        for (Integer i : dLib.getAllIDs()) {
//            dLib.removeDVD(i);
//        }
//        dLib.loadDVDLibrary();
//        //should have 4
//        Assert.assertEquals(8, dLib.librarySize());
//        List<Dvd> arr = dLib.findDVDsByTitle("Mad Max: Fury Road");
//        Assert.assertEquals(arr.size(), 1);
////must be Mad Max
//        Dvd d = arr.get(0);
//        dLib.removeDVD(d.getId());
//        //should have 3 now
//        dLib.writeToDVDLibrary();
//        for (Integer i : dLib.getAllIDs()) {
//            dLib.removeDVD(i);
//        }
//        dLib.loadDVDLibrary();
//        Assert.assertEquals(7, dLib.librarySize());;
//    }
    /**
     * Test of getAllDVDs method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testGetAllDVDs() {
//        Dvd[] arr = dLib.getAllDVDs();
//        Assert.assertEquals(7, arr.length);
//        for (Integer i : dLib.getAllIDs()) {
//            dLib.removeDVD(i);
//        }
//        arr = dLib.getAllDVDs();
//        Assert.assertTrue(arr == null);
//        dLib.addDVD(b);
//        arr = dLib.getAllDVDs();
//        Assert.assertEquals(1, arr.length);
//
//    }
    /**
     * Test of addDVD method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testAddDVD() {
//        dLib.addDVD(a);
//        Assert.assertEquals(8, dLib.librarySize());
//        dLib.addDVD(b);
//        Assert.assertEquals(9, dLib.librarySize());
//    }
    /**
     * Test of updateDVD method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testUpdateDVD() {
//        dLib.addDVD(b);
//        String old = b.getTitle();
//        Integer id = b.getId();
//        Assert.assertTrue(old == "TestTitle");
//        b.setTitle("Better title");
//        Assert.assertTrue(b.getTitle() == "Better title");
//        dLib.updateDVD(b);
//        Dvd edited = null;
//        edited = dLib.findDVDByID(b.getId());
//        Assert.assertTrue(edited != null);
//        Assert.assertTrue(edited.getTitle() == "Better title");
//        Assert.assertEquals(b, edited);
//
//    }
    /**
     * Test of removeDVD method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testRemoveDVD() {
//        dLib.addDVD(a);
//        Dvd c = dLib.removeDVD(a.getId());
//        Assert.assertEquals(a, c);
//        Dvd d = dLib.removeDVD(1000);
//        Assert.assertEquals(null, d);
//    }
    /**
     * Test of findDVDByID method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testFindDVDByID() {
//        dLib.addDVD(a);
//        Dvd c = dLib.findDVDByID(a.getId());
//        Assert.assertEquals(a, c);
//    }
    /**
     * Test of findDVDsByTitle method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testFindDVDsByTitle() {
//        dLib.addDVD(a);
//        List<Dvd> arr = dLib.findDVDsByTitle(a.getTitle());
//        Assert.assertEquals(1, arr.size());
//        dLib.addDVD(aCopy);
//        arr = dLib.findDVDsByTitle(a.getTitle());
//        Assert.assertEquals(2, arr.size());
//        arr = dLib.findDVDsByTitle("Bogus title");
//        Assert.assertEquals(0, arr.size());
//        //Assert.assertArrayEquals(0, map.size());
//    }
    /**
     * Test of findDVDsByDirector method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testFindDVDsByDirector() {
//        dLib.addDVD(a);
//        dLib.addDVD(b);
//
//        Map<String, List<Dvd>> map = dLib.findDVDsByDirector(a.getDirector());
//        Assert.assertEquals(1, map.size());
//
//        List<Dvd> movies = map.get("Test-R");
//
//        Assert.assertEquals("George Miller", movies.get(0).getDirector());
//
//    }
    /**
     * Test of findDVDsByMPAARating method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testFindDVDsByMPAARating() {
//        dLib.addDVD(a);
//        List<Dvd> arr = dLib.findDVDsByMPAARating(a.getMpaaRating());
//        Assert.assertEquals(1, arr.size());
//        Assert.assertEquals("Test-R", arr.get(0).getMpaaRating());
//
//    }
    /**
     * Test of findDVDsByStudio method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testFindDVDsByStudio() {
//        dLib.addDVD(a);
//        List<Dvd> arr = dLib.findDVDsByStudio(a.getStudio());
//        Assert.assertEquals(1, arr.size());
//        Assert.assertEquals("Test-Universal", arr.get(0).getStudio());
//
//    }
    /**
     * Test of findDVDsByNumYears method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testfindDVDsByNumYears() {
//        dLib.addDVD(a);
//        dLib.addDVD(b);
//        dLib.addDVD(c);
//        dLib.addDVD(d);
//        int numYears = 4;
//
//        List<Dvd> arr = dLib.findDVDsByNumYears(numYears);
//        Assert.assertEquals(7, arr.size());
//
//    }
    /**
     * Test of displayAvgAgeOfLibrary method, of class DVDLibraryDAOLambdaImpl.
     */
//    @Test
//    public void testDisplayAvgAgeOfLibrary() {
//
//        DecimalFormat df = new DecimalFormat("#.#");
//
//        double avgAge = dLib.displayAvgAgeOfLibrary();
//        String answer = df.format(avgAge);
//
//        Assert.assertEquals("7.6", answer);
//
//    }
    /**
     * Test of findMostRecentDVD method, of class DVDLibraryDAPLambdaImpl.
     */
//    @Test
//    public void testFindMostRecentDVD() {
//        dLib.addDVD(a);
//        dLib.addDVD(b);
//        dLib.addDVD(c);
//        dLib.addDVD(d);
//
//        Dvd test = dLib.findMostRecentDVD();
//        Assert.assertEquals("MostRecentTitle", test.getTitle());
//    }
    /**
     * Test of findMostRecentDVD method, of class DVDLibraryDAPLambdaImpl.
     */
//    @Test
//    public void testOldestDVD() {
//        dLib.add(a);
//        dLib.add(b);
//        dLib.add(c);
//        dLib.add(d);
//        
//
//        Dvd test = dLib.findOldestDVD();
//        Assert.assertEquals("OldestTitle", test.getTitle());
//    }
    /**
     * Test of displayAvgNumNotes method, of class DVDLibraryDAPLambdaImpl.
     */
//    @Test
//    public void testDisplayAvgNumNotes() {
//
//        dLib.addDVD(a);
//        dLib.addDVD(b);
//        dLib.addDVD(c);
//
//        double notesAvg = dLib.displayAvgNumNotes();
//
//        Assert.assertEquals(2.0, notesAvg, 0);
//    }
}
