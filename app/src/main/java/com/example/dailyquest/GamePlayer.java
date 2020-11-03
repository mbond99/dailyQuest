package com.example.dailyquest;
import java.util.Random;

public class GamePlayer {
    //variables
    private String playerName;
    private String playerClass;
    private int playerLevel;
    private int playerPoints;
    private int playerHP;
    private int playerStrength;
    private int playerIntelligance;
    private int playerDexterity;
    private int playerConstitution;

    //constructors
    public GamePlayer()
    {
        playerName = "";
        playerClass = "";
        playerLevel = 0;
        playerPoints = 0;
        playerHP = 0;
        playerStrength = 0;
        playerIntelligance = 0;
        playerDexterity = 0;
        playerConstitution = 0;
    }

    public GamePlayer(String name, String pClass, int level, int points, int hp, int stregth, int intelligance, int dexterity, int constitution)
    {
        playerName = name;
        playerClass = pClass;
        playerLevel = level;
        playerPoints = points;
        playerHP = hp;
        playerStrength = stregth;
        playerIntelligance = intelligance;
        playerDexterity = dexterity;
        playerConstitution = constitution;
    }

    //getter and setter functions
    private void setPlayerName(String name)
    {
        playerName = name;
    }
    private void setPlayerClass(String pClass)
    {
        playerClass = pClass;
    }
    private void setPlayerLevel(int level)
    {
        playerLevel = level;
    }
    private void setPlayerPoints(int points)
    {
        playerPoints = points;
    }
    private void setPlayerHP(int hp)
    {
        playerHP = hp;
    }
    private void setPlayerStrength(int strength)
    {
        playerStrength = strength;
    }
    private void setPlayerIntelligance(int intelligance)
    {
        playerIntelligance = intelligance;
    }
    private void setPlayerDexterity(int dexterity)
    {
        playerDexterity = dexterity;
    }
    private void setPlayerConstitution(int constitution)
    {
        playerConstitution = constitution;
    }

    public String getPlayerName()
    {
        return playerName;
    }
    public String getPlayerClass()
    {
        return playerClass;
    }
    public int getPlayerLevel()
    {
        return playerLevel;
    }
    public int getPlayerPoints()
    {
        return playerPoints;
    }
    public int getPlayerHP()
    {
        return playerHP;
    }
    public int getPlayerStrength()
    {
        return playerStrength;
    }
    public int getPlayerIntelligance()
    {
        return playerIntelligance;
    }
    public int getPlayerDexterity()
    {
        return playerDexterity;
    }
    public int getPlayerConstitution()
    {
        return playerConstitution;
    }
    //the attackMonster function uses the players stats to calculate the attack and returns the attack points
    public int attackMonster()
    {

    }

    //the defend function subtracts 10 points from the player since they chose to buy defense
    // and returns a value that will be used to block the monster's attack
    public int Defend()
    {
        playerPoints -= 10;

        //when a player purchases a defense it will return a value between 5-10
        Random ranNum = new Random();
        int upperbound = 6;
        int attack = ranNum.nextInt(upperbound) + 5;

        return attack;
    }

    //if the player has scored enough points increase the player level
    public void levelUP()
    {

    }

}
