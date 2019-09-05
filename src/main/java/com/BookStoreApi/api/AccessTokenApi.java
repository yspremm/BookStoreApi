package com.BookStoreApi.api;

import com.BookStoreApi.model.Users;
import com.BookStoreApi.model.response.AccessTokenResponse;
import com.BookStoreApi.model.response.GetBooksInfo;
import com.BookStoreApi.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.DatatypeConverter;
import java.net.URI;
import java.util.List;

@Component
public class AccessTokenApi {

    private RestTemplate restTemplate;

    @Value("${spring.oauth.url}")
    private String url;

    @Autowired
    public AccessTokenApi(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    public AccessTokenResponse getAccessToken(String username, String password) throws Exception {
        String data = "{}";
        try {
//            RequestEntity requestEntity = RequestEntity                //prepare
//                    .get(URI.create(url+username+"&password="+password))
//                    .build();

//            System.err.println("Request: " + requestEntity.getMethod() + " Url: " + requestEntity.getUrl());
            final String baseUrl = url+username+"&password="+password;
            String authorizationHeader = "Basic " + DatatypeConverter.printBase64Binary(("my-trusted-client:secret").getBytes());
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Authorization", authorizationHeader);
            requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
            ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, requestEntity, String.class);
//            ResponseEntity<String> response = restTemplate.exchange(requestEntity, HttpMethod.POST, String.class); //ยิงจริง
            if(response.getStatusCode().value() == 200){
                JSONObject resp = new JSONObject(response.getBody());
                data = resp.toString();
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

        return Util.readValue(data, AccessTokenResponse.class);

    }
}
