package com.fisTest.restUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class RestManager {

    public static Response get(Map<String, String> headers, String path){
        return RestAssured.given()
                .urlEncodingEnabled(false)
                .headers(headers)
                .relaxedHTTPSValidation()
                .get(path);
    }
}
