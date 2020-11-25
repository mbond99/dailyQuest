package com.example.dailyquest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class ChooseFighter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_fighter);
    }

    public void toClericViewFromFighter(View view){
        Intent intent = new Intent(getApplicationContext(), ChooseCleric.class);
        ChooseFighter.this.startActivity(intent);
    }

    public void toRogueViewFromFighter(View view){
        Intent intent = new Intent(getApplicationContext(), ChooseRogue.class);
        ChooseFighter.this.startActivity(intent);
    }
}