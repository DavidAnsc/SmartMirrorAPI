package com.davidan.SmartMirrorAPI.api_related.api_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidan.SmartMirrorAPI.api_related.api_models.APIData;
import com.davidan.SmartMirrorAPI.api_related.api_repos.CalendarRepo;
import com.davidan.SmartMirrorAPI.api_related.api_repos.CodingNewsRepo;
import com.davidan.SmartMirrorAPI.api_related.api_repos.MathQuestionsRepo;
import com.davidan.SmartMirrorAPI.api_related.api_repos.QuotesRepo;
import com.davidan.SmartMirrorAPI.api_related.api_repos.TimeRepo;
import com.davidan.SmartMirrorAPI.api_related.api_repos.WeatherRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private TimeRepo timeRepo;
    @Autowired
    private QuotesRepo quotesRepo;
    @Autowired
    private CodingNewsRepo codingNewsRepo;
    @Autowired
    private MathQuestionsRepo mathQuestionsRepo;
    @Autowired
    private CalendarRepo calendarRepo;
    @Autowired
    private WeatherRepo weatherRepo;

    @GetMapping
    public APIData getAllObjects() {
        var time = timeRepo.findAll().iterator().next();
        var quote = quotesRepo.findAll().iterator().next();
        var codingNews = codingNewsRepo.findAll().iterator().next();
        var mathQuestions = mathQuestionsRepo.findAll().iterator().next();
        var calendar = calendarRepo.findAll().iterator().next();
        var weather = weatherRepo.findAll().iterator().next();

        var apiData = new APIData();

        apiData.setTimeModel(time);
        apiData.setZenQuotesModel(quote);
        apiData.setCodingNewsModel(codingNews);
        apiData.setMathQuestionsModel(mathQuestions);
        apiData.setCalendarEventsModel(calendar);
        apiData.setWeatherModel(weather);
        
        return apiData;
    }

}
