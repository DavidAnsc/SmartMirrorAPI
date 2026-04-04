package com.davidan.SmartMirrorAPI.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

import com.davidan.SmartMirrorAPI.kits.APIKit;
import com.davidan.SmartMirrorAPI.kits.XMLHandlerKit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;

@Entity
public class TimeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "time_model_id_seq")
    @SequenceGenerator(name = "time_model_id_seq", sequenceName = "time_model_id_seq", allocationSize = 1)
    int id;
    
    @Transient
    /**/DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    @Transient
    /**/DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Transient
    /**/private LocalDateTime TIME;
    @Transient
    /**/private String APIURL = "http://worldtimeapi.org/api/timezone/America/Toronto";
    @Column(name="time")
    private String time;
    @Column(name="date")
    private String date;
    @Column(name="weekday")
    private String weekday;
    @Column(name="daysLeft")
    private String daysLeft; // of the year
    @Transient
    /**/private JSONObject jsonObj;

    public TimeModel() {
        this.TIME = LocalDateTime.now();
        this.time = TIME.format(TIMEFORMATTER);
        this.date = TIME.format(DATEFORMATTER);
        formatTime();
        formatDate();
    }
    
    
    public int getId() {
        return id;
    }
    public String getTime() {
        return time;
    }
    public String getDate() {
        return date;
    }
    public String getWeekday() {
        return weekday;
    }
    public String getDaysLeft() {
        return daysLeft;
    }

    public void obtainData() throws Exception {
        this.jsonObj = APIKit.getJSONObjectByIS(XMLHandlerKit.xmlAPIToIS(APIURL));
        int dayOfTheYear = APIKit.<Long>getJSONToT(jsonObj, "day_of_year").intValue();
        int dayOfTheWeek = APIKit.<Long>getJSONToT(jsonObj, "day_of_week").intValue();

        this.daysLeft = String.valueOf(365 - dayOfTheYear);
        switch (dayOfTheWeek) {
            case 1:
                this.weekday = "Mon";
                break;
            case 2:
                this.weekday = "Tue";
                break;
            case 3:
                this.weekday = "Wed";
                break;
            case 4:
                this.weekday = "Thu";
                break;
            case 5:
                this.weekday = "Fri";
                break;
            case 6:
                this.weekday = "Sat";
                break;
            case 0:
                this.weekday = "Sun";
                break;
        }

        this.daysLeft = String.valueOf(365 - dayOfTheYear);

    }


    private void formatTime() {
        String hour = this.time.substring(0, 2);
        
        if (Integer.parseInt(hour) >= 12) {
            hour = String.valueOf(Integer.parseInt(hour) - 12);
            this.time = hour + this.time.substring(2) + " PM";
        } else if (Integer.parseInt(hour) == 0) {
            hour = "12";
            this.time = hour + this.time.substring(2) + " AM";
        } else {
            this.time = hour + this.time.substring(2) + " AM";
        }
    }
    private void formatDate() {
        String modifiedMonth = "";
        String modifiedDay = "";
        int month = Integer.parseInt(this.date.substring(5, 7));
        int day = Integer.parseInt(this.date.substring(8, 10));
        if (day == 1) {
            modifiedDay = day + "st";
        } else if (day == 2) {
            modifiedDay = day + "nd";
        } else if (day == 3) {
            modifiedDay = day + "rd";
        } else if (day == 11 || day == 12 || day == 13) {
            modifiedDay = day + "th";
        } else {
            if (day % 10 == 1) {
                modifiedDay = day + "st";
            } else if (day % 10 == 2) {
                modifiedDay = day + "nd";
            } else if (day % 10 == 3) {
                modifiedDay = day + "rd";
            } else {
                modifiedDay = day + "th";
            }
        }
        switch (month) {
            case 1:
                modifiedMonth = "Jan";
                break;
            case 2:
                modifiedMonth = "Feb";
                break;
            case 3:
                modifiedMonth = "Mar";
                break;
            case 4:
                modifiedMonth = "Apr";
                break;
            case 5:
                modifiedMonth = "May";
                break;
            case 6:
                modifiedMonth = "Jun";
                break;
            case 7:
                modifiedMonth = "Jul";
                break;
            case 8:
                modifiedMonth = "Aug";
                break;
            case 9:
                modifiedMonth = "Sep";
                break;
            case 10:
                modifiedMonth = "Oct";
                break;
            case 11:
                modifiedMonth = "Nov";
                break;
            case 12:
                modifiedMonth = "Dec";
                break;
        }
        this.date = modifiedMonth + " " + modifiedDay;
    }
    
}