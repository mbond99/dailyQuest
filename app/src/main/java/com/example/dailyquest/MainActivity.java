package com.example.dailyquest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // Database specific declarations
    private SQLiteConnection localDatabase;
    private static final boolean RESET_DATABASE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseSetup();

        setContentView(R.layout.activity_main);
    }

    /** Sets up everything needed for the database connection **/
    private void DatabaseSetup(){
        localDatabase = new SQLiteConnection(this);

        // Wipes the database if RESET_DATABASE is true
        if (RESET_DATABASE) {
            localDatabase.wipeDatabase();
        }
    }
}
