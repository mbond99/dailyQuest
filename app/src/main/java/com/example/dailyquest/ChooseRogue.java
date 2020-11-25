package com.example.dailyquest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class ChooseRogue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_rogue);
    }

    public void toFighterViewFromRogue(View view){
        Intent intent = new Intent(getApplicationContext(), ChooseFighter.class);
        ChooseRogue.this.startActivity(intent);
    }
}