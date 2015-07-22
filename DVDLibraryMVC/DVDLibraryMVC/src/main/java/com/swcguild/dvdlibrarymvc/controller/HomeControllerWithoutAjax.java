package com.swcguild.dvdlibrarymvc.controller;

import com.swcguild.dvdlibrarymvc.dao.DvdLibraryDao;
import com.swcguild.dvdlibrarymvc.model.Dvd;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping({"/dvd"})
public class HomeControllerWithoutAjax {

    private DvdLibraryDao dao;
    static int counter=0;

    @Inject
    public HomeControllerWithoutAjax(DvdLibraryDao dao) {
        this.dao = dao;
        
    }
    
    

//    @RequestMapping(value="/loadSampleDvds", method=RequestMethod.POST)
//    public String loadSampleDvds () throws ParseException, FileNotFoundException {
//        dao.loadDVDLibrary();
//        
//        return "redirect:displayDvdLibrary";
//    }
//    
//    @RequestMapping(value="/saveSampleDvds", method=RequestMethod.POST)
//    public String saveSampleDvds () throws ParseException, FileNotFoundException, IOException {
//        dao.writeToDVDLibrary();
//        
//        return "redirect:displayDvdLibrary";
//    }
    
    
    @RequestMapping(value = "/displayDvdLibrary", method = RequestMethod.GET)
    public String displayDvdLibrary(Model model) {
        String temp = System.getProperty("user.dir");

        Dvd[] dvdLibrary = dao.getAllDVDs();


        model.addAttribute("dvdLibrary", dvdLibrary);

        return "displayDvdLibrary";
    }

    @RequestMapping(value = "/displayNewDvdForm", method = RequestMethod.GET)
    public String displayNewDvdForm() {
        return "addNewDvd";

    }

    @RequestMapping(value = "/addNewDvd", method = RequestMethod.POST)
    public String addDvd(HttpServletRequest req) throws IOException {

        String title = req.getParameter("title");
        String releaseDate = req.getParameter("releaseDate");
        String mpaaRating = req.getParameter("mpaaRating");
        String directorName = req.getParameter("directorName");
        String studio = req.getParameter("studio");
        String userNote = req.getParameter("userNote");

        ArrayList<String> noteList = new ArrayList<>();
        noteList.add(userNote);

        Dvd newDvd = new Dvd();

        newDvd.setTitle(title);
        //newDvd.setReleaseDate(LocalDate.parse(releaseDate));
        //newDvd.setReleaseDate(releaseDate);
        newDvd.setMpaaRating(mpaaRating);
        newDvd.setDirectorName(directorName);
        newDvd.setStudio(studio);
        newDvd.setUserNote(userNote/*noteList*/);

        dao.addDVD(newDvd);
        //dao.writeToDVDLibrary();

        return "redirect:displayDvdLibrary";

    }

    @RequestMapping(value = "/displayNewDvd", method = RequestMethod.GET)
    public String displayNewDvd() {
        return "displayNewDvd";
    }

    @RequestMapping(value = "/deleteDvd", method = RequestMethod.GET)
    public String deleteDvd(HttpServletRequest req) throws IOException {
        int dvdId = Integer.parseInt(req.getParameter("dvdId"));

        dao.removeDVD(dvdId);
        //dao.writeToDVDLibrary();
        return "redirect:displayDvdLibrary";

    }

    @RequestMapping(value = "/displayEditDvd", method = RequestMethod.GET)
    public String displayEditDvd(HttpServletRequest req, Model model) {
        int dvdId = Integer.parseInt(req.getParameter("dvdId"));

        Dvd dvd = dao.findDVDByID(dvdId);

        model.addAttribute("dvd", dvd);

        return "editDvdForm";

    }

    @RequestMapping(value = "/editDvd", method = RequestMethod.POST)
    public String editDvd(@Valid @ModelAttribute("dvd") Dvd dvd,
            BindingResult result) {

        if (result.hasErrors()) {
            return "editDvdForm";
        }

        dao.updateDVD(dvd);

        return "redirect:displayDvdLibrary";

    }

}
