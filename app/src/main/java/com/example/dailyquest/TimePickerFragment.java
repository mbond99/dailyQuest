package com.example.dailyquest;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private Calendar startTime;

    public TimePickerFragment(Calendar c) {
        startTime = c;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView)getActivity().findViewById(R.id.timeText);
        textView.setText(timeToString(hourOfDay,minute));
        startTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        startTime.set(Calendar.MINUTE, minute);
    }

    private String timeToString(int hour, int minute){
        String time ="";
        if(hour>12){
            time+=Integer.toString(hour-12);
            time+=":";
            time+=Integer.toString(minute);
            time+=(" pm");
        }
        else{
            time+=Integer.toString(hour);
            time+=":";
            time+=Integer.toString(minute);
            time+=(" am");
        }
        return time;
    }
}