package com.example.dailyquest;
import java.awt.image.BufferedImage;

//not sure how to get image to work and need to hard code characters

public class Character {
    //variables
    private String characterClass;
    private image characterImage;
    private int strength;
    private int intelligance;
    private int dexterity;
    private int consitution;

    //constructors
    private Character()
    {
        characterClass = "";
        characterImage = "";
        strength = 0;
        intelligance = 0;
        dexterity = 0;
        consitution = 0;
    }
    private Character(String charClass, image charImage, int cStrength, int cIntelligance, int cDexterity, int cConsitution)
    {
        characterClass = charClass;
        characterImage = charImage;
        strength = cStrength;
        intelligance = cIntelligance;
        dexterity = cDexterity;
        consitution = cConsitution;
    }

    //getters and setters
    public void setCharacterClass(String cClass)
    {
        characterClass = cClass;
    }
    public void setCharacterImage(image cImage)
    {
        characterImage = cImage;
    }
    public void setStrength(int s)
    {
        strength = s;
    }
    public void setIntelligance(int i)
    {
        intelligance = i;
    }
    public void setDexterity(int d)
    {
        dexterity = d;
    }
    public void setConsitution(int c)
    {
        consitution = c;
    }
    public String getCharacterClass()
    {
        return characterClass;
    }
    public image getCharacterImage()
    {
        return characterImage;
    }
    public int getStrength()
    {
        return strength;
    }
    public int getIntelligance()
    {
        return intelligance;
    }
    public int getDexterity()
    {
        return dexterity;
    }
    public int getConsitution()
    {
        return consitution;
    }
}
