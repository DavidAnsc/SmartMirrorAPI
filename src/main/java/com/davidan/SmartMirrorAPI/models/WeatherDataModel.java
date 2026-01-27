package com.davidan.SmartMirrorAPI.models;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import com.davidan.SmartMirrorAPI.kits.APIKit;
import com.davidan.SmartMirrorAPI.kits.XMLHandlerKit;

public class WeatherDataModel {
    /**/private String APIURL = "https://api.openweathermap.org/data/3.0/onecall?lat=43.48246288302679&lon=-80.5262673561612&appid=3029ae615ff51030029ab9eb85569067";
    /**/private JSONObject jsonObj;
    private Double[] minMaxTemp = new Double[2];
    private Long humidity;
    private Long precipitation;
    private Double actTemp;
    private Double feelsLike;
    private String description; // weather description

    private static Double kelvinToCelcius(Double kelvin) {
        return kelvin - 273.15;
    }
    public WeatherDataModel() throws Exception {
        this.jsonObj = APIKit.getJSONObjectByIS(XMLHandlerKit.xmlAPIToIS(APIURL));
    }
    public Double[] getMinMaxTemp() {
        return minMaxTemp;
    }
    public Long getHumidity() {
        return humidity;
    }
    public Long getPrecipitation() {
        return precipitation;
    }
    public Double getActTemp() {
        return actTemp;
    }
    public Double getFeelsLike() {
        return feelsLike;
    }
    public String getDescription() {
        return description;
    }

    public void obtainData() throws Exception {
        JSONObject currentRoot = APIKit.getJSONSubitem(jsonObj, "current");
        JSONArray dailyRoot = APIKit.getJSONSubarray(jsonObj, "daily");
        try {
            JSONObject snowRoot = APIKit.getJSONSubitem(currentRoot, "snow");
            JSONObject rainRoot = APIKit.getJSONSubitem(currentRoot, "rain");

            this.precipitation = APIKit.<Long>getJSONToT(snowRoot, "1h") + APIKit.<Long>getJSONToT(rainRoot, "1h");
        } catch (Exception e) {
            // No precipitation data
        }
        JSONObject tempRoot = APIKit.getJSONSubitem(dailyRoot, "temp");
        JSONArray weatherRoot = APIKit.getJSONSubarray(currentRoot, "weather");

        this.actTemp = kelvinToCelcius(APIKit.getJSONToT(currentRoot, "temp"));
        this.feelsLike = kelvinToCelcius(APIKit.<Double>getJSONToT(currentRoot, "feels_like"));
        this.humidity = APIKit.<Long>getJSONToT(currentRoot, "humidity");
        this.minMaxTemp[0] = kelvinToCelcius(APIKit.<Double>getJSONToT(tempRoot, "min"));
        this.minMaxTemp[1] = kelvinToCelcius(APIKit.<Double>getJSONToT(tempRoot, "max"));
        this.description = APIKit.<String>getJSONToT(weatherRoot, "description");

        roundData();
    }
    private void roundData() {
        this.minMaxTemp[0] = (double) Math.round(this.minMaxTemp[0] * 100) / 100;
        this.minMaxTemp[1] = (double) Math.round(this.minMaxTemp[1] * 100) / 100;
        this.feelsLike = (double) Math.round(this.feelsLike * 100) / 100;
        this.actTemp = (double) Math.round(this.actTemp * 100) / 100;
    }
}
