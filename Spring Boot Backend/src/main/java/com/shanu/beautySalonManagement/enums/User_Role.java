package com.shanu.beautySalonManagement.enums;


public enum User_Role {
    ADMIN("Admin"),
    CUSTOMER("Customer"),
    EXPERT("Expert");

    private String value;

    User_Role(String value) {
        this.value = value;
    }

}
