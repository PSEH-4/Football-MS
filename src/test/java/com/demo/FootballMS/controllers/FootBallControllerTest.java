package com.demo.FootballMS.controllers;

import com.demo.FootballMS.models.Standing;
import com.demo.FootballMS.services.FootBallService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class FootBallControllerTest {

    @InjectMocks
    FootBallController controller;

    @Mock
    FootBallService service;

    @Test
    public void returns_standings_when_path_param_isPresent() {
        Standing standing = new Standing();
        standing.setCountryId(41);
        standing.setCountryName("England");
        standing.setLeagueId(148);
        standing.setLeagueName("Premier League");
        standing.setTeamId(2626);
        standing.setTeamName("Manchester City");
        Mockito.when(service.getFootBallStandingsByNames(anyString(), anyString(), anyString())).thenReturn(standing);
        assertEquals(new ResponseEntity(standing, HttpStatus.OK), controller.getFootballStandings("hi", "jj", "assdsd"));
    }

    @Test
    public void give404_No_Data_When_API_Calls_ResponseFilterationReturnNull() {
        Mockito.when(service.getFootBallStandingsByNames(anyString(), anyString(), anyString())).thenReturn(null);
        assertEquals(new ResponseEntity("No Data", HttpStatus.NOT_FOUND), controller.getFootballStandings("hi", "jj", "assdsd"));
    }
}
