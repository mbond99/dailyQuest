package com.example.dailyquest;

public class GameController {
    //variables
    private boolean defeated;
    private int level;
    private int round;

    //constructor
    public GameController()
    {
        defeated = false;
        level = 0;
        round = 0;
    }
    public GameController(boolean d, int l, int r)
    {
        defeated = d;
        level = l;
        round = r;
    }

    //
}
