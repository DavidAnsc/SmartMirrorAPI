package com.davidan.SmartMirrorAPI.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

import com.davidan.SmartMirrorAPI.kits.APIKit;
import com.davidan.SmartMirrorAPI.kits.XMLHandlerKit;

public class TimeModel {
    /**/DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    /**/DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**/private LocalDateTime TIME = LocalDateTime.now();
    /**/private String APIURL = "http://worldtimeapi.org/api/timezone/America/Toronto";
    private String time = TIME.format(TIMEFORMATTER);
    private String date = TIME.format(DATEFORMATTER);
    private String weekday;
    private String daysLeft; // of the year
    /**/private JSONObject jsonObj;

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
                modifiedMonth = "January";
                break;
            case 2:
                modifiedMonth = "February";
                break;
            case 3:
                modifiedMonth = "March";
                break;
            case 4:
                modifiedMonth = "April";
                break;
            case 5:
                modifiedMonth = "May";
                break;
            case 6:
                modifiedMonth = "June";
                break;
            case 7:
                modifiedMonth = "July";
                break;
            case 8:
                modifiedMonth = "August";
                break;
            case 9:
                modifiedMonth = "September";
                break;
            case 10:
                modifiedMonth = "October";
                break;
            case 11:
                modifiedMonth = "November";
                break;
            case 12:
                modifiedMonth = "December";
                break;
        }
        this.date = modifiedMonth + " " + modifiedDay + " " + this.date.substring(0, 4);
    }
    public TimeModel() throws Exception {
        formatTime();
        formatDate();
        this.jsonObj = APIKit.getJSONObjectByIS(XMLHandlerKit.xmlAPIToIS(APIURL));
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
        int dayOfTheYear = APIKit.<Long>getJSONToT(jsonObj, "day_of_year").intValue();
        int dayOfTheWeek = APIKit.<Long>getJSONToT(jsonObj, "day_of_week").intValue();

        this.daysLeft = String.valueOf(365 - dayOfTheYear);
        switch (dayOfTheWeek) {
            case 1:
                this.weekday = "Monday";
                break;
            case 2:
                this.weekday = "Tuesday";
                break;
            case 3:
                this.weekday = "Wednesday";
                break;
            case 4:
                this.weekday = "Thursday";
                break;
            case 5:
                this.weekday = "Friday";
                break;
            case 6:
                this.weekday = "Saturday";
                break;
            case 0:
                this.weekday = "Sunday";
                break;
        }

        this.daysLeft = String.valueOf(365 - dayOfTheYear);
    }
}