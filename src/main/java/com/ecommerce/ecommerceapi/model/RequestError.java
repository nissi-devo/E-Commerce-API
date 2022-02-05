package com.ecommerce.ecommerceapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class RequestError {
    private String message;
    private String fieldName;
}
