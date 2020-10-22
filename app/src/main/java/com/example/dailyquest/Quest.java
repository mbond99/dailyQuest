package com.example.dailyquest;

import java.util.Date;

public class Quest {

    private String  type;
    private Date    startTime;
    private Date    endTime;
    private String  description;

    public Quest(){
        type        = "";
        startTime   = new Date();
        endTime     = new Date();
        description = "";
    }

    public Quest(String t, Date start, Date end, String desc){
        type        = t;
        startTime   = start;
        endTime     = end;
        description = desc;
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

    public void setSTime(Date d){
        startTime = d;
    }

    public void setETime(Date d){
        endTime = d;
    }

    public void setDescription(String s){
        description = s;
    }

    public String getType(){
        return type;
    }

    public Date getStartTime(){
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }
}
