package com.web2.booking.enums;

public enum Role {

    ADMIN("ADMINISTRATOR"),
    ESTABLISHMENT("ESTABLISHMENT"),
    CUSTOMER("COSTUMER");

    private String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
