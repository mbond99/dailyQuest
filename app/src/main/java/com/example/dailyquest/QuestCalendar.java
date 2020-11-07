package com.example.dailyquest;

import java.util.ArrayList;

public class QuestCalendar {

    private ArrayList<Quest> questList;

    public QuestCalendar(){
        questList = new ArrayList<>();
    }

    public QuestCalendar(ArrayList<Quest> quests){
        questList = quests;
    }

    public void addQuest(Quest q){
        questList.add(q);
    }

    public void remQuest(Quest q){
        questList.remove(q);
    }

    public Quest getQuest(int i){
        return questList.get(i);
    }

    public ArrayList<Quest> getAllQuests(){
        return questList;
    }

    public void startQuest(Quest q){
        q.startQuest();
    }


}
