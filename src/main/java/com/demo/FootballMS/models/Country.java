package com.demo.FootballMS.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("country_id")
    private int id;
    @JsonProperty("country_name")
    private String name;

    public Country() {
    }

    public Country(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
