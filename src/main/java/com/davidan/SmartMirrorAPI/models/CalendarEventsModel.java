package com.davidan.SmartMirrorAPI.models;

import com.davidan.SmartMirrorAPI.kits.CalendarFetcher;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;

@Entity
public class CalendarEventsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calendar_events_id_seq")
    @SequenceGenerator(name = "calendar_events_id_seq", sequenceName = "calendar_events_id_seq", allocationSize = 1)
    int id;

    @Transient
    /**/private CalendarFetcher calendarFetcher = CalendarFetcher.shared;
    @Transient
    private String[] eventNames = new String[10];
    @Transient
    private String[] eventTimes = new String[10];
    
    
    public CalendarEventsModel() {
    }

    public int getId() {
        return id;
    }
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
