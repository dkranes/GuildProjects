/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.swcguild.dvdlibrarymvc.dao.DvdLibraryDao;
import com.swcguild.dvdlibrarymvc.model.Dvd;
import com.swcguild.dvdlibrarymvc.model.SearchTerm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

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

    @Test
    public void addGetDeleteDvd() throws ParseException {
        
        
        Dvd d = new Dvd();
        d.setTitle("Whiplash");
        d.setReleaseDate(sdf.parse("10/15/2014"));
        d.setMpaaRating("R");
        d.setDirectorName("Damien Chazelle");
        d.setStudio("Sony Pictures");
        d.setUserNote("Provocative and emotional");

        dao.addDVD(d);

        Dvd fromDb = dao.findDVDByID(d.getDvdId());

        Assert.assertEquals(fromDb, d);

        dao.removeDVD(d.getDvdId());

        Assert.assertNull(dao.findDVDByID(d.getDvdId()));

    }

    @Test
    public void addUpdateContact() throws ParseException {

        
        Dvd d = new Dvd();
        d.setTitle("Whiplash");
        d.setReleaseDate(sdf.parse("10/15/2014"));
        d.setMpaaRating("R");
        d.setDirectorName("Damien Chazelle");
        d.setStudio("Sony Pictures");
        d.setUserNote("Provocative and emotional");

        dao.addDVD(d);

        d.setDirectorName("Jeff Gordon");
        dao.updateDVD(d);

        Dvd fromDb = dao.findDVDByID(d.getDvdId());

        Assert.assertEquals(fromDb, d);

    }

    @Test
    public void getAllDvds() throws ParseException {

        Dvd d = new Dvd();
        d.setTitle("Whiplash");
        d.setReleaseDate(sdf.parse("10/15/2014"));
        d.setMpaaRating("R");
        d.setDirectorName("Damien Chazelle");
        d.setStudio("Sony Pictures");
        d.setUserNote("Provocative and emotional");

        dao.addDVD(d);

        Dvd d2 = new Dvd();
        d2.setTitle("Jurassic World");
        d2.setReleaseDate(sdf.parse("10/15/2014"));
        d2.setMpaaRating("PG-13");
        d2.setDirectorName("Colin Trevorrow");
        d2.setStudio("Amblin Entertainment");
        d2.setUserNote("Exciting 3D action adventure movie");

        dao.addDVD(d2);

        Dvd[] dList = dao.getAllDVDs();

        Assert.assertEquals(2, dList.length);
        Assert.assertEquals(dList[0].getDirectorName(), "Damien Chazelle");

    }

    @Test
    public void findDvds() throws ParseException {

        Dvd d = new Dvd();
        d.setTitle("Whiplash");
        d.setReleaseDate(sdf.parse("10/15/2014"));
        d.setMpaaRating("R");
        d.setDirectorName("Damien Chazelle");
        d.setStudio("Sony Pictures");
        d.setUserNote("Provocative and emotional");

        dao.addDVD(d);

        Dvd d2 = new Dvd();
        d2.setTitle("Jurassic World");
        d2.setReleaseDate(sdf.parse("06/10/20115"));
        d2.setMpaaRating("PG-13");
        d2.setDirectorName("Colin Trevorrow");
        d2.setStudio("Amblin Entertainment");
        d2.setUserNote("Exciting 3D action adventure movie");

        dao.addDVD(d2);

        Dvd d3 = new Dvd();
        d3.setTitle("Children of Men");
        d3.setReleaseDate(sdf.parse("01/05/2007"));
        d3.setMpaaRating("R");
        d3.setDirectorName("Alfonso Cuaron");
        d3.setStudio("Universal Pictures");
        d3.setUserNote("A good mix of thriller, drama and action");

        dao.addDVD(d3);
        
        Dvd d4 = new Dvd();
        d4.setTitle("Y Tu Mama Tambien");
        d4.setReleaseDate(sdf.parse("04/26/2002"));
        d4.setMpaaRating("R");
        d4.setDirectorName("Alfonso Cuaron");
        d4.setStudio("20th Century Fox");
        d4.setUserNote("Haven't seen it yet");

        dao.addDVD(d4);
        
        Map<SearchTerm, String> criteria = new HashMap<>();
        
        criteria.put(SearchTerm.DIRECTOR_NAME, "Alfonso Cuaron");
        List<Dvd> cList = dao.searchDvds(criteria);
        
        Assert.assertEquals(2, cList.size());
        Assert.assertEquals(d3, cList.get(0));
        
        criteria.put(SearchTerm.DIRECTOR_NAME, "George Lucas");
        cList = dao.searchDvds(criteria);
        Assert.assertEquals(0, cList.size());
        
        criteria.clear();
        criteria.put(SearchTerm.MPAA_RATING, "R");
        cList = dao.searchDvds(criteria);
        Assert.assertEquals(3, cList.size());
        Assert.assertEquals(d, cList.get(0));
        
        criteria.clear();
        criteria.put(SearchTerm.TITLE, "Jurassic World");
        cList = dao.searchDvds(criteria);
        Assert.assertEquals(1, cList.size());
        Assert.assertEquals(d2, cList.get(0));
        Assert.assertEquals("Amblin Entertainment", cList.get(0).getStudio());

    }
    
    @Test
    public void findMultipleNotes() throws ParseException{
        
        Dvd d = new Dvd();
        d.setTitle("Whiplash");
        d.setReleaseDate(sdf.parse("10/15/2014"));
        d.setMpaaRating("R");
        d.setDirectorName("Damien Chazelle");
        d.setStudio("Sony Pictures");
        d.setUserNote("Provocative and emotional");

        dao.addDVD(d);
        
        dao.addUserNote(d.getDvdId(), "Second Note");
        
        Assert.assertEquals(d.getAllUserNotes().size(), 2);
        Assert.assertEquals(d.getAllUserNotes().get(0), "Provocative and emotional");
    
    }

}
