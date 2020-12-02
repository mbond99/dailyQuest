package com.example.dailyquest;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestCalendar implements Serializable {

    private ArrayList<Quest> questList;

    public QuestCalendar(){
        questList = new ArrayList<>();
    }

    public void addQuest(Quest q){
        questList.add(q);
    }

    public ArrayList<Quest> getAllQuests(){
        return questList;
    }



}
