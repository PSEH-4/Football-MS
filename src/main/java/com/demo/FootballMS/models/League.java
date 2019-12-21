package com.demo.FootballMS.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class League implements Serializable {
    private static final long serialVersionUID = 1L;

    private Country country;
    @JsonProperty("league_id")

    private Integer id;
    @JsonProperty("league_name")

    private String name;

    public League() {

    }
    public League(Country country, Integer id, String name) {
        super();
        this.country = country;
        this.id = id;
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
