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


/*        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTimex = (Calendar) startTime.clone();
        endTimex.add(Calendar.HOUR, 1);
        endTimex.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent eventx = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTimex);
        eventx.setColor(getResources().getColor(R.color.event_color_01));
        events.add(eventx);*/

        int count = 0;
        for(Quest q:questList.getAllQuests()){
            Calendar endTime = (Calendar) q.getStartTime().clone();
            endTime.set(Calendar.MINUTE, q.getEndTime()+q.getStartTime().get(Calendar.MINUTE));
            WeekViewEvent event = new WeekViewEvent(count,q.getDescription(),q.getStartTime(),endTime);
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
            events.add(event);
            count++;
        }


        return events;
    }

}
