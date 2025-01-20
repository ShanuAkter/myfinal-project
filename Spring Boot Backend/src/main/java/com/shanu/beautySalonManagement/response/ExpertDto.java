// ExpertDto.java
package com.shanu.beautySalonManagement.response;

import lombok.Data;

import java.util.List;

@Data
public class ExpertDto {
    private long id;
    private String speciality;
    private String phone;
    private int experienceYears;
    private List<Long> serviceIds; // Updated field
    private long userId;
}