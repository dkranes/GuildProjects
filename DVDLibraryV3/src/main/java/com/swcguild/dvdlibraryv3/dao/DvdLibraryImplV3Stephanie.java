/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibraryv3.dao;

import com.swcguild.dvdlibrary.dao.DvdLibraryDao;
import com.swcguild.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DvdLibraryImplV3Stephanie implements DvdLibraryDao {

    public static int idGenerator = 10;
    private final HashMap<Integer, Dvd> dvds = new HashMap<>();

    public DvdLibraryImplV3Stephanie() {
       // loadDVDLibrary();
    }

    private void loadDVDLibrary() {

        Dvd madmax;
        Dvd testtitle;
        Dvd madmax2;
        Dvd dcrystal;
        Dvd anotherMiller;
        
        madmax = new Dvd();
        madmax.setTitle("Mad Max: Fury Road");
        // madmax.setId(0);
        madmax.setDirector("George Miller");
        madmax.setMpaaRating("R");
        madmax.setReleaseDate(LocalDate.parse("2005-05-10"));
        madmax.setStudio("Not Sure");
        madmax.setNote("Intense thrill ride!");

        testtitle = new Dvd();
        testtitle.setTitle("TestTitle");
        // testtitle.setId(1);
        testtitle.setDirector("testDir");
        testtitle.setMpaaRating("G");
        testtitle.setReleaseDate(LocalDate.parse("2010-06-15"));
        testtitle.setStudio("blah");
        testtitle.setNote("okay");

        madmax2 = new Dvd();
        madmax2.setTitle("Mad Max Movie 2");
        //  madmax2.setId(2);
        madmax2.setDirector("George Miller");
        madmax2.setMpaaRating("PG");
        madmax2.setReleaseDate(LocalDate.parse("2014-05-15"));
        madmax2.setStudio("Not Sure");
        madmax2.setNote("Intense fun!");

        dcrystal = new Dvd();
        dcrystal.setTitle("The Dark Crystal");
        //  dcrystal.setId(3);
        dcrystal.setDirector("Jim Henson");
        dcrystal.setMpaaRating("PG");
        dcrystal.setReleaseDate(LocalDate.parse("1982-06-15"));
        dcrystal.setStudio("blah");
        dcrystal.setNote("Awesome!");

        anotherMiller = new Dvd();
        anotherMiller.setTitle("Some Miller Movie");
        // anotherMiller.setId(4);
        anotherMiller.setDirector("George Miller");
        anotherMiller.setMpaaRating("PG");
        anotherMiller.setReleaseDate(LocalDate.parse("2009-11-30"));
        anotherMiller.setStudio("aStudio");
        anotherMiller.setNote("another Comment!");
        
        add(madmax);
        add(testtitle);
        add(madmax2);
        add(dcrystal);
        add(anotherMiller);
    }

    public List<Dvd> listAll() {
        if (dvds.values().isEmpty()) {
            return null;
        }
        return new ArrayList<>(dvds.values());
    }

    @Override
    public void add(Dvd d) {
        d.setId(++idGenerator);
        dvds.put(d.getId(), d);
        // return d;
    }

    @Override
    public void remove(int id) {
        dvds.remove(id);
    }

    @Override
    public Dvd getById(int id) {
        return dvds.get(id);
    }

    @Override
    public List<Dvd> getByTitle(String title) {
        String lTitle = title.toLowerCase();
        List<Dvd> withTitle = dvds.values()
                .stream()
                .filter(d -> d.getTitle().toLowerCase().startsWith(lTitle))
                .collect(Collectors.toList());
        if (withTitle.isEmpty()) {
            return null;
        }
        return withTitle;
    }

    @Override
    public List<Dvd> getByRating(String mpaa) {
        String lName = mpaa.toLowerCase();
        List<Dvd> withMPAA = dvds.values()
                .stream()
                .filter(d -> d.getMpaaRating().toLowerCase().contains(lName))
                .collect(Collectors.toList());
        if (withMPAA.isEmpty()) {
            return null;
        }
        return withMPAA;
    }

    @Override
    public List<Dvd> getByStudio(String studio) {
        String lName = studio.toLowerCase();
        List<Dvd> byStudio = dvds.values()
                .stream()
                .filter(d -> d.getStudio().toLowerCase().contains(lName))
                .collect(Collectors.toList());
        if (byStudio.isEmpty()) {
            return null;
        }
        return byStudio;

    }

        //only for reading from file
//    private Dvd addDVD(Dvd d) {
//        dvds.put(d.getId(), d);
//        return d;
//    }
    //    public void loadDVDLibrary() throws ParseException, FileNotFoundException {
//        Scanner scan = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
//        String currentLine;
//        String[] dvdValues;
//        if (scan.hasNextLine()) {
//            currentLine = scan.nextLine().trim();
//            idGenerator = Integer.parseInt(currentLine);
//        }
//        while (scan.hasNextLine()) {
//            currentLine = scan.nextLine();
//            dvdValues = currentLine.split(DELIMITER);
//            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
//            Dvd d = new Dvd();
//            //(Integer.parseInt(dvdValues[0]), dvdValues[1], LocalDate.parse(dvdValues[2]), dvdValues[3], dvdValues[4], dvdValues[5], null);
//            d.setNote(dvdValues[6]);
//            addDVD(d);
//        }
//
//        scan.close();
//
//    }
//
//    public void writeToDVDLibrary() throws IOException {
//        PrintWriter out = new PrintWriter(new FileWriter(DVD_FILE));
//        List<Dvd> arr = listAll();
//        out.print(idGenerator);
//        for (Dvd currentDVD : arr) {
//            out.print("\n" + currentDVD.getId() + DELIMITER + currentDVD.getTitle() + DELIMITER + currentDVD.getReleaseDate()
//                    + DELIMITER + currentDVD.getMpaaRating() + DELIMITER + currentDVD.getDirector()
//                    + DELIMITER + currentDVD.getStudio() + DELIMITER + currentDVD.getNote());
//            out.flush();
//        }
//        out.close();
//    }
}
