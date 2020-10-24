package com.example.dailyquest;

public class SQLiteDataModels {

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
