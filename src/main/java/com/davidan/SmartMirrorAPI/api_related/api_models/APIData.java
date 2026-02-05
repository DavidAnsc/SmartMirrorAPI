package com.davidan.SmartMirrorAPI.api_related.api_models;

import com.davidan.SmartMirrorAPI.models.*;

public class APIData {
    TimeModel timeModel;
    ZenQuotesModel zenQuotesModel;
    WeatherDataModel weatherDataModel;
    MathQuestionsModel mathQuestionsModel;
    CodingNewsModel codingNewsModel;

    public APIData() throws Exception {
        timeModel = new TimeModel();
        zenQuotesModel = new ZenQuotesModel();
        weatherDataModel = new WeatherDataModel();
        codingNewsModel = new CodingNewsModel();
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



    public void obtainAllData() throws Exception {
        timeModel.obtainData();
        weatherDataModel.obtainData();
        zenQuotesModel.obtainData();
        codingNewsModel.obtainData();
    }
}
