/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author apprentice
 */
public class Dvd {
    
    private String userNote;

    private ArrayList<String> allUserNotes = new ArrayList<String>();

    @NotEmpty(message = "You must supply a value for Title")
    @Length(max = 50, message = "Title must be no more than 50 characters")
    private String title;

    @NotNull(message = "You must supply a value for Release Date")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Past(message = "You must supply a value for Release Date")
    private Date releaseDate;

    @NotEmpty(message = "You must supply a value for MPAA Rating")
    @Length(max = 20, message = "MPAA Rating must be no more than 20 characters")
    private String mpaaRating;
    @NotEmpty(message = "You must supply a value for Director Name")
    @Length(max = 30, message = "Director Name must be no more than 30 characters")
    private String directorName;
    @NotEmpty(message = "You must supply a value for Studio")
    @Length(max = 30, message = "Studio name must be no more than 30 characters")
    private String studio;
    private int id;

    //@NotEmpty(message = "You must supply a value for User Note")
    //@Length(max = 50, message = "User Note must be no more than 50 characters")
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.title);
        hash = 11 * hash + Objects.hashCode(this.releaseDate);
        hash = 11 * hash + Objects.hashCode(this.mpaaRating);
        hash = 11 * hash + Objects.hashCode(this.directorName);
        hash = 11 * hash + Objects.hashCode(this.studio);
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.userNote);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.directorName, other.directorName)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.userNote, other.userNote)) {
            return false;
        }
        return true;
    }

    public /*ArrayList<String>*/ String getUserNote() {

        return userNote;
    }

    public void setUserNote(/*ArrayList<String>*/String userNote) {
        
        allUserNotes.add(userNote);
        this.userNote = userNote;
        
    }

    public ArrayList<String> getAllUserNotes() {
        return allUserNotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) throws ParseException {
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = releaseDate;
//        int dd = date.getDate() + 1;
//        int mm = date.getMonth() + 1;
//        int yyyy = date.getYear();
//        if (dd < 10) {
//            dd = '0' + dd;
//        };
//        if (mm < 10) {
//            mm = '0' + mm;
//        };
//        String stringDate = (yyyy+"-"+mm+"-"+dd);
//        releaseDate = df.parse(stringDate);
        
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(releaseDate);
        int dd = cal.get(Calendar.DATE);
        int mm = (cal.get(Calendar.MONTH)+1);
        int yyyy = cal.get(Calendar.YEAR);
        
        if (dd < 10) {
            dd = 0 + dd;
        };
        if (mm < 10) {
            mm = 0 + mm;
        };
        
        String stringDate = (yyyy + "-" + (mm) + "-" + (dd));
        releaseDate = df.parse(stringDate);
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setDvdId(int id) {
        this.id = id;
    }

    public Integer getDvdId() {
        return id;
    }

    

}
