package com.example.dailyquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Database specific declarations
    private SQLiteConnection localDatabase;
    private boolean FirstTimeStartUp = true;
    private static final boolean RESET_DATABASE = false;
    private static final boolean TEST_DATABASE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseSetup();

        Intent intent;
        if (FirstTimeStartUp){
            // This is the first time the user has played the game, launch the character creation
            // menu and then start the main menu.
            intent = new Intent(getApplicationContext(), ChooseCharacterActivity.class);
        }
        else{
            // Boots up the main menu on app startup, unless database has not been created.
            intent = new Intent(getApplicationContext(), MainMenu.class);
        }
        MainActivity.this.startActivity(intent);
    }


    /** Sets up everything needed for the database connection **/
    private void DatabaseSetup(){
        localDatabase = new SQLiteConnection(this); //Game needs this

        // Wipes the database if RESET_DATABASE is true
        if (RESET_DATABASE) {
            localDatabase.wipeDatabase();
        }

        FirstTimeStartUp = localDatabase.isFirstTimeStartUp(); // Check if this is the first time the game is launching

        // Tests to see if database is properly loading
        if (TEST_DATABASE) {
            ArrayList<SQLiteDataModels.StatModel> stats = localDatabase.getAllStats();
            for (SQLiteDataModels.StatModel stat : stats) {
                System.out.println(String.format("\tID: %d, NAME: %s, VALUE: %d", stat.StatId, stat.StatName, stat.StatValue));
            }

            // Testing to see if updateStat works
            for (SQLiteDataModels.StatModel stat : stats) {
                System.out.println(localDatabase.updateStat(stat.StatName, stat.StatValue+1));
            }

            SQLiteDataModels.StatModel strengthStat = localDatabase.getStatByName("strength");
            System.out.println(strengthStat.StatValue);

            // Reload stats to make sure they were updated in the database
            stats = localDatabase.getAllStats();
            for (SQLiteDataModels.StatModel stat : stats) {
                System.out.println(String.format("\tID: %d, NAME: %s, VALUE: %d", stat.StatId, stat.StatName, stat.StatValue));
            }
        }

        localDatabase.close();
    }
}
