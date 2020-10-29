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

    private static final String QUERY_SELECT_WHERE = "SELECT * FROM %s WHERE %s = %s";

    private static final String QUERY_SELECT_ALL_STATS = String.format(
            "SELECT * FROM %s",
            TABLE_STAT
    );

    private static final String QUERY_SELECT_ALL_QUESTS = String.format(
            "SELECT * FROM %s",
            TABLE_QUEST
    );

    //endregion

    /** The SQLiteConnection constructor **/
    public SQLiteConnection(Context context) {
        super(context, DatabaseFile, null, 1);
    }

    //region Public Methods

    /** Drops all tables and then recreates them, effectively wiping the local db for future use **/
    public void wipeDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete all tables
        db.execSQL(String.format(QUERY_DELETE_TABLE, TABLE_PLAYER));
        db.execSQL(String.format(QUERY_DELETE_TABLE, TABLE_STAT));
        db.execSQL(String.format(QUERY_DELETE_TABLE, TABLE_QUEST));

        // Recreate the database
        onCreate(db);
    }

    //endregion

    //region Overrides

    /** Overrides the onCreate method so that the application can create all required
     * database tables and other database info if they do not already exist **/
    @Override
    public void onCreate(SQLiteDatabase db){
        // Create the tables
        db.execSQL(QUERY_CREATE_PLAYER_TABLE);
        db.execSQL(QUERY_CREATE_STAT_TABLE);
        db.execSQL(QUERY_CREATE_QUEST_TABLE);

        // TODO Insert initial player row

        // Insert required stat rows
        ArrayList<SQLiteDataModels.StatModel> stats = new ArrayList<SQLiteDataModels.StatModel>();
        stats.add(SQLiteDataModels.CreateStatModel("strength",0));
        stats.add(SQLiteDataModels.CreateStatModel("intelligence",0));
        stats.add(SQLiteDataModels.CreateStatModel("dexterity",0));
        stats.add(SQLiteDataModels.CreateStatModel("constitution",0));

        for (SQLiteDataModels.StatModel stat : stats){
            ContentValues row = new ContentValues();
            row.put(STAT_NAME, stat.StatName);
            row.put(STAT_VALUE, stat.StatValue);
            db.insert(TABLE_STAT, null, row);
        }
    }

    /** Overrides the onUpgrade method so that if the database needs upgraded all tables
     * will be properly wiped. **/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        wipeDatabase();
    }

    //endregion

    //region Data Insertion

    /** Inserts a quest with the passed parameters. Returns true if successful
     * and false if the insertion failed. **/
    public boolean insertQuest(String questDesc, int questType, int questStartTime, int questEndTime){
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
        return result > 0;
    }

    //endregion

    //region Data Deletion

    public boolean deleteQuestById(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(TABLE_QUEST, String.format("%s = ?", QUEST_ID),
                new String[] {Integer.toString(id)});

        return result > 0;
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

        while (!nextStat.isAfterLast()){
            SQLiteDataModels.StatModel stat = new SQLiteDataModels.StatModel();

            stat.StatId = nextStat.getInt(nextStat.getColumnIndex(STAT_ID));
            stat.StatName = nextStat.getString(nextStat.getColumnIndex(STAT_NAME));
            stat.StatValue = nextStat.getInt(nextStat.getColumnIndex(STAT_VALUE));

            stats.add(stat);

            nextStat.moveToNext();
        }
        nextStat.close();

        return stats;
    }

    public SQLiteDataModels.StatModel getStatByName(String statName){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDataModels.StatModel stat = new SQLiteDataModels.StatModel();

        String query = String.format("SELECT * FROM %s WHERE %s = %s",
                TABLE_STAT, STAT_NAME, statName);
        Cursor dbEntry = db.rawQuery(query, null);
        if (dbEntry.getCount() > 0) {
            dbEntry.moveToFirst();

            stat.StatId = dbEntry.getInt(dbEntry.getColumnIndex(STAT_ID));
            stat.StatName = dbEntry.getString(dbEntry.getColumnIndex(STAT_NAME));
            stat.StatValue = dbEntry.getInt(dbEntry.getColumnIndex(STAT_VALUE));

            dbEntry.close();
        }

        return stat;
    }

    public ArrayList<SQLiteDataModels.QuestModel> getAllQuests(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<SQLiteDataModels.QuestModel> quests = new ArrayList<SQLiteDataModels.QuestModel>();

        Cursor nextQuest = db.rawQuery(QUERY_SELECT_ALL_QUESTS, null);
        nextQuest.moveToFirst();

        while (!nextQuest.isAfterLast()){
            SQLiteDataModels.QuestModel quest = new SQLiteDataModels.QuestModel();

            quest.QuestId = nextQuest.getInt(nextQuest.getColumnIndex(QUEST_ID));
            quest.QuestDesc = nextQuest.getString(nextQuest.getColumnIndex(QUEST_DESC));
            quest.QuestType = nextQuest.getInt(nextQuest.getColumnIndex(QUEST_TYPE));
            quest.QuestStartTime = nextQuest.getInt(nextQuest.getColumnIndex(QUEST_START_TIME));
            quest.QuestEndTime = nextQuest.getInt(nextQuest.getColumnIndex(QUEST_END_TIME));

            quests.add(quest);

            nextQuest.moveToNext();
        }
        nextQuest.close();

        return quests;
    }

    public SQLiteDataModels.QuestModel getQuestById(int questId){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDataModels.QuestModel quest = new SQLiteDataModels.QuestModel();

        String query = String.format(QUERY_SELECT_WHERE, TABLE_QUEST, QUEST_ID, questId);
        Cursor dbEntry = db.rawQuery(query, null);
        if (dbEntry.getCount() > 0) {
            dbEntry.moveToFirst();

            quest.QuestId = dbEntry.getInt(dbEntry.getColumnIndex(QUEST_ID));
            quest.QuestDesc = dbEntry.getString(dbEntry.getColumnIndex(QUEST_DESC));
            quest.QuestType = dbEntry.getInt(dbEntry.getColumnIndex(QUEST_TYPE));
            quest.QuestStartTime = dbEntry.getInt(dbEntry.getColumnIndex(QUEST_START_TIME));
            quest.QuestEndTime = dbEntry.getInt(dbEntry.getColumnIndex(QUEST_END_TIME));

            dbEntry.close();
        }

        return quest;
    }

    //endregion
}
