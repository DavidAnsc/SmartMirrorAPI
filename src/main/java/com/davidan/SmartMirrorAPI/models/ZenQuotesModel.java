package com.davidan.SmartMirrorAPI.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.davidan.SmartMirrorAPI.kits.APIKit;
import com.davidan.SmartMirrorAPI.kits.XMLHandlerKit;

public class ZenQuotesModel {
    private String APIURL = "https://zenquotes.io/api/quotes";
    private String quote = "";
    private String author = "";

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

    private String[] allQuotes = new String[10];
    private String[] allAuthors = new String[10];

    private JSONArray jsonAry;

    public ZenQuotesModel() throws Exception{
        this.jsonAry = APIKit.getJSONArrayByIS(XMLHandlerKit.xmlAPIToIS(APIURL));
    }

    public void obtainData() {
        try {
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
