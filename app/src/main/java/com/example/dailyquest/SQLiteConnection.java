package com.example.dailyquest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.execSQL(String.format("DROP TABLE IF EXISTS %s;", TABLE_PLAYER));
        db.execSQL(String.format("DROP TABLE IF EXISTS %s;", TABLE_STAT));
        db.execSQL(String.format("DROP TABLE IF EXISTS %s;", TABLE_QUEST));

        // Recreate the database
        onCreate(db);
    }

    //endregion
}
