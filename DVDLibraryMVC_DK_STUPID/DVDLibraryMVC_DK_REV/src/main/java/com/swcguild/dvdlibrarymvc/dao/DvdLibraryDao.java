/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.dao;

import com.swcguild.dvdlibrarymvc.model.Dvd;
import com.swcguild.dvdlibrarymvc.model.SearchTerm;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface DvdLibraryDao {

//    public void loadDVDLibrary() throws ParseException, FileNotFoundException;
//
//    public void writeToDVDLibrary() throws IOException;

    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria);

    //public Dvd[] getAllDVDs();
    
    public List<Dvd> getAllDVDs();

    public Integer[] getAllIDs();

    public Dvd addDVD(Dvd d);

    public void updateDVD(Dvd d);

    public Dvd removeDVD(Integer id);

    public Dvd findDVDByID(Integer id);

}
