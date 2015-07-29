/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.controller;

import com.swcguild.dvdlibrarymvc.dao.DvdLibraryDao;
import com.swcguild.dvdlibrarymvc.model.Dvd;
import com.swcguild.dvdlibrarymvc.model.SearchTerm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class SearchController {
    
    private DvdLibraryDao dao;
    
    @Inject
    public SearchController(DvdLibraryDao dao) 
    {
        this.dao = dao;
    }
    
    @RequestMapping(value="/search", method=RequestMethod.GET)
    public String displaySearchPage() 
    {
        return "search";
    }
    
    @RequestMapping(value="search/dvds", method=RequestMethod.POST)
    @ResponseBody public List<Dvd> searchDvds(@RequestBody Map<String, String> searchMap)
    {
        Map<SearchTerm, String> criteriaMap = new HashMap();
        
        String currentTerm = searchMap.get("title");
        if(!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.TITLE, currentTerm);
        }
        
                currentTerm = searchMap.get("releaseDate");
        if(!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.RELEASE_DATE, currentTerm);
        }
        
                currentTerm = searchMap.get("mpaaRating");
        if(!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.MPAA_RATING, currentTerm);
        }
        
                currentTerm = searchMap.get("directorName");
        if(!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.DIRECTOR_NAME, currentTerm);
        }
        
                currentTerm = searchMap.get("studio");
        if(!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.STUDIO, currentTerm);
        }
        
                currentTerm = searchMap.get("userNote");
        if(!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.NOTE, currentTerm);
        }
        
        return dao.searchDvds(criteriaMap);
        
    }
    
}
