package com.web2.booking.enums;

public enum Category {

    RESTAURANT("RESTAURANT"),
    ACCOMMODATION("ACCOMMODATION"),
    TICKETS("TICKETS"),
    ACTIVITIES("ACTIVITIES"),
    PACKAGES("PACKAGES");

    private String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
