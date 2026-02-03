package com.davidan.SmartMirrorAPI.api_related.api_models;

import org.springframework.stereotype.Service;

@Service
public class APIDataService {
    private APIData apiData;

    public APIDataService() throws Exception {
        this.apiData = new APIData();
    }

    public APIData getAPIData() throws Exception {
        this.apiData.obtainAllData();
        return this.apiData;
    }
}
