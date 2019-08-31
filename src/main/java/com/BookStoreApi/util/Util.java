package com.BookStoreApi.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.util.List;

public class Util {
    private static ObjectMapper obMap = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    public static <T> T readValue(String jsonValue, Class<T> tClass) throws IOException {
        return obMap.readValue(jsonValue, tClass);
    }

    public static <T> T readValue(String jsonValue, TypeReference<List<T>> listClass) throws IOException {
        return obMap.readValue(jsonValue, listClass);
    }

    public static <T> T readValue(String jsonValue, List<T> listClass) throws IOException {
        return obMap.readValue(jsonValue, (TypeReference) listClass);
    }
}
