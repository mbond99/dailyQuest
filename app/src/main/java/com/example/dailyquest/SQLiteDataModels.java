package com.example.dailyquest;

import java.util.ArrayList;

public class SQLiteDataModels {

    public static class PlayerModel {
        public String PlayerName;
        public int PlayerLevel;
        public int PlayerPoints;
        public int PlayerType;
        public ArrayList<StatModel> PlayerStats;
    }

    public static class StatModel {
        public int StatId;
        public String StatName;
        public int StatValue;
    }

    public static class QuestModel {
        public int QuestId;
        public String QuestDesc;
        public int QuestType;
        public int QuestStartTime;
        public int QuestEndTime;
    }
}
