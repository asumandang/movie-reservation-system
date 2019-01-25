/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieschedule;

import exceptions.DateCollisionException;
import exceptions.ValidEndDateException;
import exceptions.ValidStartDateException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.MovieSchedule;

/**
 *
 * @author mark.torres
 */
public class MovieScheduleTest {

    public static ArrayList<MovieSchedule> schedules = new ArrayList<>();
    public static final int CINEMA_MAX = 4;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        initialize();
        //displayAllSchedules();
        displaySchedulesByMovie(2);

        cal.set(2019, Calendar.FEBRUARY, 6);
        cal2.set(2019, Calendar.FEBRUARY, 8);

        MovieSchedule movieSched = new MovieSchedule(getLatestId() + 1, 5, 1, cal.getTime(), cal2.getTime(), false);
        movieSched.save();

    }

    private static void initialize() {
        Calendar cal = Calendar.getInstance();
        cal.set(2019, Calendar.JANUARY, 28);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2019, Calendar.FEBRUARY, 5);
        schedules.add(new MovieSchedule(1, 1, 1, cal.getTime(), cal2.getTime(), false));
        schedules.add(new MovieSchedule(2, 1, 2, cal.getTime(), cal2.getTime(), true));
        schedules.add(new MovieSchedule(3, 2, 3, cal.getTime(), cal2.getTime(), true));
        schedules.add(new MovieSchedule(4, 2, 4, cal.getTime(), cal2.getTime(), true));
    }

    public static void checkSchedule(MovieSchedule ms) {

    }

    public static int getLatestId() {
        String id;
        int ID;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(MovieSchedule.SCHEDMETA));
            id = br.readLine();
            ID = Integer.parseInt(id);
            return ID;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MovieScheduleTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MovieScheduleTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    //@Test
    public static void deleteSchedule(int scheduleId) {
    }

    //Test
    public static void displayAllSchedules() {
        for (int i = 0; i < CINEMA_MAX; i++) {
            System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
            System.out.println("|             CINEMA " + (i + 1) + "             |");
            System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
            for (MovieSchedule ms : schedules) {
                if (i + 1 == ms.getCinemaId()) {
                    System.out.println("|        Movie:      " + ms.getMovieId() + "             |");
                    System.out.println("|        From: " + formatDate(ms.getStartDate()) + "    |");
                    System.out.println("|        To: " + formatDate(ms.getEndDate()) + "     |");
                    if (ms.isIsActive()) {
                        System.out.println("|             NOW SHOWING!            |");
                    } else {
                        System.out.println("|            SHOWING SOON!            |");
                    }
                    System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n");
                }
            }
        }
        //need retrieve all schedules from file

    }
    
    public static void displaySchedulesByMovie(int id) {
        for (int i = 0; i < CINEMA_MAX; i++) {
            System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
            System.out.println("|             CINEMA " + (i + 1) + "             |");
            System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
            for (MovieSchedule ms : schedules) {
                if (i + 1 == ms.getCinemaId() && id==ms.getMovieId()) {
                    System.out.println("|        Movie:      " + ms.getMovieId() + "             |");
                    System.out.println("|        From: " + formatDate(ms.getStartDate()) + "    |");
                    System.out.println("|        To: " + formatDate(ms.getEndDate()) + "     |");
                    if (ms.isIsActive()) {
                        System.out.println("|             NOW SHOWING!            |");
                    } else {
                        System.out.println("|            SHOWING SOON!            |");
                    }
                    System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n");
                }
            }
        }
        //need retrieve all schedules from file

    }

    static String formatDate(Date dt) {
        SimpleDateFormat dt1 = new SimpleDateFormat("MMMMM dd, yyyy");
        return dt1.format(dt);
    }

}
