package com.davidan.SmartMirrorAPI.api_models;

import com.davidan.SmartMirrorAPI.models.*;

public class APIData {
    TimeModel timeModel;
    WeatherDataModel weatherDataModel;
    MathQuestionsModel mathQuestionsModel;
    CodingNewsModel codingNewsModel;

    public APIData() throws Exception {
        timeModel = new TimeModel();
        weatherDataModel = new WeatherDataModel();
        mathQuestionsModel = new MathQuestionsModel();
        codingNewsModel = new CodingNewsModel();
    }

    public void obtainAllData() throws Exception {
        timeModel.obtainData();
        weatherDataModel.obtainData();
        mathQuestionsModel.obtainData();
        codingNewsModel.obtainData();
    }
}
