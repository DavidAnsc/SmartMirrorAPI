package com.davidan.SmartMirrorAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.davidan.SmartMirrorAPI.api_related.api_repos.CalendarRepo;
import com.davidan.SmartMirrorAPI.api_related.api_repos.CodingNewsRepo;
import com.davidan.SmartMirrorAPI.api_related.api_repos.MathQuestionsRepo;
import com.davidan.SmartMirrorAPI.api_related.api_repos.QuotesRepo;
import com.davidan.SmartMirrorAPI.api_related.api_repos.TimeRepo;
import com.davidan.SmartMirrorAPI.api_related.api_repos.WeatherRepo;

@SpringBootApplication
public class SmartMirrorApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmartMirrorApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(CalendarRepo calendarRepo, CodingNewsRepo codingNewsRepo, MathQuestionsRepo mathQuestionsRepo, QuotesRepo quotesRepo, WeatherRepo weatherRepo, TimeRepo timeRepo) {
		return args -> {
			final var one = new com.davidan.SmartMirrorAPI.models.CalendarEventsModel();
			one.obtainData();
			final var two = new com.davidan.SmartMirrorAPI.models.CodingNewsModel();
			two.obtainData();
			final var three = new com.davidan.SmartMirrorAPI.models.MathQuestionsModel();
			three.obtainData();
			final var four = new com.davidan.SmartMirrorAPI.models.ZenQuotesModel();
			four.obtainData();
			final var five = new com.davidan.SmartMirrorAPI.models.WeatherDataModel();
			five.obtainData();
			final var six = new com.davidan.SmartMirrorAPI.models.TimeModel();
			six.obtainData();
			calendarRepo.save(one);
			codingNewsRepo.save(two);
			mathQuestionsRepo.save(three);
			quotesRepo.save(four);
			weatherRepo.save(five);
			timeRepo.save(six);
		};
	}

}

