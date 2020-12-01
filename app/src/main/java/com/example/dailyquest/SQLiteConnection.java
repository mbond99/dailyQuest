package com.example.dailyquest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteConnection extends SQLiteOpenHelper {
    private static final String DatabaseFile = "DailyQuestDatabase.db";
    private boolean FirstTimeStartUp = false;

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
    private static final String QUEST_DURATION = "q_duration";
    private static final String QUEST_DATE = "q_date";

    //endregion

    //region Database Creation Queries

    private static final String QUERY_CREATE_PLAYER_TABLE = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY, " +
                    "%s TEXT NOT NULL, " +
                    "%s INTEGER NOT NULL, " +
                    "%s INTEGER NOT NULL, "+
                    "%s TEXT NOT NULL);",
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
                    "%s TEXT NOT NULL, " +
                    "%s INTEGER NOT NULL, "+
                    "%s TEXT NOT NULL);",
            TABLE_QUEST, QUEST_ID, QUEST_DESC, QUEST_TYPE, QUEST_DURATION, QUEST_DATE);

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

    public boolean isFirstTimeStartUp(){
        return FirstTimeStartUp;
    }

    //endregion

    //region Overrides

    /** Overrides the onCreate method so that the application can create all required
     * database tables and other database info if they do not already exist **/
    @Override
    public void onCreate(SQLiteDatabase db){
        FirstTimeStartUp = true;

        // Create the tables
        db.execSQL(QUERY_CREATE_PLAYER_TABLE);
        db.execSQL(QUERY_CREATE_STAT_TABLE);
        db.execSQL(QUERY_CREATE_QUEST_TABLE);

        ContentValues val = new ContentValues();
        val.put(PLAYER_NAME, "Player");
        val.put(PLAYER_LEVEL, 1);
        val.put(PLAYER_POINTS, 0);
        val.put(PLAYER_CLASS, "mage");
        db.insert(TABLE_PLAYER, null, val);

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
    public boolean insertQuest(String questDesc, String questType, int questDuration, String questDate){
        // Grab the database
        SQLiteDatabase db = this.getWritableDatabase();

        // Set the values to insert into the QUEST table
        ContentValues newQuest = new ContentValues();
        newQuest.put(QUEST_DESC, questDesc);
        newQuest.put(QUEST_TYPE, questType);
        newQuest.put(QUEST_DURATION, questDuration);
        newQuest.put(QUEST_DATE, questDate);

        // Insert the quest and get results
        long result = db.insert(TABLE_QUEST, null, newQuest);

        // If result is greater than 0 then the quest was properly inserted, otherwise return false
        return result > 0;
    }

    public boolean insertQuest(SQLiteDataModels.QuestModel quest){
        // Grab the database
        SQLiteDatabase db = this.getWritableDatabase();

        // Set the values to insert into the QUEST table
        ContentValues newQuest = new ContentValues();
        newQuest.put(QUEST_DESC, quest.QuestDesc);
        newQuest.put(QUEST_TYPE, quest.QuestType);
        newQuest.put(QUEST_DURATION, quest.QuestDuration);
        newQuest.put(QUEST_DATE, quest.QuestDate);

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

    public boolean updatePlayer(String playerName, int playerLevel, int playerPoints, String playerClass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(PLAYER_NAME, playerName);
        val.put(PLAYER_LEVEL, playerLevel);
        val.put(PLAYER_POINTS, playerPoints);
        val.put(PLAYER_CLASS, playerClass);

        int result = db.update(TABLE_PLAYER, val, String.format("%s = ?", PLAYER_ID),
                new String[] { String.valueOf(1) });

        return result > 0;
    }

    public boolean updatePlayerLevel(int playerLevel, int playerPoints){
        // TODO
        return false;
    }

    public boolean updateStat(String statName, int statVal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues stat = new ContentValues();

        stat.put(STAT_NAME, statName);
        stat.put(STAT_VALUE, statVal);

        int result = db.update(TABLE_STAT, stat, String.format("%s = ?", STAT_NAME),
                new String[] { statName });

        return result > 0;
    }

    public boolean updateQuest(int questId, String questDesc, String questType, int questDuration, String questDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues quest = new ContentValues();

        quest.put(QUEST_DESC, questDesc);
        quest.put(QUEST_TYPE, questType);
        quest.put(QUEST_DURATION, questDuration);
        quest.put(QUEST_DATE, questDate);

        int result = db.update(TABLE_QUEST, quest, String.format("%s = ?", QUEST_ID),
                new String[] { Integer.toString(questId) });

        return result > 0;
    }

    public boolean updateQuest(SQLiteDataModels.QuestModel quest){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dbquest = new ContentValues();

        dbquest.put(QUEST_DESC, quest.QuestDesc);
        dbquest.put(QUEST_TYPE, quest.QuestType);
        dbquest.put(QUEST_DURATION, quest.QuestDuration);
        dbquest.put(QUEST_DATE, quest.QuestDate);

        int result = db.update(TABLE_QUEST, dbquest, String.format("%s = ?", QUEST_ID),
                new String[] { Integer.toString(quest.QuestId) });

        return result > 0;
    }

    //endregion

    //region Data Getters

    public SQLiteDataModels.PlayerModel getPlayer(){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDataModels.PlayerModel player = new SQLiteDataModels.PlayerModel();

        String query = String.format("SELECT * FROM %s WHERE %s = '%s'",
                TABLE_PLAYER, PLAYER_ID, String.valueOf(1));
        Cursor dbEntry = db.rawQuery(query, null);
        if (dbEntry.getCount() > 0) {
            dbEntry.moveToFirst();

            player.PlayerName = dbEntry.getString(dbEntry.getColumnIndex(PLAYER_NAME));
            player.PlayerLevel = dbEntry.getInt(dbEntry.getColumnIndex(PLAYER_LEVEL));
            player.PlayerPoints = dbEntry.getInt(dbEntry.getColumnIndex(PLAYER_POINTS));
            player.PlayerType = dbEntry.getString(dbEntry.getColumnIndex(PLAYER_CLASS));

            dbEntry.close();
        }

        return player;
    }

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

        String query = String.format("SELECT * FROM %s WHERE %s = '%s'",
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
            quest.QuestType = nextQuest.getString(nextQuest.getColumnIndex(QUEST_TYPE));
            quest.QuestDuration = nextQuest.getInt(nextQuest.getColumnIndex(QUEST_DURATION));
            quest.QuestDate = nextQuest.getString(nextQuest.getColumnIndex(QUEST_DATE));

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
            quest.QuestType = dbEntry.getString(dbEntry.getColumnIndex(QUEST_TYPE));
            quest.QuestDuration = dbEntry.getInt(dbEntry.getColumnIndex(QUEST_DURATION));
            quest.QuestDate = dbEntry.getString(dbEntry.getColumnIndex(QUEST_DATE));

            dbEntry.close();
        }

        return quest;
    }

    //endregion
}
