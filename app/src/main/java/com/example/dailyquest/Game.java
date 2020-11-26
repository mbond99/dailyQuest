package com.example.dailyquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class Game extends AppCompatActivity {

    private SQLiteConnection localDatabase;
    Button button;
    ImageView image;
    Monster currentMonster = new Monster();
    //need to get from db
    String className = "rogue";
    int strength = 0;
    int intelligence = 0;
    int constitution = 0;
    int dexterity = 0;
    int playerHP = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    //returns the next monster that needs to be added to the scene
    private Monster addMonster(int levelNum){
        int m = 1;
        while (levelNum > 8){
            levelNum -= 8;
            m++;
        }
        switch(levelNum){
            case 1:
                image = (ImageView) findViewById(R.id.skeleton1);
                image.setVisibility(View.VISIBLE);
                Monster level1Monster = new Monster("skeleton1", 1, 50*m, 2*m, 2*m, 2*m, 2*m);
                return level1Monster;
            case 2:
                image = (ImageView) findViewById(R.id.orc);
                image.setVisibility(View.VISIBLE);
                Monster level2Monster = new Monster("orc", 2, 75*m, 4*m, 1*m, 3*m, 2*m);
                return level2Monster;
            case 3:
                image = (ImageView) findViewById(R.id.blue_flying_monster);
                image.setVisibility(View.VISIBLE);
                Monster level3Monster = new Monster("blue_flying_monster", 3, 100*m, 3*m, 4*m, 3*m ,3*m);
                return level3Monster;
            case 4:
                image = (ImageView) findViewById(R.id.skeleton2);
                image.setVisibility(View.VISIBLE);
                Monster level4Monster = new Monster("skeleton2", 4, 150*m, 5*m, 5*m, 5*m, 5*m);
                return level4Monster;
            case 5:
                image = (ImageView) findViewById(R.id.vampire);
                image.setVisibility(View.VISIBLE);
                Monster level5Monster = new Monster("vampire", 5, 175*m, 6*m, 8*m, 4*m, 4*m);
                return level5Monster;
            case 6:
                image = (ImageView) findViewById(R.id.hooded_orc);
                image.setVisibility(View.VISIBLE);
                Monster level6Monster = new Monster("hooded_orc", 6, 250*m, 8*m, 3*m, 7*m, 6*m);
                return level6Monster;
            case 7:
                image = (ImageView) findViewById(R.id.witch);
                image.setVisibility(View.VISIBLE);
                Monster level7Monster = new Monster("witch", 7, 325*m, 7*m, 6*m, 5*m, 7*m);
                return level7Monster;
            case 8:
                image = (ImageView) findViewById(R.id.skeleton3);
                image.setVisibility(View.VISIBLE);
                Monster level8Monster = new Monster("skeleton3", 8, 450*m, 8*m,8*m,8*m,8*m);
                return level8Monster;
            default:
                //all monsters have been defeated or there has been an error return an empty monster
                Monster emptyMonster = new Monster();
                return emptyMonster;
        }
    }

    //intelligance based attack for mage or cleric
    public int mindBlastAttack(int i, int d){
        int intelligence = i;
        int dexterity = d;
        Random rand = new Random();
        int attack = 0;
        int iMulti = rand.nextInt(3) + 1; //will return a random number between 1-3
        int dMulti = rand.nextInt(3); //will return a random number from 0-2
        attack = intelligence * iMulti +  dexterity * dMulti;
        return attack;
    }

    //dexterity based attack for mage or cleric
    public int mysticBurstAttack(int s, int i, int d){
        int strength = s;
        int intelligence = i;
        int dexterity = d;
        int attack = 0;
        Random rand = new Random();
        int sMulti = rand.nextInt(2); //will return 0 or 1
        int iMulti = rand.nextInt(2);
        int dMulti = rand.nextInt(4) + 1; //will return random number from 1-4
        attack = strength* sMulti + intelligence * iMulti + dexterity * dMulti;
        return attack;
    }

    //strength based attack for fighter and rogue
    public int smashingBlitzAttack(int s, int c){
        int strength = s;
        int constitution = c;
        int attack = 0;
        Random rand = new Random();
        int sMulti = rand.nextInt(3) + 1; //will return a random number between 1-3
        int cMulti = rand.nextInt(3); //will return a random number from 0-2
        attack = strength * sMulti + constitution * cMulti;
        return attack;
    }

    //constitution based attack for fighter and rogue
    public int chaosShockAttack(int s, int i, int c){
        int strength = s;
        int intelligence = i;
        int constitution = c;
        int attack = 0;
        Random rand = new Random();
        int sMulti = rand.nextInt(2); //will return 0 or 1
        int iMulti = rand.nextInt(2);
        int cMulti = rand.nextInt(4) + 1; //will return random number from 1-4
        attack = strength* sMulti + intelligence * iMulti + constitution * cMulti;
        return attack;
    }

    //rounded attack for all player types focuses on all four stats
    public int blazingRushAttack(int s, int i, int c, int d){
        int strength = s;
        int intelligence = i;
        int constitution = c;
        int dexterity = d;
        int attack = 0;
        Random rand = new Random();
        int s_iMulti = rand.nextInt(3) + 1; //will return random number from 1-3
        int c_dMulti = rand.nextInt(3); //will return random number from 0-2
        attack = strength * s_iMulti + intelligence * s_iMulti + constitution * c_dMulti + dexterity * c_dMulti;
        return attack;
    }

    //main game function
    public void startGame(View view){
        //set button invisible
        button=(Button) findViewById(R.id.playButton);
        button.setVisibility(View.GONE);
        localDatabase = new SQLiteConnection(this);
        int levelNum = 1;
        switch(className){
            case "mage":
                image = (ImageView) findViewById(R.id.mage);
                image.setVisibility(View.VISIBLE);
                currentMonster = addMonster(levelNum);
                displayMageClericAttackButtons();
                monsterAttack();
                break;
            case "cleric":
                image = (ImageView) findViewById(R.id.cleric);
                image.setVisibility(View.VISIBLE);
                currentMonster = addMonster(levelNum);
                displayMageClericAttackButtons();
                monsterAttack();
                break;
            case "fighter":
                image = (ImageView) findViewById(R.id.fighter);
                image.setVisibility(View.VISIBLE);
                currentMonster = addMonster(levelNum);
                displayFighterRogueAttackButtons();
                monsterAttack();
                break;
            case "rogue":
                image = (ImageView) findViewById(R.id.rogue);
                image.setVisibility(View.VISIBLE);
                currentMonster = addMonster(levelNum);
                displayFighterRogueAttackButtons();
                monsterAttack();
                break;
            default:
               //there has been an error with the game return to the home menu
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                Game.this.startActivity(intent);
        }
    }

    public void displayMageClericAttackButtons(){
        Button button1, button2, button3;
        button1 = (Button) findViewById(R.id.mindBlast);
        button1.setVisibility(View.VISIBLE);
        button2 = (Button) findViewById(R.id.mysticBurst);
        button2.setVisibility(View.VISIBLE);
        button3 = (Button) findViewById(R.id.blazingRush);
        button3.setVisibility(View.VISIBLE);
    }

    public void removeMageClericAttackButtons(){
        Button button1, button2, button3;
        button1 = (Button) findViewById(R.id.mindBlast);
        button1.setVisibility(View.INVISIBLE);
        button2 = (Button) findViewById(R.id.mysticBurst);
        button2.setVisibility(View.INVISIBLE);
        button3 = (Button) findViewById(R.id.blazingRush);
        button3.setVisibility(View.INVISIBLE);
    }

    public void displayFighterRogueAttackButtons(){
        Button button1, button2, button3;
        button1 = (Button) findViewById(R.id.smashingBlitz);
        button1.setVisibility(View.VISIBLE);
        button2 = (Button) findViewById(R.id.chaosShock);
        button2.setVisibility(View.VISIBLE);
        button3 = (Button) findViewById(R.id.blazingRush);
        button3.setVisibility(View.VISIBLE);
    }

    public void removeFighterRogueAttackButtons(){
        Button button1, button2, button3;
        button1 = (Button) findViewById(R.id.smashingBlitz);
        button1.setVisibility(View.INVISIBLE);
        button2 = (Button) findViewById(R.id.chaosShock);
        button2.setVisibility(View.INVISIBLE);
        button3 = (Button) findViewById(R.id.blazingRush);
        button3.setVisibility(View.INVISIBLE);
    }

    public void activateMindBlast(View view){
        int attack;
        attack = mindBlastAttack(intelligence, dexterity);
        applyAttackToMonster(currentMonster, attack);
        removeMageClericAttackButtons();
    }

    public void activateMysticBurst(View view){
        int attack;
        attack = mysticBurstAttack(strength, intelligence, dexterity);
        applyAttackToMonster(currentMonster, attack);
        removeMageClericAttackButtons();
    }

    public void activateBlazingRush(View view){
        int attack;
        attack = blazingRushAttack(strength, intelligence, constitution, dexterity);
        applyAttackToMonster(currentMonster, attack);
        if (className == "mage" || className == "cleric"){
            removeMageClericAttackButtons();
        }
        else{
            removeFighterRogueAttackButtons();
        }
    }

    public void activateSmashingBlitz(View view){
        int attack;
        attack = smashingBlitzAttack(strength, constitution);
        applyAttackToMonster(currentMonster, attack);
        removeFighterRogueAttackButtons();
    }

    public void activateChaosShock(View view){
        int attack;
        attack = chaosShockAttack(strength, intelligence, constitution);
        applyAttackToMonster(currentMonster, attack);
        removeFighterRogueAttackButtons();
    }

    public void applyAttackToMonster(Monster currentMonster, int attack){
        int hp;
        hp = currentMonster.getMonsterHP();
        hp -= attack;
        currentMonster.setMonsterHP(hp);
    }

    public boolean MonsterDied(){
        int hp = currentMonster.getMonsterHP();
        if(hp <= 0)
            return true;
        else
            return false;
    }

    public boolean PlayerDied(){
        if(playerHP <= 0)
            return true;
        else
            return false;
    }

    public void monsterAttack(){
        int attack = 0;
        Random rand = new Random();
        int multi = rand.nextInt(4); //will return random number from 0-3
        attack = currentMonster.getMonsterStrength() * multi + currentMonster.getMonsterIntelligance() * multi + currentMonster.getMonsterConsitution() * multi + currentMonster.getMonsterDexterity() * multi;
        playerHP -= attack;
    }

    public void gameOver(){

    }
}