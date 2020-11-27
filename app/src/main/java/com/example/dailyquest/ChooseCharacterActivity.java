package com.example.dailyquest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChooseCharacterActivity extends AppCompatActivity {

    private int ClassChoice = 0;

    // region Static class data

    public static final List<String> ClassNames = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("Mage");
                add("Cleric");
                add("Fighter");
                add("Rogue");
            }});

    public static final List<String> ClassDescriptions = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("The Mage is suggested for players who wish to improve mental health, study habits, projects, and etc.");
                add("The Cleric is suggested for players who wish to improve mental health, study habits, projects, and etc.");
                add("The Fighter is suggested for players who wish to improve physical health, stamina, handywork skills, and etc.");
                add("The Rogue is suggested for players who wish to improve physical health, stamina, handywork skills, and etc.");
            }});

    public static final List<Integer> MageStats = Collections.unmodifiableList(
            new ArrayList<Integer>() {{
                add(2);
                add(8);
                add(6);
                add(4);
            }});

    public static final List<Integer> ClericStats = Collections.unmodifiableList(
            new ArrayList<Integer>() {{
                add(2);
                add(8);
                add(6);
                add(4);
            }});

    public static final List<Integer> FighterStats = Collections.unmodifiableList(
            new ArrayList<Integer>() {{
                add(8);
                add(2);
                add(4);
                add(6);
            }});

    public static final List<Integer> RogueStats = Collections.unmodifiableList(
            new ArrayList<Integer>() {{
                add(8);
                add(2);
                add(4);
                add(6);
            }});

    public static final List<List<Integer>> ClassStats = Collections.unmodifiableList(
            new ArrayList<List<Integer>>() {{
                add(MageStats);
                add(ClericStats);
                add(FighterStats);
                add(RogueStats);
            }});

    public static final List<Integer> ClassImages = Collections.unmodifiableList(
            new ArrayList<Integer>() {{
                add(R.drawable.mage);
                add(R.drawable.cleric);
                add(R.drawable.fighter);
                add(R.drawable.rogue);
            }});

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_character);

        SetDisplayValues();
    }

    public void nextClass(View view) {
        ClassChoice++;
        while (ClassChoice >= 4){
            ClassChoice -= 4;
        }
        SetDisplayValues();
    }

    public void prevClass(View view) {
        ClassChoice--;
        while (ClassChoice < 0){
            ClassChoice += 4;
        }
        SetDisplayValues();
    }

    public void selectClass(View view) {
        // first check to make sure the user inputted a username:
        TextView playername = (TextView)findViewById(R.id.playerNameField);
        if (playername.getText().equals("Player Name") || playername.getText().length() < 1){
            // The player name was not inputted
            new AlertDialog.Builder(this)
                    .setTitle("Input player name")
                    .setMessage("Please input your name to continue!")
                    .setNegativeButton(android.R.string.ok, null)
                    .show();
        }
        else{
            // check to make sure the player is happy with their choice:
            new AlertDialog.Builder(this)
                    .setTitle("Confirmation")
                    .setMessage("Are you happy with both your class and name? \n!!!These can't be changed later!!!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // The player is happy. Save the new player data and then start the main menu
                            FinishStartup();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        }
    }

    private void FinishStartup(){
        // Grab all the player info:
        String playername, playerclass;
        int strStat, intStat, conStat, dexStat;

        TextView playerNameField = (TextView)findViewById(R.id.playerNameField);
        playername = String.valueOf(playerNameField.getText());
        playerclass = ClassNames.get(ClassChoice).toLowerCase();

        List<Integer> stats = ClassStats.get(ClassChoice);
        strStat = stats.get(0);
        intStat = stats.get(1);
        conStat = stats.get(2);
        dexStat = stats.get(3);

        // Save the player info to the database
        SQLiteConnection db = new SQLiteConnection(this);

        boolean success =  db.updatePlayer(playername, 1, 0, playerclass);
        System.out.println("Player update success: " + success);
        db.updateStat("strength", strStat);
        db.updateStat("intelligence", intStat);
        db.updateStat("constitution", conStat);
        db.updateStat("dexterity", dexStat);
        db.close();

        // Start the main menu
        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
        ChooseCharacterActivity.this.startActivity(intent);
    }

    private void SetDisplayValues(){
        TextView classnametext = (TextView)findViewById(R.id.className);
        TextView classdescriptiontext = (TextView)findViewById(R.id.classDescText);
        TextView strstattext = (TextView)findViewById(R.id.strengthStat);
        TextView intstattext = (TextView)findViewById(R.id.intStat);
        TextView conststattext = (TextView)findViewById(R.id.constStat);
        TextView dexstattext = (TextView)findViewById(R.id.dexStat);
        ImageView classimage = (ImageView)findViewById(R.id.classImg);

        classimage.setImageResource(ClassImages.get(ClassChoice));

        classnametext.setText(ClassNames.get(ClassChoice));
        classdescriptiontext.setText(ClassDescriptions.get(ClassChoice));

        List<Integer> stats = ClassStats.get(ClassChoice);

        strstattext.setText("Strength: " + stats.get(0));
        intstattext.setText("Intelligence: " + stats.get(1));
        conststattext.setText("Constitution: " + stats.get(2));
        dexstattext.setText("Dexterity: " + stats.get(3));
    }

    @Override
    public void onBackPressed() {
        // Disables the back button on this screen
    }
}
