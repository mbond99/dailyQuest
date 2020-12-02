package com.example.dailyquest;

import android.content.Intent;
import android.os.Bundle;

import com.alamkanak.weekview.WeekViewEvent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestActivity extends AppCompatActivity {

    private Quest quest;
    private SQLiteConnection localDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        quest = (Quest) getIntent().getSerializableExtra("QuestView");

        localDatabase = new SQLiteConnection(this);

        setContentView(R.layout.activity_quest);
        TextView text = findViewById(R.id.questText);
        text.setText(quest.getDescription());

    }

    public void yesButton(View view){
        ArrayList<SQLiteDataModels.StatModel> playerStats = localDatabase.getAllStats();
        if(quest.getType().equals("Fitness")){
            updateStats(playerStats,2,1,1,1);
        }
        else if(quest.getType().equals("Mental")){
            updateStats(playerStats,1,2,1,1);
        }
        else{
            updateStats(playerStats,1,1,1,2);
        }
        removeFromDB();
        localDatabase.close();
        Intent intent = new Intent(getApplicationContext(), BasicActivity.class);
        QuestActivity.this.startActivity(intent);
    }

    public void noButton(View view){
        localDatabase.close();
        Intent intent = new Intent(getApplicationContext(), BasicActivity.class);
        QuestActivity.this.startActivity(intent);
    }

    public void deleteButton(View view) {
        removeFromDB();
        localDatabase.close();
        Intent intent = new Intent(getApplicationContext(), BasicActivity.class);
        QuestActivity.this.startActivity(intent);
    }

    private void updateStats(ArrayList<SQLiteDataModels.StatModel> stats, int a, int b, int c, int d){
        for (SQLiteDataModels.StatModel stat : stats) {
            switch (stat.StatName) {
                case "strength":
                    stat.StatValue += a;
                    localDatabase.updateStat("strength", stat.StatValue);
                    break;
                case "intelligence":
                    stat.StatValue += b;
                    localDatabase.updateStat("intelligence", stat.StatValue);
                    break;
                case "dexterity":
                    stat.StatValue += c;
                    localDatabase.updateStat("constitution", stat.StatValue);
                    break;
                case "constitution":
                    stat.StatValue += d;
                    localDatabase.updateStat("dexterity", stat.StatValue);
                    break;
            }
        }
    }

    private void removeFromDB(){
        localDatabase.deleteQuestById(quest.getId());
    }
}