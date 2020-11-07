package com.example.dailyquest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {

    private SQLiteConnection localDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localDatabase = new SQLiteConnection(this);
        ArrayList<SQLiteDataModels.StatModel> stats = localDatabase.getAllStats();
        setContentView(R.layout.activity_main_menu);
    }
}