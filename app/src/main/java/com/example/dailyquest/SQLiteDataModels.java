package com.example.dailyquest;

import java.util.ArrayList;

public class SQLiteDataModels {

    //region Player Model

    public static class PlayerModel {
        public String PlayerName;

        public int PlayerLevel;
        public int PlayerPoints;
        public String PlayerType;

        public ArrayList<StatModel> PlayerStats;
    }

    //endregion

    //region Stat Model

    public static class StatModel {
        public int StatId;
        public String StatName;
        public int StatValue;
    }

    public static StatModel CreateStatModel(int id, String name, int value){
        StatModel stat = new StatModel();

        stat.StatId = id;
        stat.StatName = name;
        stat.StatValue = value;

        return stat;
    }

    public static StatModel CreateStatModel(String name, int value){
        StatModel stat = new StatModel();

        stat.StatName = name;
        stat.StatValue = value;

        return stat;
    }

    //endregion

    //region Quest Model

    public static class QuestModel {
        public int QuestId;
        public String QuestDesc;
        public int QuestType;
        public int QuestStartTime;
        public int QuestEndTime;
    }

    //endregion
}
