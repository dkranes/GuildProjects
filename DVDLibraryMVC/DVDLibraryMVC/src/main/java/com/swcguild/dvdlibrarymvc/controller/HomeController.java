/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.controller;

import com.swcguild.dvdlibrarymvc.dao.DvdLibraryDao;
import com.swcguild.dvdlibrarymvc.model.Dvd;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {

    private DvdLibraryDao dao;
    static int counter = 0;

    @Inject
    public HomeController(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Dvd getDvd(@PathVariable("id") int dvdId) {
        return dao.findDVDByID(dvdId);
    }

    @RequestMapping(value = "/dvd", method = RequestMethod.POST)
    @ResponseBody
    public Dvd addDvd(@Valid @RequestBody Dvd dvd) {

        dao.addDVD(dvd);
        return dvd;
    }
    
    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addDvdNote(@PathVariable("id") int id, @RequestBody String note){
        
        dao.addUserNote(id, note);
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDvd(@PathVariable("id") int id) {
        dao.removeDVD(id);
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editDvd(@PathVariable("id") int id, @RequestBody Dvd dvd) {
        dvd.setDvdId(id);
        dao.updateDVD(dvd);
    }

    @RequestMapping(value = "/dvds", method = RequestMethod.GET)
    @ResponseBody
    public Dvd[] getAllDvds() {
        return dao.getAllDVDs();
    }

}
