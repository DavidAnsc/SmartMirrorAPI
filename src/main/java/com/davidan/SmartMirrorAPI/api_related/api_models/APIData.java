package com.davidan.SmartMirrorAPI.api_related.api_models;

import com.davidan.SmartMirrorAPI.models.*;

public class APIData {
    TimeModel timeModel;
    ZenQuotesModel zenQuotesModel;
    WeatherDataModel weatherDataModel;
    MathQuestionsModel mathQuestionsModel;
    CodingNewsModel codingNewsModel;
    CalendarEventsModel calendarEventsModel;

    public APIData() throws Exception {
        timeModel = new TimeModel();
        zenQuotesModel = new ZenQuotesModel();
        weatherDataModel = new WeatherDataModel();
        codingNewsModel = new CodingNewsModel();
        calendarEventsModel = new CalendarEventsModel();
    }


    public TimeModel getTimeModel() {
        return timeModel;
    }
    public WeatherDataModel getWeatherDataModel() {
        return weatherDataModel;
    }
    public ZenQuotesModel getZenQuotesModel() {
        return zenQuotesModel;
    }
    public CodingNewsModel getCodingNewsModel() {
        return codingNewsModel;
    }
    public CalendarEventsModel getCalendarEventsModel() {
        return calendarEventsModel;
    }



    public void obtainAllData() throws Exception {
        timeModel.obtainData();
        weatherDataModel.obtainData();
        zenQuotesModel.obtainData();
        codingNewsModel.obtainData();
        calendarEventsModel.obtainData();
    }

    public void fetchAllData() throws Exception {
        timeModel.obtainData();
        weatherDataModel.obtainData();
        zenQuotesModel.obtainData();
        codingNewsModel.obtainData();
        calendarEventsModel.fetchData();
    }
}
