/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibraryv3.dao;

import com.swcguild.dvdlibrary.dao.DvdLibraryDao;
import com.swcguild.dvdlibrary.dto.Dvd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */

public class DvdLibraryDaoImplMary implements DvdLibraryDao { //couuld call this one DvdLibraryDaoMemImpl--because it's the memory implementation
    
    static int counter = 0;
    private HashMap<Integer, Dvd> dvdCollection = new HashMap<>(); //if this is private, you need to make a getter and setter for it to be available
                        //outside of class--even in an instance


    public HashMap<Integer, Dvd> getDvdCollection() {
        return dvdCollection;
    }

    public void setDvdCollection(HashMap<Integer, Dvd> dvdCollection) {
        this.dvdCollection = dvdCollection;
    }
    

    @Override
    public void add(Dvd dvd) {
        ++counter;
        dvd.setId(counter); //will recognize this because is part of the DTO--we are setting on the object
         dvdCollection.put(dvd.getId(), dvd); // here we are getting the id we just set when we add to the collection so that the HashMap is keeping track
    }

    @Override
    public void remove(int id) {
        dvdCollection.remove(id);
    }

    @Override
    public List<Dvd> listAll() {
         List<Dvd> dvdList = new ArrayList<Dvd>(dvdCollection.values());
        return dvdList;
    }

    @Override
    public Dvd getById(int id) {
       return dvdCollection.get(id);
    }

    @Override
    public List<Dvd> getByTitle(String title) {
        List<Dvd> titleMatchList = dvdCollection.values().stream()
                .filter(d -> d.getTitle().contains(title)).collect(Collectors.toList());
        return titleMatchList;
    }

    @Override
    public List<Dvd> getByRating(String rating) {
         List<Dvd> titlesByRating = dvdCollection.values()
                .stream()
                .filter(d -> d.getMpaaRating().equalsIgnoreCase(rating)).collect(Collectors.toList());
        return titlesByRating;
    }

    @Override
    public List<Dvd> getByStudio(String studio) {
        List<Dvd> titlesByStudio = dvdCollection.values()
                .stream()
                .filter(d -> d.getStudio().equalsIgnoreCase(studio)).collect(Collectors.toList());
        return titlesByStudio;
        

    }//end studio method
    
}