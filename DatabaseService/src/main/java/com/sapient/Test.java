package com.sapient;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;

public class Test {
    public static void main(String[] args) {
        String jsonString = "{\"area\":\"Koramangala tes\",\"city\":\"1\",\"name\":\"Finox mall\"}";
        ObjectMapper mapper = new ObjectMapper();

        try {
            LinkedHashMap<String, String> map = mapper.readValue(jsonString, LinkedHashMap.class);
            System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
