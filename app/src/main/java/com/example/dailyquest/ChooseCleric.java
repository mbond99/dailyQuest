package com.example.dailyquest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class ChooseCleric extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_cleric);
    }

    public void toMageViewFromCleric(View view){
        Intent intent = new Intent(getApplicationContext(), ChooseMage.class);
        ChooseCleric.this.startActivity(intent);
    }

    public void toFighterViewFromCleric(View view){
        Intent intent = new Intent(getApplicationContext(), ChooseFighter.class);
        ChooseCleric.this.startActivity(intent);
    }
}