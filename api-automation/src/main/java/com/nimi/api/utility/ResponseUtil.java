package com.nimi.api.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseUtil {

    public static <T> T deserializeTo(String response, Class<T> toType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response, toType);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String responseAsString(Response response, String responseField) {
        JsonPath jsonPath = response.jsonPath();
        String responseString = jsonPath.getString(responseField);
        return responseString;
    }

    public static String extractJsonPathStringValue(Response response, String jsonPath){
        return response.body().jsonPath().getString(jsonPath);
    }
    public static String extractJsonHeaderValue(Response response, String headerName){
        return response.getHeader(headerName);
    }
}
