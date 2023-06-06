package com.nimi.api.utility;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class JsonReaderUtil {

    public JsonReaderUtil(){

    }

    public static <T> T readFromJsonFile(String filePath, Class<T> request) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(Paths.get(filePath).toFile(), request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
