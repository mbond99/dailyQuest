package com.example.dailyquest;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Quest implements Serializable {

    private String  type;
    private Calendar startTime;
    private int    durationMinutes;
    private String  description;
    private int id;

    public Quest(){
        type        = "";
        startTime   = Calendar.getInstance();
        durationMinutes     = 0;
        description = "";
        id = 0;
    }

    public Quest(String t, Calendar start, int end, String desc, int i){
        type        = t;
        startTime   = start;
        durationMinutes     = end;
        description = desc;
        id = i;
    }


    public void setType(String s){
        type = s;
    }

    public void setSTime(Calendar d){
        startTime = d;
    }

    public void setETime(int d){
        durationMinutes = d;
    }

    public void setDescription(String s){
        description = s;
    }

    public void setId(int i){
        id = i;
    }

    public String getType(){
        return type;
    }

    public Calendar getStartTime(){
        return startTime;
    }

    public int getEndTime() {
        return durationMinutes;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}
