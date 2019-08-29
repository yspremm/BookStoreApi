package com.BookStoreApi.api;

import com.BookStoreApi.model.response.GetBooksInfoResponse;
import com.BookStoreApi.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class BookStoreApi {

    private RestTemplate restTemplate;

    @Value("${spring.bookService.host}")
    private String host;

    @Value("${spring.bookService.endpoint.getInfo}")
    private String getInfo;

    @Autowired
    public BookStoreApi(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    public List<GetBooksInfoResponse> getBookInfo() throws Exception {
        String data = "{}";
        try {
            RequestEntity requestEntity = RequestEntity                //prepare
                    .get(URI.create(host + "/" + getInfo))
                    .build();

            System.err.println("Request: " + requestEntity.getMethod() + " Url: " + requestEntity.getUrl());
            ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class); //ยิงจริง

            if(response.getStatusCode().value() == 200){
                JSONArray resp = new JSONArray(response.getBody());
                data = resp.toString();
                System.out.println(resp);
//                JSONObject resp = new JSONObject(response.getBody());
//                JSONObject status = new JSONObject(resp.getString("status"));
//                if(status.getString("code").equals("0")){
//                    data = resp.get("data").toString();
//                }
            }
        } catch (final HttpClientErrorException httpClientErrorException) {
            System.err.println("httpClientErrorException: " + httpClientErrorException);
            throw new Exception("httpClientErrorException");
        } catch (HttpServerErrorException httpServerErrorException) {
            System.err.println("httpServerErrorException: " + httpServerErrorException);
            throw new Exception("httpServerErrorException");
        } catch (Exception exception) {
            System.err.println("exception: " + exception);
            throw exception;
        }

        return (List<GetBooksInfoResponse>) Util.readValue(data, new TypeReference<List<GetBooksInfoResponse>>(){} );

    }
}
