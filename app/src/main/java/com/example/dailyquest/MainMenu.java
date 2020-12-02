package com.example.dailyquest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {

    private SQLiteConnection localDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localDatabase = new SQLiteConnection(this);

        ArrayList<SQLiteDataModels.StatModel> stats = localDatabase.getAllStats();
        setContentView(R.layout.activity_main_menu);

        updatePlayerStatsDisplay();
    }

    public void toCalView(View view) {
        Intent intent = new Intent(getApplicationContext(), BasicActivity.class);
        MainMenu.this.startActivity(intent);
    }

    public void toGame(View view){
        Intent intent = new Intent(getApplicationContext(), Game.class);
        MainMenu.this.startActivity(intent);
    }

    @SuppressLint("ResourceType")
    private void updatePlayerStatsDisplay(){
        ArrayList<SQLiteDataModels.StatModel> playerStats = localDatabase.getAllStats();

        SQLiteDataModels.PlayerModel player = localDatabase.getPlayer();
        TextView welcomeMessage = (TextView)findViewById(R.id.welcomeText);
        welcomeMessage.setText("Welcome back " + player.PlayerName);

        for (SQLiteDataModels.StatModel stat : playerStats) {
            TextView textbox = new TextView(this);
            switch (stat.StatName){
                case "strength":
                     textbox = (TextView)findViewById(R.id.strengthStatBox);
                     break;
                case "intelligence":
                    textbox = (TextView)findViewById(R.id.intelligenceStatBox);
                    break;
                case "dexterity":
                    textbox = (TextView)findViewById(R.id.dexterityStatBox);
                    break;
                case "constitution":
                    textbox = (TextView)findViewById(R.id.constitutionStatBox);
                    break;
            }
            textbox.setText(String.valueOf(stat.StatValue));
        }

        String playerClass = localDatabase.getPlayer().PlayerType;

        ImageView image = (ImageView)findViewById(R.id.avatarImg);
        switch (playerClass.toLowerCase()){
            case "mage":
                image.setImageResource(R.drawable.mage);
                break;
            case "fighter":
                image.setImageResource(R.drawable.fighter);
                break;
            case "cleric":
                image.setImageResource(R.drawable.cleric);
                break;
            case "rogue":
                image.setImageResource(R.drawable.rogue);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // Disables the back button on this screen
    }
}