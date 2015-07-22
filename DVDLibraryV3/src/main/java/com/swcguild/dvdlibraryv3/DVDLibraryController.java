/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibraryv3;

import com.swcguild.dvdlibrary.dao.DvdLibraryDao;
import com.swcguild.dvdlibrary.dto.Dvd;
import com.swcguild.dvdlibraryv3.dao.DVDLibraryDaoImplDK;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class DVDLibraryController {

    //private Dvd dvdsRack = new Dvd();
    //DvdLibraryDao dvds;
//    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//    DvdLibraryDao dvds = ctx.getBean("dvdLibraryDao", DvdLibraryDao.class);
    private DvdLibraryDao dvds;
    private ConsoleIO con;// = new ConsoleIO();
    //private static int id;

    public DVDLibraryController(ConsoleIO con, DvdLibraryDao dvds) {
        this.con = con;
        this.dvds = dvds;

    }

    public void run() {
        //try {
        boolean keepGoing = true;
        int menuSelection = 0;
        //dvdsRack.loadDVDLibrary();

        while (keepGoing) {
            printMenu();
            menuSelection = con.readInt("Please choose from the following options:", 1, 6);
            String title;
            switch (menuSelection) {
                case 1:
                    con.print("Add DVD");
                    addDVD();
                    break;

                case 2:
                    con.print("Remove DVD");
                    removeDVD();
                    break;

                case 3:
                    con.print("Display all DVDs");
                    listDVDs();
                    break;
                case 4:
                    con.print("Search for DVDs");
                    printDVDs();
                    break;
                case 5:
                    con.print("Edit DVD");
                    editDVD();
                    break;
                case 6:
                    con.print("Exit");
                    keepGoing = false;
                    break;
                default:
                    con.print("Unknown Command");
            }
        }
        con.print("Goodbye");
//            dvdsRack.writeToDVDLibrary();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
    }

    private void printMenu() {
        con.print("MENU");
        con.print("1: Add DVD \n2: Remove DVD\n3: Display all DVDs\n"
                + "4: Search for DVDs\n5: Edit DVD\n6: Exit");
    }

    private void addDVD() {
        String title = con.readString("Enter the title: ");

        List<Dvd> arr = dvds.getByTitle(title);  //dvdsRack.findDVDsByTitle(title);
        if (arr.isEmpty()) {
            con.print("There's no DVD with the title " + title);

        } else {
            con.print("There're already DVDs with that title. ");
            if (con.readYorN("Do you want to change the title? [Y/N]").equalsIgnoreCase("Y")) {
                title = con.readString("Enter the title: ");
            }
        }
        String dateStr = con.readDate("Enter the release date in format YYYY-mm-dd");
        LocalDate date = LocalDate.parse(dateStr);

        String mpaaRating = con.readMPAARating("Enter the mpaa rating as \"G\", \"PG\", \"PG-13\", \"R\", \"NC-17\":");
        String directorName = con.readString("Enter the director name");
        String studio = con.readString("Enter the studio name");
        String userNotes = con.readString("Enter your note");
        //userNotes.add(con.readString("Enter your note"));
        //System.out.println(userNotes);
        Dvd d = new Dvd();//(title, date, mpaaRating, directorName, studio, userNotes);
        
        //id++;
        d.setTitle(title);
        d.setReleaseDate(date);
        d.setMpaaRating(mpaaRating);
        d.setDirector(directorName);
        d.setStudio(studio);
        d.setNote(userNotes);
        //d.setId(id);

        printDVD(d);
        dvds.add(d);

    }

    private void removeDVD() {
        String title = con.readString("Enter the DVD title you wish to remove: ");

        List<Dvd> arr = dvds.getByTitle(title);//dvdsRack.findDVDsByTitle(title);
        if (arr.size() == 0) {
            System.out.println(title + " doesn't exist in our DVD library");
        } else if (arr.size() == 1) {
            dvds.remove(arr.get(0).getId());//dvdsRack.removeDVD(arr.get(0).getDvdId());
            System.out.println("DVD successfully removed");

        } else {
            for (Dvd d : arr) {
                printDVD(d);
            }
            int input = con.readInt("Enter t1"
                    + "he ID of the DVD you wish to remove");
            if (dvds.getById(input) == null) {
                System.out.println("No DVD with id: " + input + " found");
            } else {
                dvds.remove(input);
                System.out.println("We successfully remove the DVD with id: " + input);
            }
        }

    }

    private void editDVD() {
        Dvd toEdit = null;
        boolean done = false;
        String title = con.readString("Please enter the title");
        List<Dvd> arr = dvds.getByTitle(title);  //dvdsRack.findDVDsByTitle(title);
        if (arr.size() == 1) {
            toEdit = arr.get(0);
        } else if (arr.size() == 0) {
            System.out.println(title + " doesn't exist in our DVD library");
            return;
        } else if (arr.size() >= 2) {
            for (Dvd d : arr) {
                printDVD(d);
            }
            int input = con.readInt("Enter the ID of the DVD you wish to edit");
            toEdit = dvds.getById(input);//dvdsRack.findDVDByID(input);
            if (toEdit == null) {
                con.print("No DVD with id: " + input + " found");
                return;
            }
        }
        while (done == false) {
            int editChoice = printEditMenu();
            switch (editChoice) {
                case 1:
                    editTitle(toEdit);
                    break;
                case 2:// edited = 
                    editReleaseDate(toEdit);
                    break;
                case 3:
                    editMpaaRating(toEdit);
                    break;
                case 4:
                    editDirectorName(toEdit);
                    break;
                case 5:
                    editStudio(toEdit);
                    break;
                case 6:
                    editUserNote(toEdit);
                    break;
                case 7:
                    done = true;
                    break;
            }
        }
    }

    private void printDVDs() {

        int menuChoice = con.readInt("1: Search by Title\n2: Search by MPAA Rating\n3: Search by Studio", 1, 3);

        switch (menuChoice) {

            case 1:
                try {
                    String title = con.readString("Enter the DVD Title: ");
                    List<Dvd> arrTitle = dvds.getByTitle(title);//dvdsRack.findDVDsByTitle(title);
                    if (arrTitle.size() == 0) {
                        System.out.println(title + " doesn't exist in our DVD library");
                    } else {
                        for (Dvd d : arrTitle) {
                            printDVD(d);
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("No DVD found by that title.");
                }
                break;

            case 2:
                String mpaaRating = con.readMPAARating("Enter the MPAA Rating as \"G\", \"PG\", \"PG-13\", \"R\", \"NC-17\":");
                List<Dvd> arrRating = dvds.getByRating(mpaaRating); //dvdsRack.findDVDsByMPAARating(mpaaRating);
                if (arrRating.size() == 0) {
                    System.out.println("No DVD's found with " + mpaaRating + " rating.");

                } else {
                    for (Dvd d : arrRating) {
                        printDVD(d);
                    }
                }
                break;

            case 3:
                String studio = con.readString("Enter the Production Studio: ");
                List<Dvd> arrStudio = dvds.getByStudio(studio); //dvdsRack.findDVDsByStudio(studio);
                if (arrStudio.size() == 0) {
                    System.out.println("No DVD's produced by " + studio + " were found.");
                } else {
                    for (Dvd d : arrStudio) {
                        printDVD(d);
                    }
                }
                break;

            default:
                System.out.println("Invalid selection.");
                break;

        }
    }

    public void printDVD(Dvd d) {

        System.out.println("=====================================");
        System.out.println("DVD id:             " + d.getId());
        System.out.println("DVD title:          " + d.getTitle());
        System.out.println("DVD release date:   " + d.getReleaseDate());
        System.out.println("DVD MPAA rating:    " + d.getMpaaRating());
        System.out.println("DVD director name:  " + d.getDirector());
        System.out.println("DVD studio          " + d.getStudio());
        System.out.println("DVD user note:      " + d.getNote());
        System.out.println("=====================================");
    }

    public int printEditMenu() {
        con.print("1:Edit title");
        con.print("2:Edit release date");
        con.print("3:Edit MPAA rating");
        con.print("4:Edit director name");
        con.print("5:Edit studio");
        con.print("6:Edit user note");
        con.print("7:Return to main menu");
        return con.readInt("Please choose from the choices above ", 1, 7);
    }

    private void listDVDs() {

        try {
            List<Dvd> arr = dvds.listAll(); //dvdsRack.getAllDVDs();
            for (Dvd d : arr) {
                printDVD(d);
            }
        } catch (NullPointerException e) {
            con.print("No DVD's found in library.");
        }
    }

    private void editTitle(Dvd toEdit) {
        String newTitle = con.readString("Please enter the new title for this DVD.");
        toEdit.setTitle(newTitle);
        con.print("The new title is " + newTitle);
    }

    private void editReleaseDate(Dvd toEdit) {
        String dateStr = con.readString("Enter the new release date in format YYYY-mm-dd");
        LocalDate date = LocalDate.parse(dateStr);
        toEdit.setReleaseDate(date);
        con.print("The new release date is " + dateStr);
    }

    private void editMpaaRating(Dvd toEdit) {
        String newRating = con.readString("Please enter the new MPAA rating for this DVD.");
        toEdit.setMpaaRating(newRating);
        con.print("The new rating is " + newRating);
    }

    private void editDirectorName(Dvd toEdit) {
        String newDirectorName = con.readString("Please enter the new director's name for this DVD.");
        toEdit.setDirector(newDirectorName);
        con.print("The new director name is " + newDirectorName);
    }

    private void editStudio(Dvd toEdit) {
        String newStudioName = con.readString("Please enter the new studio's name for this DVD.");
        toEdit.setStudio(newStudioName);
        con.print("The new studio name is " + newStudioName);
    }

    private void editUserNote(Dvd toEdit) {

        String additionalUserNote = con.readString("Please enter another user note for this DVD.");
        toEdit.setNote(additionalUserNote);
        con.print("The newly added usernote is " + additionalUserNote);
    }
}
