// src/main/java/com/shanu/beautySalonManagement/request/OurServiceCreateRequest.java
package com.shanu.beautySalonManagement.request;

import lombok.Data;

@Data
public class OurServiceCreateRequest {
    private String name;
    private String description;
    private double price;
}