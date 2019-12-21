package com.demo.FootballMS.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Standing implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty("country_id")
    private Integer countryId;
    @JsonProperty("country_name")

    private String countryName;
    @JsonProperty("league_id")

    private Integer leagueId;
    @JsonProperty("league_name")

    private String leagueName;
    @JsonProperty("team_key")
    private Integer teamId;
    @JsonProperty("team_name")
    private String teamName;
    @JsonProperty("overall_league_position")
    private Integer position;

    public Standing() {
    }

    public Standing(Integer countryId, String countryName, Integer leagueId, String leagueName, Integer teamId, String teamName, Integer position) {
        super();
        this.countryId = countryId;
        this.countryName = countryName;
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.teamId = teamId;
        this.teamName = teamName;
        this.position = position;
    }


    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

