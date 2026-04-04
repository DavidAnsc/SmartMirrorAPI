package com.davidan.SmartMirrorAPI.api_related.api_models;

import com.davidan.SmartMirrorAPI.models.*;


public class APIData {
    private TimeModel timeModel;
    private ZenQuotesModel zenQuotesModel;
    private MathQuestionsModel mathQuestionsModel;
    private CodingNewsModel codingNewsModel;
    private CalendarEventsModel calendarEventsModel;
    private WeatherDataModel weatherModel;

    
    public APIData() {
    }
    
    
    public WeatherDataModel getWeatherModel() {
        return weatherModel;
    }
    public void setWeatherModel(WeatherDataModel weatherModel) {
        this.weatherModel = weatherModel;
    }
    public TimeModel getTimeModel() {
        return timeModel;
    }
    public void setTimeModel(TimeModel timeModel) {
        this.timeModel = timeModel;
    }
    public ZenQuotesModel getZenQuotesModel() {
        return zenQuotesModel;
    }
    public void setZenQuotesModel(ZenQuotesModel zenQuotesModel) {
        this.zenQuotesModel = zenQuotesModel;
    }
    public MathQuestionsModel getMathQuestionsModel() {
        return mathQuestionsModel;
    }
    public void setMathQuestionsModel(MathQuestionsModel mathQuestionsModel) {
        this.mathQuestionsModel = mathQuestionsModel;
    }
    public CodingNewsModel getCodingNewsModel() {
        return codingNewsModel;
    }
    public void setCodingNewsModel(CodingNewsModel codingNewsModel) {
        this.codingNewsModel = codingNewsModel;
    }
    public CalendarEventsModel getCalendarEventsModel() {
        return calendarEventsModel;
    }
    public void setCalendarEventsModel(CalendarEventsModel calendarEventsModel) {
        this.calendarEventsModel = calendarEventsModel;
    }
}
