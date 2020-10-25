package com.example.dailyquest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteConnection extends SQLiteOpenHelper {
    private static final String DatabaseFile = "DailyQuestDatabase.db";

    // region Table and Column name declarations

    // Table name declarations
    private static final String TABLE_PLAYER = "PLAYER";
    private static final String TABLE_STAT = "STAT";
    private static final String TABLE_QUEST = "QUEST";

    // Player table column name declarations
    private static final String PLAYER_ID = "p_id";
    private static final String PLAYER_NAME = "p_name";
    private static final String PLAYER_LEVEL = "p_level";
    private static final String PLAYER_CLASS = "p_class";
    private static final String PLAYER_POINTS = "p_points";

    // Stat table column name declarations
    private static final String STAT_ID = "s_id";
    private static final String STAT_NAME = "s_name";
    private static final String STAT_VALUE = "s_value";

    // Quest table column name declarations
    private static final String QUEST_ID = "q_id";
    private static final String QUEST_DESC = "q_desc";
    private static final String QUEST_TYPE = "q_type";
    private static final String QUEST_START_TIME = "q_start_time";
    private static final String QUEST_END_TIME = "q_end_time";

    //endregion

    //region Database Creation Queries

    private static final String QUERY_CREATE_PLAYER_TABLE = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY, " +
                    "%s TEXT NOT NULL, " +
                    "%s INTEGER NOT NULL, " +
                    "%s INTEGER NOT NULL, "+
                    "%s INTEGER NOT NULL);",
            TABLE_PLAYER, PLAYER_ID, PLAYER_NAME, PLAYER_LEVEL, PLAYER_POINTS, PLAYER_CLASS);

    private static final String QUERY_CREATE_STAT_TABLE = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY, " +
                    "%s TEXT NOT NULL, " +
                    "%s INTEGER NOT NULL );",
             TABLE_STAT, STAT_ID, STAT_NAME, STAT_VALUE);

    private static final String QUERY_CREATE_QUEST_TABLE = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY, " +
                    "%s TEXT NOT NULL, " +
                    "%s INTEGER NOT NULL, " +
                    "%s INTEGER NOT NULL, "+
                    "%s INTEGER NOT NULL);",
            TABLE_QUEST, QUEST_ID, QUEST_DESC, QUEST_TYPE, QUEST_START_TIME, QUEST_END_TIME);

    private static final String QUERY_DELETE_TABLE = "DROP TABLE IF EXISTS %s;";
    //endregion

    //region Database Selection Queries

    private static String QUERY_SELECT_ALL_STATS = String.format(
            "SELECT * FROM %s",
            TABLE_STAT
    );

    private static String QUERY_SELECT_ALL_QUESTS = String.format(
            "SELECT * FROM %s",
            TABLE_QUEST
    );

    //endregion

    public SQLiteConnection(Context context) {
        /** The SQLiteConnection constructor **/
        super(context, DatabaseFile, null, 1);
    }

    //region Overrides

    @Override
    public void onCreate(SQLiteDatabase db){
        /** Overrides the onCreate method so that the application can create all required
         * database tables and other database info if they do not already exist **/

        // Create the tables
        db.execSQL(QUERY_CREATE_PLAYER_TABLE);
        db.execSQL(QUERY_CREATE_STAT_TABLE);
        db.execSQL(QUERY_CREATE_QUEST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        /** Overrides the onUpgrade method so that if the database needs upgraded all tables
         * will be properly updated. **/

        // Delete all tables
        db.execSQL(String.format(QUERY_DELETE_TABLE, TABLE_PLAYER));
        db.execSQL(String.format(QUERY_DELETE_TABLE, TABLE_STAT));
        db.execSQL(String.format(QUERY_DELETE_TABLE, TABLE_QUEST));

        // Recreate the database
        onCreate(db);
    }

    //endregion

    //region Data Inserters

    public boolean insertQuest(String questDesc, int questType, int questStartTime, int questEndTime){
        /** Inserts a quest with the passed parameters. Returns true if successful
         * and false if the insertion failed. **/

        // Grab the database
        SQLiteDatabase db = this.getWritableDatabase();

        // Set the values to insert into the QUEST table
        ContentValues newQuest = new ContentValues();
        newQuest.put(QUEST_DESC, questDesc);
        newQuest.put(QUEST_TYPE, questType);
        newQuest.put(QUEST_START_TIME, questStartTime);
        newQuest.put(QUEST_END_TIME, questEndTime);

        // Insert the quest and get results
        long result = db.insert(TABLE_QUEST, null, newQuest);

        // If result is greater than 0 then the quest was properly inserted, otherwise return false
        if (result > 0){
            return true;
        }
        return false;
    }

    //endregion

    //region Data Updaters

    public boolean updatePlayer(String playerName, int playerLevel, int playerPoints, int playerClass){
        // TODO
        return false;
    }

    public boolean updatePlayerLevel(int playerLevel, int playerPoints){
        // TODO
        return false;
    }

    public boolean updateStat(String statName, int statVal){
        // TODO
        return false;
    }

    public boolean updateQuest(int questId, String questDesc, int questType, int questStartTime, int questEndTime){
        // TODO
        return false;
    }

    //endregion

    //region Data Getters

    public ArrayList<SQLiteDataModels.StatModel> getAllStats(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<SQLiteDataModels.StatModel> stats = new ArrayList<SQLiteDataModels.StatModel>();

        Cursor nextStat = db.rawQuery(QUERY_SELECT_ALL_STATS, null);
        nextStat.moveToFirst();

        while (nextStat.isAfterLast() == false){
            SQLiteDataModels.StatModel stat = new SQLiteDataModels.StatModel();

            stat.StatId = nextStat.getInt(nextStat.getColumnIndex(STAT_ID));
            stat.StatName = nextStat.getString(nextStat.getColumnIndex(STAT_NAME));
            stat.StatValue = nextStat.getInt(nextStat.getColumnIndex(STAT_VALUE));

            nextStat.moveToNext();
        }

        return stats;
    }

    public SQLiteDataModels.StatModel getStatByName(String statName){
        SQLiteDataModels.StatModel stat = new SQLiteDataModels.StatModel();

        // TODO

        return stat;
    }

    public ArrayList<SQLiteDataModels.QuestModel> getAllQuests(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<SQLiteDataModels.QuestModel> quests = new ArrayList<SQLiteDataModels.QuestModel>();

        Cursor nextQuest = db.rawQuery(QUERY_SELECT_ALL_QUESTS, null);
        nextQuest.moveToFirst();

        while (nextQuest.isAfterLast() == false){
            SQLiteDataModels.QuestModel quest = new SQLiteDataModels.QuestModel();

            quest.QuestId = nextQuest.getInt(nextQuest.getColumnIndex(QUEST_ID));
            quest.QuestDesc = nextQuest.getString(nextQuest.getColumnIndex(QUEST_DESC));
            quest.QuestType = nextQuest.getInt(nextQuest.getColumnIndex(QUEST_TYPE));
            quest.QuestStartTime = nextQuest.getInt(nextQuest.getColumnIndex(QUEST_START_TIME));
            quest.QuestEndTime = nextQuest.getInt(nextQuest.getColumnIndex(QUEST_END_TIME));

            nextQuest.moveToNext();
        }

        return quests;
    }

    public SQLiteDataModels.QuestModel getQuestById(int questId){
        SQLiteDataModels.QuestModel quest = new SQLiteDataModels.QuestModel();

        // TODO

        return quest;
    }

    //endregion
}
