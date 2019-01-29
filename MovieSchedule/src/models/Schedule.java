/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author mark.torres
 */
public abstract class Schedule {
        private int scheduleId;
	private Date startDate;
	private Date endDate;

    public Schedule(int scheduleId, Date startDate, Date endDate) {
        this.scheduleId = scheduleId;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

  
        
        
        
        
}
