package com.example.dailyquest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class AddQuestActivity extends AppCompatActivity {

    private Spinner spinner;
    private static final String[] paths = {"Fitness", "Mental", "Health"};
    private Calendar startTime;
    private QuestCalendar questList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quest);
        questList = new QuestCalendar();

        startTime = Calendar.getInstance();

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddQuestActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment(startTime);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(startTime);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void finish(View v){
        Quest quest = new Quest();
        quest.setType((String) spinner.getSelectedItem());
        EditText desc = findViewById(R.id.descriptionText);
        quest.setDescription(desc.getText().toString());
        System.out.println(startTime.toString());
        quest.setSTime(startTime);
        EditText duration = findViewById(R.id.durationText);
        quest.setETime(Integer.parseInt(duration.getText().toString()));
        //questList.addQuest(quest);
        Intent intent = new Intent(getApplicationContext(), BasicActivity.class);
        intent.putExtra("Quest", quest);
        AddQuestActivity.this.startActivity(intent);
    }

}