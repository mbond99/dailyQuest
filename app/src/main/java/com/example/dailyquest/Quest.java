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

    /*
    WORK IN PROGRESS. NEED GUI IN ORDER TO SETUP TIMER
     */
    public void startQuest(){

    }

    /*
    WIP. NEED CHARACTER CLASS AND DATABASE CONNECTION TO UPDATE CHAR STATS
     */
    public int[] finishQuest(int[] initialStats){
        int[] updatedStats = new int[initialStats.length];
        return updatedStats;
    }

    /*
    WIP NEED GUI AND TIMER
     */
    private int getTotalTime(Date start, Date end){
        int totalTime = 0;

        return totalTime;
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
