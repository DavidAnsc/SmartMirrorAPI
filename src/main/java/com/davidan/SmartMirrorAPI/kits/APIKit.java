package com.davidan.SmartMirrorAPI.kits;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class APIKit {

    public static Pair<URL, Integer> getURLData(String url) {
        try {
            URI uri = new URI(url);
            URL connectionURL = uri.toURL();
            HttpURLConnection connection =
                (HttpURLConnection) connectionURL.openConnection();

            connection.setRequestMethod("GET");
            connection.connect();

            return new Pair<URL, Integer>(
                connectionURL,
                connection.getResponseCode()
            );
        } catch (Exception e) {
            return new Pair<URL, Integer>(null, 404);
        }
    }

    public static JSONObject getJSONObjectByURLData(Pair<URL, Integer> pack)
        throws Exception {
        if (pack.getValue() != 200) {
            throw new RuntimeException("Http response: " + pack.getValue());
        }
        Scanner sc = new Scanner(pack.getKey().openStream());
        String contentString = "";

        while (sc.hasNext()) {
            contentString += sc.nextLine();
        }
        sc.close();

        JSONParser parser = new JSONParser();
        JSONObject jsonDataObject = (JSONObject) parser.parse(contentString);
        return jsonDataObject;
    }

    public static JSONObject getJSONObjectByIS(InputStream inputStream)
        throws Exception {
        Scanner sc = new Scanner(inputStream).useDelimiter("\\A");
        String contentString = "";

        while (sc.hasNext()) {
            contentString += sc.nextLine();
        }
        sc.close();

        JSONParser parser = new JSONParser();
        JSONObject jsonDataObject = (JSONObject) parser.parse(contentString);
        return jsonDataObject;
    }

    public static JSONArray getJSONArrayByIS(InputStream inputStream)
        throws Exception {
        Scanner sc = new Scanner(inputStream).useDelimiter("\\A");
        String contentString = "";

        while (sc.hasNext()) {
            contentString += sc.nextLine();
        }
        sc.close();

        JSONParser parser = new JSONParser();
        JSONArray jsonDataObject = (JSONArray) parser.parse(contentString);
        return jsonDataObject;
    }

    public static JSONObject getJSONSubitem(JSONObject jsonObj, String key)
        throws Exception {
        if (jsonObj.containsKey(key)) {
            return (JSONObject) jsonObj.get(key);
        } else {
            throw new Exception(
                "APIKit.getJSONSubitem: Key not found in JSON object: " + key
            );
        }
    }

    public static JSONObject getJSONSubitem(JSONArray jsonAry, String key)
        throws Exception {
        try {
            JSONObject temp = (JSONObject) jsonAry.get(0);
            return (JSONObject) temp.get(key);
        } catch (Exception e) {
            throw new Exception(
                "APIKit.getJSONSubitem: Key not found in JSON object: " + key
            );
        }
    }

    public static JSONArray getJSONSubarray(JSONObject jsonObj, String key)
        throws Exception {
        if (jsonObj.containsKey(key)) {
            return (JSONArray) jsonObj.get(key);
        } else {
            throw new Exception(
                "APIKit.getJSONSubarray: Key not found in JSON object: " + key
            );
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getJSONToT(JSONObject jsonObj, String key)
        throws Exception {
        if (jsonObj.containsKey(key)) {
            return (T) jsonObj.get(key);
        } else {
            throw new Exception(
                "APIKit.getJSONSubitem: Key not found in JSON object: " + key
            );
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getJSONToT(JSONArray jsonAry, String key)
        throws Exception {
        try {
            JSONObject temp = (JSONObject) jsonAry.get(0);
            return (T) temp.get(key);
        } catch (Exception e) {
            throw new Exception(
                "APIKit.getJSONSubitem: Key not found in JSON object: " + key
            );
        }
    }
}
