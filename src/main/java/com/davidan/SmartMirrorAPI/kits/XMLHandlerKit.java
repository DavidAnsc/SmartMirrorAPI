package com.davidan.SmartMirrorAPI.kits;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class XMLHandlerKit {
    public static File xmlAPIToJSONFile(String targetPath, String url) throws Exception{
        URL urlObj = new URI(url).toURL();
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("API request failed with response code: " + connection.getResponseCode());
        }

        try (var inputStream = connection.getInputStream()) {
            File outputFile = new File(targetPath);
            Files.copy(inputStream, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return outputFile;
        }
    }

    public static InputStream xmlAPIToIS(String url) throws Exception {
        URL urlObj = new URI(url).toURL();

        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("API request failed with response code: " + connection.getResponseCode());
        }

        try (var inputStream = connection.getInputStream()) {
            byte[] data = inputStream.readAllBytes();
            return new ByteArrayInputStream(data);
        }
    }
}
