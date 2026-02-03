package com.davidan.SmartMirrorAPI.api_related.api_controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidan.SmartMirrorAPI.api_related.api_models.APIData;
import com.davidan.SmartMirrorAPI.api_related.api_models.APIDataService;

@RestController
public class APIDataController {
    private APIDataService apiDataService;

    @GetMapping("/api_data")
    public APIData returnServiceData() throws Exception {
        this.apiDataService = new APIDataService();
        return this.apiDataService.getAPIData();
    }
}
