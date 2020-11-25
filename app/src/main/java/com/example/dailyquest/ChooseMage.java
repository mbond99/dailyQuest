package com.example.dailyquest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.os.Bundle;

public class ChooseMage extends AppCompatActivity {
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mage);
    }

    public void toClericView(View view){
        Intent intent = new Intent(getApplicationContext(), ChooseCleric.class);
        ChooseMage.this.startActivity(intent);
    }
}