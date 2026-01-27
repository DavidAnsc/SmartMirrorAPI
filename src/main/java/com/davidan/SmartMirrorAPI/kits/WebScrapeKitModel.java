package com.davidan.SmartMirrorAPI.kits;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScrapeKitModel {
    private String url;
    private Document document;

    public void setURL(String url) {
        this.url = url;
    }
    public void fetchDocument() {
        try {
            this.document = Jsoup.connect(url).get();
        } catch (Exception e) {
            this.document = null;
            System.out.println("fetchDocument():Error fetching document: " + e.getMessage());
        }
    }
    public Element[] getElementsByTag(String tag) {
        if (this.document == null) {
            return null;
        }
        Elements elements = this.document.select(tag);
        return elements.toArray(new Element[elements.size()]);

    }
}
