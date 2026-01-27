package com.davidan.SmartMirrorAPI.models;

import org.jsoup.nodes.Element;

import com.davidan.SmartMirrorAPI.kits.WebScrapeKitModel;

public class CodingNewsModel {
    /**/private String HEADLINESURL = "https://news.ycombinator.com/front";
    /**/private String QUERY = "span[class='titleline']";
    /**/private String QUESTIONSURL = "https://news.ycombinator.com/ask";
    /**/private WebScrapeKitModel headlinesScraper;
    /**/private WebScrapeKitModel questionsScraper;
    private String[] headlines = new String[10];
    private String[] headlineLinks = new String[10];
    private String[] questions = new String[10];
    private String[] questionLinks = new String[10];

    private void initialWork() {
        headlinesScraper = new WebScrapeKitModel();
        questionsScraper = new WebScrapeKitModel();
        headlinesScraper.setURL(HEADLINESURL);
        questionsScraper.setURL(QUESTIONSURL);
        headlinesScraper.fetchDocument();
        questionsScraper.fetchDocument();
    }
    public CodingNewsModel() {
        this.initialWork();
    }
    public String[] getHeadlines() {
        return headlines;
    }
    public String[] getHeadlineLinks() {
        return headlineLinks;
    }
    public String[] getQuestions() {
        return questions;
    }
    public String[] getQuestionLinks() {
        return questionLinks;
    }

    private void obtainHeadlines() {
        Element[] headlines = headlinesScraper.getElementsByTag(QUERY);
        for (int i = 0; i < 10; i++) {
            this.headlineLinks[i] = "https://news.ycombinator.com/" + headlines[i].select("a").attr("href");
            this.headlines[i] = headlines[i].text();
        }
    }
    private void obtainQuestions() {
        Element[] questions = questionsScraper.getElementsByTag(QUERY);
        for (int i = 0; i < 10; i++) {
            this.questionLinks[i] = "https://news.ycombinator.com/" + questions[i].select("a").attr("href");
            this.questions[i] = questions[i].text();
        }
    }
    public void obtainData() {
        this.obtainHeadlines();
        this.obtainQuestions();
    }
}
