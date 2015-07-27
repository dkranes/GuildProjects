/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.model;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author apprentice
 */
public class Dvd {
    
    private String userNote;

    private ArrayList<String> allUserNotes = new ArrayList<String>();
    private Object TemporalType;

    @NotEmpty(message = "You must supply a value for Title")
    @Length(max = 50, message = "Title must be no more than 50 characters")
    private String title;

    
    //@Temporal(TemporalType.DATE)
    @Past(message = "You must supply a value for Release Date")
    @Future(message = "You must supply a current or past date for Release Date...bitch")
    @Lazy
    @NotNull(message = "You must supply a value for Release Date")
    @DateTimeFormat(iso = ISO.DATE)//(pattern = "MM-dd-yyyy")
    private Date finalDate;
    private LocalDate releaseDate;
    
    
    
    
    //private LocalDate releaseDateForJs;
    

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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
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

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {


        
        this.finalDate = finalDate;
    }

}
