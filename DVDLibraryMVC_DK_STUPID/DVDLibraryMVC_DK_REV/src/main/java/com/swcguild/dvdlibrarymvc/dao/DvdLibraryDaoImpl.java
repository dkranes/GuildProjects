/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.dao;

import com.swcguild.dvdlibrarymvc.model.Dvd;
import com.swcguild.dvdlibrarymvc.model.SearchTerm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DvdLibraryDaoImpl implements DvdLibraryDao {

    private final HashMap<Integer, Dvd> dvdMap = new HashMap<>();
    public static final String DELIMITER = "::";
    public static final String NOTEDELIMITER = ",";
    public static final String DVD_FILE = "DVDs.txt";
    private static Integer idGenerator = 0;

//    @Override
//    public void loadDVDLibrary() throws ParseException, FileNotFoundException {
//        Scanner sc = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
//        String currentLine;
//        String[] dvdValues;
//
//        while (sc.hasNextLine()) {
//            ArrayList<String> userNotes = new ArrayList<String>();
//            currentLine = sc.nextLine();
//            dvdValues = currentLine.split(DELIMITER);
//            String tempNote = dvdValues[5];
//
//            String[] tempArray = tempNote.split(NOTEDELIMITER);
//
//            for (int i = 0; i < tempArray.length; i++) {
//                userNotes.add(tempArray[i]
//                        .replace("[", "")
//                        .replace("]", "")
//                        .trim());
//            }
//
//            Dvd d = new Dvd();
//
//            d.setTitle(dvdValues[0]);
//            d.setReleaseDate(LocalDate.parse(dvdValues[1]));
//            d.setMpaaRating(dvdValues[2]);
//            d.setDirectorName(dvdValues[3]);
//            d.setStudio(dvdValues[4]);
//            d.setUserNote(userNotes);
//
//            //Dvd d = new Dvd(dvdValues[0], LocalDate.parse(dvdValues[1]), dvdValues[2], dvdValues[3], dvdValues[4], userNotes);
//            addDVD(d);
//        }
//        sc.close();
//
//    }
//
//    @Override
//    public void writeToDVDLibrary() throws IOException {
//
//        PrintWriter out = new PrintWriter(new FileWriter(DVD_FILE));
//        Dvd[] dvdArray = getAllDVDs();
//
//        for (Dvd currentDVD : dvdArray) {
//            out.println(currentDVD.getTitle() + DELIMITER + currentDVD.getReleaseDate()
//                    + DELIMITER + currentDVD.getMpaaRating() + DELIMITER + currentDVD.getDirectorName()
//                    + DELIMITER + currentDVD.getStudio() + DELIMITER + currentDVD.getUserNote().toString());
//            out.flush();
//        }
//        out.close();
//    }
    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {

        String titleCriteria = criteria.get(SearchTerm.TITLE);
        String releaseDateCriteria = criteria.get(SearchTerm.RELEASE_DATE);
        String mpaaRatingCriteria = criteria.get(SearchTerm.MPAA_RATING);
        String directorNameCriteria = criteria.get(SearchTerm.DIRECTOR_NAME);
        String studioCriteria = criteria.get(SearchTerm.STUDIO);
        String noteCriteria = criteria.get(SearchTerm.NOTE);

        Predicate<Dvd> titleMatches;
        Predicate<Dvd> releaseDateMatches;
        Predicate<Dvd> mpaaRatingMatches;
        Predicate<Dvd> directorNameDateMatches;
        Predicate<Dvd> studioDateMatches;
        Predicate<Dvd> noteMatches;

        Predicate<Dvd> truePredicate = (c) -> {
            return true;
        };

        titleMatches = (titleCriteria == null) || (titleCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getTitle().equalsIgnoreCase(titleCriteria);

        releaseDateMatches = (releaseDateCriteria == null) || (releaseDateCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getReleaseDate().equals(releaseDateCriteria);

        mpaaRatingMatches = (mpaaRatingCriteria == null) || (mpaaRatingCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getMpaaRating().equalsIgnoreCase(mpaaRatingCriteria);

        directorNameDateMatches = (directorNameCriteria == null) || (directorNameCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getDirectorName().equalsIgnoreCase(directorNameCriteria);

        studioDateMatches = (studioCriteria == null) || (studioCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getStudio().equalsIgnoreCase(studioCriteria);

        noteMatches = (noteCriteria == null) || (noteCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getUserNote().equals(noteCriteria);

        return dvdMap.values().stream()
                .filter(titleMatches
                        .and(releaseDateMatches)
                        .and(mpaaRatingMatches)
                        .and(directorNameDateMatches)
                        .and(studioDateMatches)
                        .and(noteMatches))
                .collect((Collectors.toList()));
    }

    @Override
    public List<Dvd> getAllDVDs() {

        if (dvdMap.keySet().size() == 0) {
            return null;
        }
        Collection<Dvd> d = dvdMap.values();
        return new ArrayList(d);

    }

    @Override
    public Integer[] getAllIDs() {
        return dvdMap.keySet().toArray(new Integer[dvdMap.keySet().size()]);
    }

    @Override
    public Dvd addDVD(Dvd dvd) {
        dvd.setDvdId(idGenerator);
        dvdMap.put(dvd.getDvdId(), dvd);
        idGenerator++;

        return dvd;
    }

    @Override
    public Dvd findDVDByID(Integer id) {
        return dvdMap.get(id);
    }


    @Override
    public void updateDVD(Dvd dvd
    ) {
        dvdMap.put(dvd.getDvdId(), dvd);
    }

    @Override
    public Dvd removeDVD(Integer id
    ) {
        return dvdMap.remove(id);
    }

//
//    @Override
//    public List<Dvd> findDVDsByTitle(String title) {
//        return dvdMap.values()
//                .stream()
//                .filter(t -> t.getTitle().equalsIgnoreCase(title))
//                //.forEach(t -> matches.add(t));
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Map<String, List<Dvd>> findDVDsByDirector(String director) {
//        return dvdMap.values()
//                .stream()
//                .filter(f -> f.getDirectorName().equalsIgnoreCase(director))
//                .collect(Collectors.groupingBy(Dvd::getMpaaRating));
//    }
//    
//
//    @Override
//    public List<Dvd> findDVDsByMPAARating(String rating) {
//        return dvdMap.values()
//                .stream()
//                .filter(r -> r.getMpaaRating().equalsIgnoreCase(rating))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Dvd> findDVDsByStudio(String studio) {
//        return dvdMap.values()
//                .stream()
//                .filter(s -> s.getStudio().equalsIgnoreCase(studio))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Dvd> findDVDsByNumYears(Integer years) {
//        return dvdMap.values()
//                .stream()
//                .filter(y -> (LocalDate.now().getYear()- y.getReleaseDate().getYear()) <= years)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public double displayAvgAgeOfLibrary() {
//        return dvdMap.values()
//                .stream()
//                .mapToInt(s -> (LocalDate.now().getYear() - s.getReleaseDate().getYear()))
//                .average()
//                .getAsDouble();
//
//    }
//
//    @Override
//    public double displayAvgNumNotes() {
//
//        return dvdMap.values()
//                .stream()
//                .mapToInt(n -> n.getUserNote().size())
//                .average()
//                .getAsDouble();
//
//    }
//
//    @Override
//    public Dvd findMostRecentDVD() {
//
//        Comparator<Dvd> comparator = new Comparator<Dvd>() {
//
//            @Override
//            public int compare(Dvd dvd1, Dvd dvd2) {
//                return ((dvd2.getReleaseDate().toString()).compareTo((dvd1.getReleaseDate().toString())));
//            }
//        };
//
//        Dvd mostRecent;
//
//        List<Dvd> mostRecentDVDs = dvdMap.values()
//                .stream()
//                .collect(Collectors.toList());
//
//        Collections.sort(mostRecentDVDs, comparator);
//
//        return mostRecentDVDs.get(0);
//
//
//    }
//
//    @Override
//    public Dvd findOldestDVD() {
//
//        Comparator<Dvd> comparator = new Comparator<Dvd>() {
//
//            @Override
//            public int compare(Dvd dvd1, Dvd dvd2) {
//                return ((dvd1.getReleaseDate().toString()).compareTo((dvd2.getReleaseDate().toString())));
//            }
//        };
//
//        Dvd mostRecent;
//
//        List<Dvd> mostRecentDVDs = dvdMap.values()
//                .stream()
//                .collect(Collectors.toList());
//
//        Collections.sort(mostRecentDVDs, comparator);
//
//        return mostRecentDVDs.get(0);
//
//
//    }
//
//    @Override
//    public int librarySize() {
//        return dvdMap.keySet().size();
//    }
}
