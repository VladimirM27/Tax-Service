package com.motrechko.taxservice.model;

public enum Status {
    SUBMITTED("submitted"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
}

