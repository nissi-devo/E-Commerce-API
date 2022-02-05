package com.ecommerce.ecommerceapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Response<T> implements Serializable {

    private String code = "200";
    private String message = "Successful";
    private T data;
    private List<RequestError> errors;

    public Response(T data) {
        this.data = data;
    }

    public Response() {

    }

    public Response(String code, String description) {
        this.code = code;
        this.message = message;
    }

    public Boolean isSuccess() {
        if (code == "200") {
            return true;
        }
        return false;
    }
}

