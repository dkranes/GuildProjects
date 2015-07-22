/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibraryv3.dao;

import com.swcguild.dvdlibrary.dao.DvdLibraryDao;
import com.swcguild.dvdlibrary.dto.Dvd;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DVDLibraryDaoImplClay implements DvdLibraryDao {
    
    public HashMap<Integer, Dvd> dvds = new HashMap<>();

    @Override
    public void add(Dvd dvd) {
        dvds.put(dvd.getId(), dvd);
    }

    @Override
    public void remove(int id) {
         dvds.remove(id);
    }

    @Override
    public List<Dvd> listAll() {
        return Arrays.asList(dvds.values().toArray(new Dvd[dvds.values().size()]));
    }

    @Override
    public Dvd getById(int id) {
        return dvds.get(id);
    }

    @Override
    public List<Dvd> getByTitle(String title) {
        List<Dvd> matches = new ArrayList<>();
        for (Dvd e : dvds.values()) {
            if (e.getTitle().equalsIgnoreCase(title)) {
                matches.add(e);
            }
        }
        
        if (matches.isEmpty())
            return null;
        else
            return matches;
    }

    @Override
    public List<Dvd> getByRating(String rating) {
        List<Dvd> dvdsByRating = dvds.values()
                .stream()
                .filter(s->s.getMpaaRating().equalsIgnoreCase(rating))
                .collect(Collectors.toList());
        return dvdsByRating;
    }

    @Override
    public List<Dvd> getByStudio(String studio) {
        List<Dvd>dvdsByStudio = dvds.values()
                .stream()
                .filter(s->s.getStudio().equalsIgnoreCase(studio))
                .collect(Collectors.toList());
        return dvdsByStudio;
    }

}
