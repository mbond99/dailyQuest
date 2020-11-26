package com.example.dailyquest;

public class Monster {
    //variables
    private String monsterName;
    private int levelNum;
    private int monsterHP;
    private int monsterStrength;
    private int monsterIntelligence;
    private int monsterDexterity;
    private int monsterConstitution;

    //constructors
    public Monster()
    {
        monsterName = "";
        levelNum = 0;
        monsterHP = 0;
        monsterStrength = 0;
        monsterIntelligence = 0;
        monsterConstitution = 0;
        monsterDexterity = 0;
    }
    public Monster(String name, int level, int HP, int strength, int intelligence, int constitution, int dexterity)
    {
        monsterName = name;
        levelNum = level;
        monsterHP = HP;
        monsterStrength = strength;
        monsterIntelligence = intelligence;
        monsterConstitution = constitution;
        monsterDexterity = dexterity;
    }

    //getter and setter functions
    public void setMonsterName(String name)
    {
        monsterName = name;
    }
    public void setLevelNum(int level)
    {
        levelNum = level;
    }
    public void setMonsterHP(int hp)
    {
        monsterHP = hp;
    }
    public void setMonsterStrength(int strength)
    {
        monsterStrength = strength;
    }
    public void setMonsterIntelligance(int intelligence)
    {
        monsterIntelligence = intelligence;
    }
    public void setMonsterDexterity(int dexterity)
    {
        monsterDexterity = dexterity;
    }
    public void setMonsterConsitution(int constitution)
    {
        monsterConstitution = constitution;
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
        return monsterIntelligence;
    }
    public int getMonsterDexterity()
    {
        return monsterDexterity;
    }
    public int getMonsterConsitution()
    {
        return monsterConstitution;
    }

    //the attack player function takes the values of the monsters stats and performs the attack
    //calculations then returns the attack value
    public int attackPlayer()
    {
        return 0;
    }
}
