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

public class QuestActivity extends AppCompatActivity {

    Quest quest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        quest = (Quest) getIntent().getSerializableExtra("QuestView");

        setContentView(R.layout.activity_quest);
        TextView text = findViewById(R.id.questText);
        text.setText(quest.getDescription());

    }

    public void yesButton(){

    }

    public void noButton(View view){
        Intent intent = new Intent(getApplicationContext(), BasicActivity.class);
        QuestActivity.this.startActivity(intent);
    }

}