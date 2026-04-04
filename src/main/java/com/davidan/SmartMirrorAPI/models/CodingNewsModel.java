package com.davidan.SmartMirrorAPI.models;

import org.jsoup.nodes.Element;

import com.davidan.SmartMirrorAPI.kits.WebScrapeKitModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;

@Entity
public class CodingNewsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coding_news_id_seq")
    @SequenceGenerator(name = "coding_news_id_seq", sequenceName = "coding_news_id_seq", allocationSize = 1)
    int id;

    @Transient
    /**/private String HEADLINESURL = "https://news.ycombinator.com/front";
    @Transient
    /**/private String QUERY = "span[class='titleline']";
    @Transient
    /**/private String QUESTIONSURL = "https://news.ycombinator.com/ask";
    @Transient
    /**/private WebScrapeKitModel headlinesScraper;
    @Transient
    /**/private WebScrapeKitModel questionsScraper;
    @Column(name="headlines")
    private String[] headlines = new String[10];
    @Column(name="headline_links")
    private String[] headlineLinks = new String[10];
    @Column(name="questions")
    private String[] questions = new String[10];
    @Column(name="question_links")
    private String[] questionLinks = new String[10];

    public CodingNewsModel() {
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
        this.initialWork();
        Element[] headlines = headlinesScraper.getElementsByTag(QUERY);
        for (int i = 0; i < 10; i++) {
            this.headlineLinks[i] = headlines[i].select("a").attr("href");
            this.headlines[i] = headlines[i].text();
        }
    }


    private void initialWork() {
        headlinesScraper = new WebScrapeKitModel();
        questionsScraper = new WebScrapeKitModel();
        headlinesScraper.setURL(HEADLINESURL);
        questionsScraper.setURL(QUESTIONSURL);
        headlinesScraper.fetchDocument();
        questionsScraper.fetchDocument();
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
