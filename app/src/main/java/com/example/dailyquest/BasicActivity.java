package com.example.dailyquest;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A basic example of how to use week view library.
 * Created by Raquib-ul-Alam Kanak on 1/3/2014.
 * Website: http://alamkanak.github.io
 */
public class BasicActivity extends BaseActivity {




    public BasicActivity(){
    }


    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        for(Quest q:questList.getAllQuests()){
            Calendar endTime = (Calendar) q.getStartTime().clone();
            endTime.set(Calendar.MINUTE, q.getEndTime()+q.getStartTime().get(Calendar.MINUTE));
            WeekViewEvent event = new WeekViewEvent(q.getId(),q.getDescription(),q.getStartTime(),endTime);
            switch(q.getType()){
                case "Fitness":
                    event.setColor(getResources().getColor(R.color.event_color_02));
                    break;
                case "Mental":
                    event.setColor(getResources().getColor(R.color.event_color_01));
                    break;
                case "Health":
                    event.setColor(getResources().getColor(R.color.event_color_03));
            }
            if (eventMatches(event, newYear, newMonth)) {
                events.add(event);
            }

        }

        return events;
    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

}
