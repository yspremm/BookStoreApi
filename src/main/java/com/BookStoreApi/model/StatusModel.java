package com.BookStoreApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class StatusModel implements Serializable {

    @JsonProperty("message")
    private String message;

    public StatusModel() { }

    public StatusModel(String message) {
        this.message = message;
    }

}
