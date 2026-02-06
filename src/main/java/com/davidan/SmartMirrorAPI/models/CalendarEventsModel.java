package com.davidan.SmartMirrorAPI.models;

import com.davidan.SmartMirrorAPI.kits.CalendarFetcher;

public class CalendarEventsModel {
    /**/private CalendarFetcher calendarFetcher = CalendarFetcher.shared;
    private String[] eventNames = new String[10];
    private String[] eventTimes = new String[10];

    public String[] getEventNames() {
        return eventNames;
    }
    public String[] getEventTimes() {
        return eventTimes;
    }

    public void obtainData() {
        try {
            var data = calendarFetcher.obtainData();
            this.eventNames = data.getKey();
            this.eventTimes = data.getValue();
        } catch (Exception e) {
            System.out.println("CalendarEventsModel.obtainData().CalendarFetcher.fetch(): " + e.getMessage());
        }
    }
    public void fetchData() {
        try {
            var newData = calendarFetcher.fetchData();
            this.eventNames = newData.getKey();
            this.eventTimes = newData.getValue();
        } catch (Exception e) {
            System.out.println("CalendarEventsModel.fetchData().CalendarFetcher.fetch(): " + e.getMessage());
        }
    }
}
