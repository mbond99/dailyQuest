package com.example.dailyquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {

    private SQLiteConnection localDatabase;
    Handler myHandler = new Handler();  //handler is used to call delayed functions
    Button button;
    ImageView mage, cleric, fighter, rogue, mageAttack, clericAttack, fighterAttack, rogueAttack, deadMage, deadCleric, deadFighter, deadRogue;
    ImageView skeleton1, orc, flyingMonster, skeleton2, hoodedOrc, vampire, skeleton3, witch;
    ImageView skeleton1Attack, orcAttack, flyingMonsterAttack, skeleton2Attack, hoodedOrcAttack, vampireAttack, skeleton3Attack, witchAttack;
    ImageView deadSkeleton, deadOrc, deadFlyingMonster, deadHoodedOrc, deadVampire, deadWitch;
    TextView mDefeated, numMDefeated, playerHPText, numPlayerHP, monsterHPText, numMonsterHP;
    Monster currentMonster = new Monster();
    int monsterLevelNum = 1;
    int deadMonsters = 0;
    int strength, intelligence, constitution, dexterity;
    String className;
    int playerHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    //main game function
    public void startGame(View view){
        //create database connection to pull player class and stats
        localDatabase = new SQLiteConnection(this);
        SQLiteDataModels.PlayerModel playerInfo = localDatabase.getPlayer();
        className = playerInfo.PlayerType;
        System.out.println(className);

        ArrayList<SQLiteDataModels.StatModel> playerStats = localDatabase.getAllStats();
        for (SQLiteDataModels.StatModel stat : playerStats) {
            switch (stat.StatName){
                case "strength":
                    strength = stat.StatValue;
                    break;
                case "intelligence":
                    intelligence = stat.StatValue;
                    break;
                case "dexterity":
                    dexterity = stat.StatValue;
                    break;
                case "constitution":
                    constitution = stat.StatValue;
                    break;
            }
        }

        playerHP = (strength + intelligence + constitution + dexterity) * 10; //calculate playerHP from player stats

        localDatabase.close(); //info from database has been pulled can close database

        //get player images
        mage = (ImageView) findViewById(R.id.mage);
        cleric = (ImageView) findViewById(R.id.cleric);
        fighter = (ImageView) findViewById(R.id.fighter);
        rogue = (ImageView) findViewById(R.id.rogue);

        //get text displays
        mDefeated = (TextView) findViewById(R.id.monstersDefeated);
        numMDefeated = (TextView) findViewById(R.id.numMonstersDefeated);
        playerHPText = (TextView) findViewById(R.id.playerHP);
        numPlayerHP = (TextView) findViewById(R.id.playerHPValue);
        monsterHPText = (TextView) findViewById(R.id.monsterHP);
        numMonsterHP = (TextView) findViewById(R.id.monsterHPValue);

        //set text displays visible
        mDefeated.setVisibility(View.VISIBLE);
        numMDefeated.setVisibility(View.VISIBLE);
        playerHPText.setVisibility(View.VISIBLE);
        numPlayerHP.setVisibility(View.VISIBLE);
        monsterHPText.setVisibility(View.VISIBLE);
        numMonsterHP.setVisibility(View.VISIBLE);

        //set play button invisible
        button=(Button) findViewById(R.id.playButton);
        button.setVisibility(View.GONE);

        //find correct player block and display player image, add monster, display playerHP, and display attack buttons
        switch(className) {
            case "mage":
                mage.setVisibility(View.VISIBLE);
                currentMonster = addMonster();
                numPlayerHP.setText(String.valueOf(playerHP));
                displayAttackButtons();
                break;
            case "cleric":
                cleric.setVisibility(View.VISIBLE);
                currentMonster = addMonster();
                numPlayerHP.setText(String.valueOf(playerHP));
                displayAttackButtons();
                break;
            case "fighter":
                fighter.setVisibility(View.VISIBLE);
                currentMonster = addMonster();
                numPlayerHP.setText(String.valueOf(playerHP));
                displayAttackButtons();
                break;
            case "rogue":
                rogue.setVisibility(View.VISIBLE);
                currentMonster = addMonster();
                numPlayerHP.setText(String.valueOf(playerHP));
                displayAttackButtons();
                break;
            default:
                //there has been an error with the game return to the home menu
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                Game.this.startActivity(intent);
        }
    }

    //returns the next monster that needs to be added to the scene
    private Monster addMonster(){
        //get monster images
        skeleton1 = (ImageView) findViewById(R.id.skeleton1);
        orc = (ImageView) findViewById(R.id.orc);
        flyingMonster = (ImageView) findViewById(R.id.blue_flying_monster);
        skeleton2 = (ImageView) findViewById(R.id.skeleton2);
        hoodedOrc = (ImageView) findViewById(R.id.hooded_orc);
        vampire = (ImageView) findViewById(R.id.vampire);
        witch = (ImageView) findViewById(R.id.witch);
        skeleton3 = (ImageView) findViewById(R.id.skeleton3);
        numMonsterHP = (TextView) findViewById(R.id.monsterHPValue);

        //since we only have 8 monster images once a player has defeated all 8 monsters they start over with the first monster image with stats multiplied by incrementor
        int m = 1;
        int monsterHP;
        while (monsterLevelNum > 8){
            monsterLevelNum -= 8;
            m++;
        }

        //find correct monster block, remove previous monster image, add new monster image, create monster, display monsterHP, return correct monster
        switch(monsterLevelNum){
            case 1:
                skeleton3.setVisibility(View.INVISIBLE);
                skeleton1.setVisibility(View.VISIBLE);
                Monster level1Monster = new Monster("skeleton1", 1, 50*m, 2*m, 2*m, 2*m, 2*m);
                numMonsterHP.setText(String.valueOf(level1Monster.getMonsterHP()));
                return level1Monster;
            case 2:
                skeleton1.setVisibility(View.INVISIBLE);
                orc.setVisibility(View.VISIBLE);
                Monster level2Monster = new Monster("orc", 2, 75*m, 4*m, 1*m, 3*m, 2*m);
                monsterHP = 75*m;
                numMonsterHP.setText(String.valueOf(monsterHP));
                return level2Monster;
            case 3:
                orc.setVisibility(View.INVISIBLE);
                flyingMonster.setVisibility(View.VISIBLE);
                Monster level3Monster = new Monster("blue_flying_monster", 3, 100*m, 3*m, 4*m, 3*m ,3*m);
                monsterHP = 100*m;
                numMonsterHP.setText(String.valueOf(monsterHP));
                return level3Monster;
            case 4:
                flyingMonster.setVisibility(View.INVISIBLE);
                skeleton2.setVisibility(View.VISIBLE);
                Monster level4Monster = new Monster("skeleton2", 4, 150*m, 5*m, 5*m, 5*m, 5*m);
                monsterHP = 150*m;
                numMonsterHP.setText(String.valueOf(monsterHP));
                return level4Monster;
            case 5:
                skeleton2.setVisibility(View.INVISIBLE);
                vampire.setVisibility(View.VISIBLE);
                Monster level5Monster = new Monster("vampire", 5, 175*m, 6*m, 8*m, 4*m, 4*m);
                monsterHP = 175*m;
                numMonsterHP.setText(String.valueOf(monsterHP));
                return level5Monster;
            case 6:
                vampire.setVisibility(View.INVISIBLE);
                hoodedOrc.setVisibility(View.VISIBLE);
                Monster level6Monster = new Monster("hooded_orc", 6, 250*m, 8*m, 3*m, 7*m, 6*m);
                monsterHP = 250*m;
                numMonsterHP.setText(String.valueOf(monsterHP));
                return level6Monster;
            case 7:
                hoodedOrc.setVisibility(View.INVISIBLE);
                witch.setVisibility(View.VISIBLE);
                Monster level7Monster = new Monster("witch", 7, 325*m, 7*m, 6*m, 5*m, 7*m);
                monsterHP = 325*m;
                numMonsterHP.setText(String.valueOf(monsterHP));
                return level7Monster;
            case 8:
                witch.setVisibility(View.INVISIBLE);
                skeleton3.setVisibility(View.VISIBLE);
                Monster level8Monster = new Monster("skeleton3", 8, 450*m, 8*m,8*m,8*m,8*m);
                monsterHP = 450*m;
                numMonsterHP.setText(String.valueOf(monsterHP));
                return level8Monster;
            default:
                //there has been an error return an empty monster
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

    //sets attack buttons to invisible
    public void removeAttackButtons(){
        Button button1, button2, button3, button4, button5;
        button1 = (Button) findViewById(R.id.mindBlast);
        button1.setVisibility(View.INVISIBLE);
        button2 = (Button) findViewById(R.id.mysticBurst);
        button2.setVisibility(View.INVISIBLE);
        button3 = (Button) findViewById(R.id.blazingRush);
        button3.setVisibility(View.INVISIBLE);
        button4 = (Button) findViewById(R.id.chaosShock);
        button4.setVisibility(View.INVISIBLE);
        button5 = (Button) findViewById(R.id.smashingBlitz);
        button5.setVisibility(View.INVISIBLE);
    }

    //sets mindBlast, mysticBurst, and blazingRush buttons visible for mage or cleric players or
    //sets smashingBlitx, chaosShock, and blazingRush buttons visible for fighter or rogue players
    public void displayAttackButtons(){
        if(className.equals("mage") || className.equals("cleric")){
            Button button1, button2, button3;
            button1 = (Button) findViewById(R.id.mindBlast);
            button1.setVisibility(View.VISIBLE);
            button2 = (Button) findViewById(R.id.mysticBurst);
            button2.setVisibility(View.VISIBLE);
            button3 = (Button) findViewById(R.id.blazingRush);
            button3.setVisibility(View.VISIBLE);
        }
        else{
            Button button1, button2, button3;
            button1 = (Button) findViewById(R.id.smashingBlitz);
            button1.setVisibility(View.VISIBLE);
            button2 = (Button) findViewById(R.id.chaosShock);
            button2.setVisibility(View.VISIBLE);
            button3 = (Button) findViewById(R.id.blazingRush);
            button3.setVisibility(View.VISIBLE);
        }
    }

    //this method is run when the mind blast button is clicked
    //calls functions to complete 1 attack sequence between player and monster
    public void activateMindBlast(View view){
        int attack;
        displayMageOrClericAttack();
        attack = mindBlastAttack(intelligence, dexterity);
        applyAttackToMonster(currentMonster, attack);
        monsterAttack();
        displayMageOrClericAttack();
    }

    //this method is run when the mystic burst button is clicked
    //calls functions to complete 1 attack sequence between player and monster
    public void activateMysticBurst(View view){
        int attack;
        displayMageOrClericAttack();
        attack = mysticBurstAttack(strength, intelligence, dexterity);
        applyAttackToMonster(currentMonster, attack);
        monsterAttack();
        displayMageOrClericAttack();
    }

    //this method is run when the blazing rush button is clicked
    //calls functions to complete 1 attack sequence between player and monster
    public void activateBlazingRush(View view){
        int attack;
        if(className.equals("mage") || className.equals("cleric")){
            displayMageOrClericAttack();
        }
        else{
            displayFighterOrRogueAttack();
        }
        attack = blazingRushAttack(strength, intelligence, constitution, dexterity);
        applyAttackToMonster(currentMonster, attack);
        monsterAttack();
    }

    //this method is run when the smashing blitz button is clicked
    //calls functions to complete 1 attack sequence between player and monster
    public void activateSmashingBlitz(View view){
        int attack;
        displayFighterOrRogueAttack();
        attack = smashingBlitzAttack(strength, constitution);
        applyAttackToMonster(currentMonster, attack);
        monsterAttack();
        displayFighterOrRogueAttack();
    }

    //this method is run when the chaos shock button is clicked
    //calls functions to complete 1 attack sequence between player and monster
    public void activateChaosShock(View view){
        int attack;
        displayFighterOrRogueAttack();
        attack = chaosShockAttack(strength, intelligence, constitution);
        applyAttackToMonster(currentMonster, attack);
        monsterAttack();
        displayFighterOrRogueAttack();
    }

    //subtracts the player attack from the currentMonster and display the monsters new HP value on screen
    public void applyAttackToMonster(Monster currentMonster, int attack){
        int hp;
        hp = currentMonster.getMonsterHP();
        hp -= attack;
        currentMonster.setMonsterHP(hp);
        numMonsterHP = (TextView) findViewById(R.id.monsterHPValue);
        numMonsterHP.setText(String.valueOf(currentMonster.getMonsterHP()));
    }

    //if monsterHP is less than or equal to 0 increment number of dead monsters and display value on screen return true
    //otherwise reutrn false
    public boolean MonsterDied(){
        int hp = currentMonster.getMonsterHP();
        if(hp <= 0) {
            deadMonsters++;
            TextView text = (TextView) findViewById(R.id.numMonstersDefeated);
            text.setText(String.valueOf(deadMonsters));
            return true;
        }
        else
            return false;
    }

    //check if monster is dead. If monster dead display dead monster then increment monsterLevelNum
    //call delayedMonsterAttack
    public void monsterAttack(){
        boolean deadMonster;
        deadMonster = MonsterDied();
        if(deadMonster){
            switch(monsterLevelNum){
                case 1:
                    displayDeadSkeleton1();
                    myHandler.postDelayed(deadSkeletonDelay, 1000);
                    monsterLevelNum++;
                    break;
                case 2:
                    displayDeadOrc();
                    myHandler.postDelayed(deadOrcDelay, 1000);
                    monsterLevelNum++;
                    break;
                case 3:
                    displayDeadFlyingMonster();
                    myHandler.postDelayed(deadFlyingMonsterDelay,  1000);
                    monsterLevelNum++;
                    break;
                case 4:
                    displayDeadSkeleton2();
                    myHandler.postDelayed(deadSkeletonDelay, 1000);
                    monsterLevelNum++;
                    break;
                case 5:
                    displayDeadVampire();
                    myHandler.postDelayed(deadVampireDelay, 1000);
                    monsterLevelNum++;
                    break;
                case 6:
                    displayDeadHoodedOrc();
                    myHandler.postDelayed(deadHoodedOrcDelay, 1000);
                    monsterLevelNum++;
                    break;
                case 7:
                    displayDeadWitch();
                    myHandler.postDelayed(deadWitchDelay, 1000);
                    monsterLevelNum++;
                    break;
                case 8:
                    displayDeadSkeleton3();
                    myHandler.postDelayed(deadSkeletonDelay, 1000);
                    monsterLevelNum++;
                    break;
            }
        }
        myHandler.postDelayed(monsterAttackDelay, 1000);
    }

    //displays images for mage or cleric attacks
    public void displayMageOrClericAttack(){
        removeAttackButtons(); //remove the attack buttons so that players cannot spam the attack button

        //get images needed for attack
        mage = (ImageView) findViewById(R.id.mage);
        mageAttack = (ImageView) findViewById(R.id.mage_attack);
        cleric = (ImageView) findViewById(R.id.cleric);
        clericAttack = (ImageView) findViewById(R.id.cleric_attack);

        //make sure correct class block is called then remove regular image and replace with attack image call attackDelay
        if (className.equals("mage")){
            mage.setVisibility(View.INVISIBLE);
            mageAttack.setVisibility(View.VISIBLE);
            myHandler.postDelayed(mageAttackDelay, 1000);
        }
        else{
            cleric.setVisibility(View.INVISIBLE);
            clericAttack.setVisibility(View.VISIBLE);
            myHandler.postDelayed(clericAttackDelay, 1000);
        }
    }

    //displays images for fighter or rogue attacks
    public void displayFighterOrRogueAttack(){
        removeAttackButtons();
        fighter = (ImageView) findViewById(R.id.fighter);
        fighterAttack = (ImageView) findViewById(R.id.fighter_attack);
        rogue = (ImageView) findViewById(R.id.rogue);
        rogueAttack = (ImageView) findViewById(R.id.rogue_attack);
        if(className.equals("fighter")){
            fighter.setVisibility(View.INVISIBLE);
            fighterAttack.setVisibility(View.VISIBLE);
            myHandler.postDelayed(fighterAttackDelay, 1000);
        }
        else{
            rogue.setVisibility(View.INVISIBLE);
            rogueAttack.setVisibility(View.VISIBLE);
            myHandler.postDelayed(rogueAttackDelay, 1000);
        }
    }

    //the following functions display the dead image version of the monsters

    public void displayDeadSkeleton1(){
        skeleton1 = (ImageView) findViewById(R.id.skeleton1);
        deadSkeleton = (ImageView) findViewById(R.id.dead_skeleton);
        skeleton1.setVisibility(View.INVISIBLE);
        deadSkeleton.setVisibility(View.VISIBLE);
    }

    public void displayDeadSkeleton2(){
        skeleton2 = (ImageView) findViewById(R.id.skeleton2);
        deadSkeleton = (ImageView) findViewById(R.id.dead_skeleton);
        skeleton2.setVisibility(View.INVISIBLE);
        deadSkeleton.setVisibility(View.VISIBLE);
    }

    public void displayDeadSkeleton3(){
        skeleton3 = (ImageView) findViewById(R.id.skeleton3);
        deadSkeleton = (ImageView) findViewById(R.id.dead_skeleton);
        skeleton3.setVisibility(View.INVISIBLE);
        deadSkeleton.setVisibility(View.VISIBLE);
    }

    public void displayDeadOrc(){
        orc = (ImageView) findViewById(R.id.orc);
        deadOrc = (ImageView) findViewById(R.id.dead_orc);
        orc.setVisibility(View.INVISIBLE);
        deadOrc.setVisibility(View.VISIBLE);
    }

    public void displayDeadHoodedOrc(){
        hoodedOrc = (ImageView) findViewById(R.id.hooded_orc);
        deadHoodedOrc = (ImageView) findViewById(R.id.dead_hooded_orc);
        hoodedOrc.setVisibility(View.INVISIBLE);
        deadHoodedOrc.setVisibility(View.VISIBLE);
    }

    public void displayDeadVampire(){
        vampire = (ImageView) findViewById(R.id.vampire);
        vampireAttack = (ImageView) findViewById(R.id.dead_vampire);
        vampire.setVisibility(View.INVISIBLE);
        vampireAttack.setVisibility(View.VISIBLE);
    }

    public void displayDeadWitch(){
        witch = (ImageView) findViewById(R.id.witch);
        witchAttack = (ImageView) findViewById(R.id.dead_witch);
        witch.setVisibility(View.INVISIBLE);
        witchAttack.setVisibility(View.VISIBLE);
    }

    public void displayDeadFlyingMonster(){
        flyingMonster = (ImageView) findViewById(R.id.blue_flying_monster);
        flyingMonsterAttack = (ImageView) findViewById(R.id.dead_flying_monster);
        flyingMonster.setVisibility(View.INVISIBLE);
        flyingMonsterAttack.setVisibility(View.VISIBLE);
    }

    //display monster attacks
    public void displayMonsterAttack(){
        //get regular image and attack images for players
        skeleton1 = (ImageView)  findViewById(R.id.skeleton1);
        skeleton2 = (ImageView) findViewById(R.id.skeleton2);
        skeleton3 = (ImageView) findViewById(R.id.skeleton3);
        orc = (ImageView) findViewById(R.id.orc);
        hoodedOrc = (ImageView) findViewById(R.id.hooded_orc);
        flyingMonster = (ImageView) findViewById(R.id.blue_flying_monster);
        vampire = (ImageView) findViewById(R.id.vampire);
        witch = (ImageView) findViewById(R.id.witch);
        skeleton1Attack = (ImageView) findViewById(R.id.skeleton1_attack);
        skeleton2Attack = (ImageView) findViewById(R.id.skeleton2_attack);
        skeleton3Attack = (ImageView) findViewById(R.id.skeleton3_attack);
        orcAttack = (ImageView) findViewById(R.id.orc_attack);
        hoodedOrcAttack = (ImageView) findViewById(R.id.hooded_orc_attack);
        flyingMonsterAttack = (ImageView) findViewById(R.id.flying_monster_attack);
        vampireAttack = (ImageView) findViewById(R.id.vampire_attack);
        witchAttack = (ImageView) findViewById(R.id.witch_attack);

        //find correct monster and replace regular image with attack image then call attack delay
        switch(monsterLevelNum){
            case 1:
                skeleton1.setVisibility(View.INVISIBLE);
                skeleton1Attack.setVisibility(View.VISIBLE);
                myHandler.postDelayed(skeleton1AttackDelay, 1000);
                break;
            case 2:
                orc.setVisibility(View.INVISIBLE);
                orcAttack.setVisibility(View.VISIBLE);
                myHandler.postDelayed(orcAttackDelay, 1000);
                break;
            case 3:
                flyingMonster.setVisibility(View.INVISIBLE);
                flyingMonsterAttack.setVisibility(View.VISIBLE);
                myHandler.postDelayed(flyingMonsterAttackDelay, 1000);
                break;
            case 4:
                skeleton2.setVisibility(View.INVISIBLE);
                skeleton2Attack.setVisibility(View.VISIBLE);
                myHandler.postDelayed(skeleton2AttackDelay, 1000);
                break;
            case 5:
                vampire.setVisibility(View.INVISIBLE);
                vampireAttack.setVisibility(View.VISIBLE);
                myHandler.postDelayed(vampireAttackDelay, 1000);
                break;
            case 6:
                hoodedOrc.setVisibility(View.INVISIBLE);
                hoodedOrcAttack.setVisibility(View.VISIBLE);
                myHandler.postDelayed(hoodedOrcAttackDelay, 1000);
                break;
            case 7:
                witch.setVisibility(View.INVISIBLE);
                witchAttack.setVisibility(View.VISIBLE);
                myHandler.postDelayed(witchAttackDelay, 1000);
                break;
            case 8:
                skeleton3.setVisibility(View.INVISIBLE);
                skeleton3Attack.setVisibility(View.VISIBLE);
                myHandler.postDelayed(skeleton3AttackDelay, 1000);
                break;
        }
    }

    //the following Runnable objects remove the attack image and display the regular image for players and monsters

    private Runnable mageAttackDelay = new Runnable() {
        @Override
        public void run() {
            mage = (ImageView) findViewById(R.id.mage);
            mageAttack = (ImageView) findViewById(R.id.mage_attack);
            mageAttack.setVisibility(View.INVISIBLE);
            mage.setVisibility(View.VISIBLE);
        }
    };

    private Runnable clericAttackDelay = new Runnable() {
        @Override
        public void run() {
            cleric = (ImageView) findViewById(R.id.cleric);
            clericAttack = (ImageView) findViewById(R.id.cleric_attack);
            clericAttack.setVisibility(View.INVISIBLE);
            cleric.setVisibility(View.VISIBLE);
        }
    };

    private Runnable fighterAttackDelay = new Runnable() {
        @Override
        public void run() {
            fighter = (ImageView) findViewById(R.id.fighter);
            fighterAttack = (ImageView) findViewById(R.id.fighter_attack);
            fighterAttack.setVisibility(View.INVISIBLE);
            fighter.setVisibility(View.VISIBLE);
        }
    };

    private Runnable rogueAttackDelay = new Runnable() {
        @Override
        public void run() {
            rogue = (ImageView) findViewById(R.id.rogue);
            rogueAttack = (ImageView) findViewById(R.id.rogue_attack);
            rogueAttack.setVisibility(View.INVISIBLE);
            rogue.setVisibility(View.VISIBLE);
        }
    };

    private Runnable skeleton1AttackDelay = new Runnable() {
        @Override
        public void run() {
            skeleton1 = (ImageView) findViewById(R.id.skeleton1);
            skeleton1Attack = (ImageView) findViewById(R.id.skeleton1_attack);
            skeleton1Attack.setVisibility(View.INVISIBLE);
            skeleton1.setVisibility(View.VISIBLE);
            if(playerHP > 0)
                displayAttackButtons();
        }
    };

        private Runnable skeleton2AttackDelay = new Runnable() {
            @Override
            public void run() {
                skeleton2 = (ImageView) findViewById(R.id.skeleton2);
                skeleton2Attack = (ImageView) findViewById(R.id.skeleton2_attack);
                skeleton2Attack.setVisibility(View.INVISIBLE);
                skeleton2.setVisibility(View.VISIBLE);
                if(playerHP > 0)
                    displayAttackButtons();
            }
        };

        private Runnable skeleton3AttackDelay = new Runnable() {
            @Override
            public void run() {
                skeleton3 = (ImageView) findViewById(R.id.skeleton3);
                skeleton3Attack = (ImageView) findViewById(R.id.skeleton3_attack);
                skeleton3Attack.setVisibility(View.INVISIBLE);
                skeleton3.setVisibility(View.VISIBLE);
                if(playerHP > 0)
                    displayAttackButtons();
            }
        };

        private Runnable orcAttackDelay = new Runnable() {
            @Override
            public void run() {
                orc = (ImageView) findViewById(R.id.orc);
                orcAttack = (ImageView) findViewById(R.id.orc_attack);
                orcAttack.setVisibility(View.INVISIBLE);
                orc.setVisibility(View.VISIBLE);
                if(playerHP > 0)
                    displayAttackButtons();
            }
        };

        private Runnable hoodedOrcAttackDelay = new Runnable() {
            @Override
            public void run() {
                hoodedOrc = (ImageView) findViewById(R.id.hooded_orc);
                hoodedOrcAttack = (ImageView) findViewById(R.id.hooded_orc_attack);
                hoodedOrcAttack.setVisibility(View.INVISIBLE);
                hoodedOrc.setVisibility(View.VISIBLE);
                if (playerHP > 0)
                     displayAttackButtons();
            }
        };

        private Runnable flyingMonsterAttackDelay = new Runnable() {
            @Override
            public void run() {
                flyingMonster = (ImageView) findViewById(R.id.blue_flying_monster);
                flyingMonsterAttack = (ImageView) findViewById(R.id.flying_monster_attack);
                flyingMonsterAttack.setVisibility(View.INVISIBLE);
                flyingMonster.setVisibility(View.VISIBLE);
                if (playerHP > 0)
                    displayAttackButtons();
            }
        };

        private Runnable vampireAttackDelay = new Runnable() {
            @Override
            public void run() {
                vampire = (ImageView) findViewById(R.id.vampire);
                vampireAttack = (ImageView) findViewById(R.id.vampire_attack);
                vampireAttack.setVisibility(View.INVISIBLE);
                vampire.setVisibility(View.VISIBLE);
                if (playerHP > 0)
                    displayAttackButtons();
            }
        };

        private Runnable witchAttackDelay = new Runnable() {
            @Override
            public void run() {
                witch = (ImageView) findViewById(R.id.witch);
                witchAttack = (ImageView) findViewById(R.id.witch_attack);
                witchAttack.setVisibility(View.INVISIBLE);
                witch.setVisibility(View.VISIBLE);
                if (playerHP > 0)
                    displayAttackButtons();
            }
        };

        private Runnable deadSkeletonDelay = new Runnable() {
            @Override
            public void run() {
                deadSkeleton = (ImageView) findViewById(R.id.dead_skeleton);
                deadSkeleton.setVisibility(View.INVISIBLE);
                currentMonster = addMonster();
            }
        };

    private Runnable deadOrcDelay = new Runnable() {
        @Override
        public void run() {
            deadOrc = (ImageView) findViewById(R.id.dead_orc);
            deadOrc.setVisibility(View.INVISIBLE);
            currentMonster = addMonster();
        }
    };

    private Runnable deadFlyingMonsterDelay = new Runnable() {
        @Override
        public void run() {
            deadFlyingMonster = (ImageView) findViewById(R.id.dead_flying_monster);
            deadFlyingMonster.setVisibility(View.INVISIBLE);
            currentMonster = addMonster();
        }
    };

    private Runnable deadVampireDelay = new Runnable() {
        @Override
        public void run() {
            deadVampire = (ImageView) findViewById(R.id.dead_vampire);
            deadVampire.setVisibility(View.INVISIBLE);
            currentMonster = addMonster();
        }
    };

    private Runnable deadHoodedOrcDelay = new Runnable() {
        @Override
        public void run() {
            deadHoodedOrc = (ImageView) findViewById(R.id.dead_hooded_orc);
            deadHoodedOrc.setVisibility(View.INVISIBLE);
            currentMonster = addMonster();
        }
    };

    private Runnable deadWitchDelay = new Runnable() {
        @Override
        public void run() {
            deadWitch = (ImageView) findViewById(R.id.dead_witch);
            deadWitch.setVisibility(View.INVISIBLE);
            currentMonster = addMonster();
        }
    };

    //applys monsters attack to player and displays the monster attack
        private Runnable monsterAttackDelay = new Runnable() {
            @Override
            public void run() {
                int multi, attack;
                Random rand = new Random();
                multi = rand.nextInt(4); //will return random number from 0-3
                attack = currentMonster.getMonsterStrength() * multi + currentMonster.getMonsterIntelligance() * multi + currentMonster.getMonsterConsitution() * multi + currentMonster.getMonsterDexterity() * multi;
                playerHP -= attack;

                //if the player's HP is less than or equal to 0 call the gameOver delay
                if(playerHP <= 0){
                    playerHP = 0;
                    myHandler.postDelayed(gameOverDelay, 1000);
                }
                //display new playerHP to the screen
                numPlayerHP = (TextView) findViewById(R.id.playerHPValue);
                numPlayerHP.setText(String.valueOf(playerHP));
                displayMonsterAttack();
            }
        };

        //displays game over text, continue button, and dead player image
        private Runnable gameOverDelay = new Runnable() {
            @Override
            public void run() {
                TextView text;
                text = (TextView) findViewById(R.id.gameOver);
                text.setVisibility(View.VISIBLE);
                button = (Button) findViewById(R.id.returnMainMenu);
                button.setVisibility(View.VISIBLE);
                removeAttackButtons();
                switch(className){
                    case "mage":
                        mage = (ImageView) findViewById(R.id.mage);
                        deadMage = (ImageView) findViewById(R.id.dead_mage);
                        mage.setVisibility(View.INVISIBLE);
                        deadMage.setVisibility(View.VISIBLE);
                        break;
                    case "cleric":
                        cleric = (ImageView) findViewById(R.id.cleric);
                        deadCleric = (ImageView) findViewById(R.id.dead_cleric);
                        cleric.setVisibility(View.INVISIBLE);
                        deadCleric.setVisibility(View.VISIBLE);
                        break;
                    case "fighter":
                        fighter = (ImageView) findViewById(R.id.fighter);
                        deadFighter = (ImageView) findViewById(R.id.dead_fighter);
                        fighter.setVisibility(View.INVISIBLE);
                        deadFighter.setVisibility(View.VISIBLE);
                        break;
                    case "rogue":
                        rogue = (ImageView) findViewById(R.id.rogue);
                        deadRogue = (ImageView) findViewById(R.id.dead_rogue);
                        rogue.setVisibility(View.INVISIBLE);
                        deadRogue.setVisibility(View.VISIBLE);
                        break;
                }
            }
        };

        //method is called when continue button is clicked returns screen to Main menu
        public void returnToMainMenu(View view){
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            Game.this.startActivity(intent);
        }
}