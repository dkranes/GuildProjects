/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.dao;

import com.swcguild.dvdlibrarymvc.model.Dvd;
import com.swcguild.dvdlibrarymvc.model.SearchTerm;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class DvdLibraryDaoDbImpl implements DvdLibraryDao {

    //question mark style means they're ordered.  in the addContact method we need to keep the same order otherwise we get wrong data

    private static final String SQL_INSERT_DVD = "INSERT INTO dvd_library (title, release_date, mpaa_rating, director_name, studio_name, user_note) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_DVD = "DELETE FROM dvd_library WHERE contact_id = ?";
    private static final String SQL_UPDATE_DVD = "UPDATE dvd_library SET title=?, release_date=?, mpaa_rating=?, director_name=?, studio_name=?, user_note=? WHERE dvd_id=?";
    private static final String SQL_SELECT_ALL_DVDS = "SELECT * FROM dvd_library";
    private static final String SQL_SELECT_DVD = "SELECT * FROM dvd_library WHERE dvd_id=?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {    //creating a setter for Spring setter injection
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDVD(Dvd d) {
        jdbcTemplate.update(SQL_INSERT_DVD,
                d.getTitle(),
                d.getReleaseDate(),
                d.getMpaaRating(),
                d.getDirectorName(),
                d.getStudio(),
                d.getUserNote());

        d.setDvdId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));

        return d;
    }
    
    @Override
    public Dvd removeDVD(Integer id) {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new ContactMapper());
    }
    
    @Override
    public void updateDVD(Dvd d) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                d.getTitle(),
                d.getReleaseDate(),
                d.getMpaaRating(),
                d.getDirectorName(),
                d.getStudio(),
                d.getUserNote());
    }
    
    @Override
    public List<Dvd> getAllDVDs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new ContactMapper());
    }
    
    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        if (criteria == null || criteria.size() == 0) {
            return getAllDVDs();
        }

        StringBuilder query = new StringBuilder("SELECT * FROM contacts WHERE ");

        int numParams = criteria.size();
        int paramPosition = 0;

        String[] paramVals = new String[numParams];

        Set<SearchTerm> keyset = criteria.keySet();
        Iterator<SearchTerm> iter = keyset.iterator();

        while (iter.hasNext()) {
            SearchTerm currentKey = iter.next();
            String currentValue = criteria.get(currentKey);

            if (paramPosition > 0) {
                query.append(" and ");
            }

            query.append(currentKey);
            query.append(" = ?");

            paramVals[paramPosition] = currentValue;
            paramPosition++;

        }
        return jdbcTemplate.query(query.toString(), new ContactMapper(), paramVals);
    }

    

    @Override
    public Integer[] getAllIDs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

    

    @Override
    public Dvd findDVDByID(Integer id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD, new ContactMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void addUserNote(Integer id, String userNote) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
