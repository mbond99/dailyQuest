package com.example.dailyquest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private Calendar startTime;

    public DatePickerFragment(Calendar c){
        startTime = c;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView textView = (TextView)getActivity().findViewById(R.id.dateText);
        textView.setText(""+(month+1)+"-"+day+"-"+year);
        startTime.set(Calendar.YEAR, year);
        startTime.set(Calendar.MONTH, month);
        startTime.set(Calendar.DAY_OF_MONTH, day);
    }
}