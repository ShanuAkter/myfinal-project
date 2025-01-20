package com.shanu.beautySalonManagement.response;

import lombok.Data;

@Data
public class OurServiceDto {
    private long id;
    private String name;
    private String description;
    private double price;
    private byte[] serviceImage;
}
