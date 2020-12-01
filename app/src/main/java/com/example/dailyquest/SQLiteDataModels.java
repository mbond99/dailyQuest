package com.example.dailyquest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    // Translation function to and from quest models and quest objects
    public static QuestModel CreateQuestModelFromQuestObject(Quest questObject){
        QuestModel questModel = new QuestModel();

        questModel.QuestType = questObject.getType();
        questModel.QuestDesc = questObject.getDescription();
        questModel.QuestDuration = questObject.getEndTime();
        DateFormat dateAsString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        questModel.QuestDate = dateAsString.format(questObject.getStartTime().getTime());
        questModel.QuestId = questObject.getId();

        return questModel;
    }

    public static Quest CreateQuestObjectFromQuestModel(QuestModel questModel){
        Quest questObject = new Quest();

        questObject.setType(questModel.QuestType);
        questObject.setDescription(questModel.QuestDesc);
        questObject.setETime(questModel.QuestDuration);

        try {
            Calendar start = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            Date date = format.parse(questModel.QuestDate);
            start.setTime(date);
            questObject.setSTime(start);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        questObject.setId(questModel.QuestId);

        return questObject;
    }

    //endregion

    //region Quest Model

    public static class QuestModel {
        public int QuestId;
        public String QuestDesc;
        public String QuestType;
        public int QuestDuration;
        public String QuestDate;
    }

    //endregion
}
