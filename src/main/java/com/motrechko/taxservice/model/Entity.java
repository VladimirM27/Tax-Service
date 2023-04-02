package com.motrechko.taxservice.model;

public enum Entity {
    INDIVIDUAL("individual"),
    LEGAL("legal");

    private final String entity;

    Entity(String entity) {
        this.entity = entity;
    }

    public String getValue() {
        return entity;
    }
}
