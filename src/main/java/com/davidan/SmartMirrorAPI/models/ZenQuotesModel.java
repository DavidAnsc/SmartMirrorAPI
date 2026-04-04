package com.davidan.SmartMirrorAPI.models;

import org.json.simple.JSONArray;
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
public class ZenQuotesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zen_quotes_id_seq")
    @SequenceGenerator(name = "zen_quotes_id_seq", sequenceName = "zen_quotes_id_seq", allocationSize = 1)
    int id;
    
    @Transient
    /**/private String APIURL = "https://zenquotes.io/api/quotes";
    @Transient
    /**/private JSONArray jsonAry;
    
    @Column(name="all_quotes")
    private String[] allQuotes = new String[10];
    @Column(name="all_authors")
    private String[] allAuthors = new String[10];
    
    @Column(name="quote")
    private String quote = "";
    @Column(name="author")
    private String author = "";
    
    public ZenQuotesModel() {   
    }

    public int getId() {
        return id;
    }
    public String getQuote() {
        return quote;
    }
    public String getAuthor() {
        return author;
    }
    public String[] getAllQuotes() {
        return allQuotes;
    }
    public String[] getAllAuthors() {
        return allAuthors;
    }


    public void obtainData() {
        try {
            this.jsonAry = APIKit.getJSONArrayByIS(XMLHandlerKit.xmlAPIToIS(APIURL));

            this.quote = APIKit.<String>getJSONToT((JSONObject) jsonAry.getFirst(), "q");
            for (int i = 0; i < 10; i++) {
                this.allQuotes[i] = APIKit.<String>getJSONToT((JSONObject) jsonAry.get(i), "q");
                this.allAuthors[i] = APIKit.<String>getJSONToT((JSONObject) jsonAry.get(i), "a");
            }
            this.author = APIKit.<String>getJSONToT((JSONObject) jsonAry.getFirst(), "a");
        } catch (Exception e) {
            System.out.println("ZenQuotesModel.obtainData(): " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            ZenQuotesModel zqm = new ZenQuotesModel();
            zqm.obtainData();
            System.out.println(zqm.getQuote() + " -" + zqm.getAuthor());
        } catch (Exception e) {
            System.out.println("ZenQuotesModel.main(): " + e.getMessage());
        }
    }
}
