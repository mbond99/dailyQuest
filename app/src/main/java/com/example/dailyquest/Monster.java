package com.example.dailyquest;

public class Monster {
    //variables
    private String monsterName;
    private int levelNum;
    private int monsterHP;
    private int monsterStrength;
    private int monsterIntelligance;
    private int monsterDexterity;
    private int monsterConsitution;

    //constructors
    public Monster()
    {
        monsterName = "";
        levelNum = 0;
        monsterHP = 0;
        monsterStrength = 0;
        monsterIntelligance = 0;
        monsterConsitution = 0;
        monsterDexterity = 0;
    }
    public Monster(String name, int level, int HP, int strength, int intelligance, int constitustion, int dexterity)
    {
        monsterName = name;
        levelNum = level;
        monsterHP = HP;
        monsterStrength = strength;
        monsterIntelligance = intelligance;
        monsterConsitution = constitustion;
        monsterDexterity = dexterity;
    }

    //getter and setter functions
    private void setMonsterName(String name)
    {
        monsterName = name;
    }
    private void setLevelNum(int level)
    {
        levelNum = level;
    }
    private void setMonsterHP(int hp)
    {
        monsterHP = hp;
    }
    private void setMonsterStrength(int strength)
    {
        monsterStrength = strength;
    }
    private void setMonsterIntelligance(int intelligance)
    {
        monsterIntelligance = intelligance;
    }
    private void setMonsterDexterity(int dexterity)
    {
        monsterDexterity = dexterity;
    }
    private void setMonsterConsitution(int consitution)
    {
        monsterConsitution = consitution;
    }
    public String getMonsterName()
    {
        return monsterName;
    }
    public int getLevelNum()
    {
        return levelNum;
    }
    public int getMonsterHP()
    {
        return monsterHP;
    }
    public int getMonsterStrength()
    {
        return monsterStrength;
    }
    public int getMonsterIntelligance()
    {
        return monsterIntelligance;
    }
    public int getMonsterDexterity()
    {
        return monsterDexterity;
    }
    public int getMonsterConsitution()
    {
        return monsterConsitution;
    }

    //the attack player function takes the values of the monsters stats and performs the attack
    //calculations then returns the attack value
    public int attackPlayer()
    {

    }
}
