/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import exceptions.DateCollisionException;
import exceptions.ValidEndDateException;
import exceptions.ValidStartDateException;
import interfaces.FileHandler;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieschedule.MovieScheduleTest;

/**
 *
 * @author mark.torres
 */
public class MovieSchedule extends Schedule implements FileHandler {

    int movieId;
    int cinemaId;
    boolean isActive;

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    static String SCHEDULE = "schedule.sc";
    public static String SCHEDMETA = "schedmeta.sm";

    public MovieSchedule(int scheduleId, int movieId, int cinemaId, Date startDate, Date endDate, boolean isActive) {
        super(scheduleId, startDate, endDate);
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.isActive = isActive;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    @Override
    public void save() {
        try {
            checkSchedule();
             try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(MovieSchedule.SCHEDULE, true));
            writer.append(this.toFileString());
            writer.newLine();
            writer.newLine();
            writer.close();
            writer = new BufferedWriter(new FileWriter(MovieSchedule.SCHEDMETA));
            writer.write(Integer.toString(this.getScheduleId()));
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(MovieSchedule.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        } catch (DateCollisionException ex) {
            System.out.println("Date is not available.");
        } catch (ValidEndDateException ex) {
           System.out.println("Date is not available.");
        } catch (ValidStartDateException ex) {
            System.out.println("Date is not available.");
        }
       

    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String toFileString() {
        StringBuilder str = new StringBuilder();
        str.append(super.getScheduleId() + System.getProperty("line.separator"));
        str.append(this.movieId + System.getProperty("line.separator"));
        str.append(this.cinemaId + System.getProperty("line.separator"));
        str.append(super.getStartDate() + System.getProperty("line.separator"));
        str.append(super.getEndDate() + System.getProperty("line.separator"));
        str.append(this.isActive);
        return str.toString();
    }

    public void activate() {
        //dummy storage
        int id=0;
        this.isActive=true;
//        for(MovieSchedule ms:MovieScheduleTest.schedules){
//            if(this.getScheduleId()==ms.getScheduleId()) {
//                ms.setIsActive(true);
//            }
//        }
        
    }

    public void deactivate() {
        this.isActive = false;
        this.save();
    }

    public void update() {

    }

    public void checkSchedule() throws DateCollisionException, ValidEndDateException, ValidStartDateException {
        
        //check if cinema is taken
        int cinema = this.cinemaId;
        for (MovieSchedule nms : MovieScheduleTest.schedules) {
            if (nms.getCinemaId() == cinema && nms.isActive) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("date1 : " + sdf.format(this.getStartDate()));
                System.out.println("date2 : " + sdf.format(nms.getStartDate()));

                if (super.getStartDate().compareTo(nms.getStartDate()) > 0) {
                    System.out.println("Date1 is after Date2");
                     if(super.getEndDate().compareTo(nms.getEndDate())==0){
                        throw new DateCollisionException();
                    }else if(super.getEndDate().compareTo(nms.getEndDate())>0){
                         System.out.println("VALID");
                    }else{throw new DateCollisionException();}
                } else if (super.getStartDate().compareTo(nms.getStartDate()) < 0) {
                    System.out.println("Date1 is before Date2");
                     if(super.getEndDate().compareTo(nms.getEndDate())==0){
                        throw new ValidStartDateException();
                    }else if(super.getEndDate().compareTo(nms.getEndDate())>0){
                        throw new ValidEndDateException();
                    }else{throw new ValidStartDateException();}
                } else if (super.getStartDate().compareTo(nms.getStartDate()) == 0) {
                    System.out.println("Date1 is equal to Date2");
                    if(super.getEndDate().compareTo(nms.getEndDate())==0){
                        throw new DateCollisionException();
                    }else if(super.getEndDate().compareTo(nms.getEndDate())>0){
                        throw new ValidEndDateException();
                    }else{throw new DateCollisionException();}
                } else {
                   
                }
            }else{
               
            }
        }
    }
}
