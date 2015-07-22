/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.swcguild.dvdlibrarymvc.dao.DvdLibraryDao;
import com.swcguild.dvdlibrarymvc.model.Dvd;
import com.swcguild.dvdlibrarymvc.model.SearchTerm;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author admin
 */
public class DvdLibraryDaoTest {

    private DvdLibraryDao dao;

    public DvdLibraryDaoTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("DvdLibraryDao", DvdLibraryDao.class);
    }

    @After
    public void tearDown() {
    }

//    @Test
//    public void addGetDeleteDvd() {
//
//        ArrayList<String> noteList = new ArrayList<>();
//        noteList.add("Provocative and emotional");
//        Dvd d = new Dvd();
//        d.setTitle("Whiplash");
//        d.setReleaseDate(LocalDate.parse("2014-10-15"));
//        d.setMpaaRating("R");
//        d.setDirectorName("Damien Chazelle");
//        d.setStudio("Sony Pictures");
//        d.setUserNote(noteList);
//
//        dao.addDVD(d);
//
//        Dvd fromDb = dao.findDVDByID(d.getDvdId());
//
//        Assert.assertEquals(fromDb, d);
//
//        dao.removeDVD(d.getDvdId());
//
//        Assert.assertNull(dao.findDVDByID(d.getDvdId()));
//
//    }
//
//    @Test
//    public void addUpdateContact() {
//
//        ArrayList<String> noteList = new ArrayList<>();
//        noteList.add("Provocative and emotional");
//        Dvd d = new Dvd();
//        d.setTitle("Whiplash");
//        d.setReleaseDate(LocalDate.parse("2014-10-15"));
//        d.setMpaaRating("R");
//        d.setDirectorName("Damien Chazelle");
//        d.setStudio("Sony Pictures");
//        d.setUserNote(noteList);
//
//        dao.addDVD(d);
//
//        d.setDirectorName("Jeff Gordon");
//        dao.editDVD(d);
//
//        Dvd fromDb = dao.findDVDByID(d.getDvdId());
//
//        Assert.assertEquals(fromDb, d);
//
//    }
//
//    @Test
//    public void getAllContacts() {
//
//        ArrayList<String> noteList = new ArrayList<>();
//        noteList.add("Provocative and emotional");
//        Dvd d = new Dvd();
//        d.setTitle("Whiplash");
//        d.setReleaseDate(LocalDate.parse("2014-10-15"));
//        d.setMpaaRating("R");
//        d.setDirectorName("Damien Chazelle");
//        d.setStudio("Sony Pictures");
//        d.setUserNote(noteList);
//
//        dao.addDVD(d);
//
//        ArrayList<String> noteList2 = new ArrayList<>();
//        noteList2.add("Exciting 3D action adventure movie");
//        Dvd d2 = new Dvd();
//        d2.setTitle("Jurassic World");
//        d2.setReleaseDate(LocalDate.parse("2015-06-10"));
//        d2.setMpaaRating("PG-13");
//        d2.setDirectorName("Colin Trevorrow");
//        d2.setStudio("Amblin Entertainment");
//        d2.setUserNote(noteList2);
//
//        dao.addDVD(d2);
//
//        Dvd[] dList = dao.getAllDVDs();
//
//        Assert.assertEquals(2, dList.length);
//
//    }
//
//    @Test
//    public void findDvds() {
//
//        ArrayList<String> noteList = new ArrayList<>();
//        noteList.add("Provocative and emotional");
//        Dvd d = new Dvd();
//        d.setTitle("Whiplash");
//        d.setReleaseDate(LocalDate.parse("2014-10-15"));
//        d.setMpaaRating("R");
//        d.setDirectorName("Damien Chazelle");
//        d.setStudio("Sony Pictures");
//        d.setUserNote(noteList);
//
//        dao.addDVD(d);
//
//        ArrayList<String> noteList2 = new ArrayList<>();
//        noteList2.add("Exciting 3D action adventure movie");
//        Dvd d2 = new Dvd();
//        d2.setTitle("Jurassic World");
//        d2.setReleaseDate(LocalDate.parse("2015-06-10"));
//        d2.setMpaaRating("PG-13");
//        d2.setDirectorName("Colin Trevorrow");
//        d2.setStudio("Amblin Entertainment");
//        d2.setUserNote(noteList2);
//
//        dao.addDVD(d2);
//
//        ArrayList<String> noteList3 = new ArrayList<>();
//        noteList3.add("A good mix of thriller, drama and action");
//        Dvd d3 = new Dvd();
//        d3.setTitle("Children of Men");
//        d3.setReleaseDate(LocalDate.parse("2007-01-05"));
//        d3.setMpaaRating("R");
//        d3.setDirectorName("Alfonso Cuaron");
//        d3.setStudio("Universal Pictures");
//        d3.setUserNote(noteList2);
//
//        dao.addDVD(d3);
//        
//        ArrayList<String> noteList4 = new ArrayList<>();
//        noteList4.add("Haven't seen it yet");
//        Dvd d4 = new Dvd();
//        d4.setTitle("Y Tu Mama Tambien");
//        d4.setReleaseDate(LocalDate.parse("2002-04-26"));
//        d4.setMpaaRating("R");
//        d4.setDirectorName("Alfonso Cuaron");
//        d4.setStudio("20th Century Fox");
//        d4.setUserNote(noteList2);
//
//        dao.addDVD(d4);
//        
//        Map<SearchTerm, String> criteria = new HashMap<>();
//        
//        criteria.put(SearchTerm.DIRECTOR_NAME, "Alfonso Cuaron");
//        List<Dvd> cList = dao.searchDvds(criteria);
//        
//        Assert.assertEquals(2, cList.size());
//        Assert.assertEquals(d3, cList.get(0));
//        
//        criteria.put(SearchTerm.DIRECTOR_NAME, "George Lucas");
//        cList = dao.searchDvds(criteria);
//        Assert.assertEquals(0, cList.size());
//        
//        criteria.clear();
//        criteria.put(SearchTerm.MPAA_RATING, "R");
//        cList = dao.searchDvds(criteria);
//        Assert.assertEquals(3, cList.size());
//        Assert.assertEquals(d, cList.get(0));
//        
//        criteria.clear();
//        criteria.put(SearchTerm.TITLE, "Jurassic World");
//        cList = dao.searchDvds(criteria);
//        Assert.assertEquals(1, cList.size());
//        Assert.assertEquals(d2, cList.get(0));
//        Assert.assertEquals("Amblin Entertainment", cList.get(0).getStudio());
//
//    }

}
