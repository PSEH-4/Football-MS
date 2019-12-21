package com.demo.FootballMS;

import com.demo.FootballMS.models.Country;
import com.demo.FootballMS.models.League;
import com.demo.FootballMS.models.Standing;
import com.demo.FootballMS.services.FootBallService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FootballMsApplicationTests {
    private static final String APIKEY = "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";

    @Test
    void contextLoads() {
    }

    @InjectMocks
    FootBallService footBallService;

    @Mock
    RestTemplate restTemplate;

    @Test
    public void testStanding_when_country_leauge_team_isKnown() {
        ParameterizedTypeReference<List<Country>> countryTypeRef = new ParameterizedTypeReference<List<Country>>() {
        };
        ParameterizedTypeReference<List<League>> leagueTypeRef = new ParameterizedTypeReference<List<League>>() {
        };
        ParameterizedTypeReference<List<Standing>> standingRef = new ParameterizedTypeReference<List<Standing>>() {
        };
        Country country = new Country(41, "England");
        League league = new League(country, 148, "Premier League");
        Standing standing = new Standing();
        standing.setCountryId(41);
        standing.setCountryName("England");
        standing.setLeagueId(148);
        standing.setLeagueName("Premier League");
        standing.setTeamId(2626);
        standing.setTeamName("Manchester City");
        ReflectionTestUtils.setField(footBallService, "url", "https://apiv2.apifootball.com");
        ReflectionTestUtils.setField(footBallService, "key", APIKEY);

        Mockito.when(restTemplate.exchange("https://apiv2.apifootball.com/?action=get_countries&APIkey=" + APIKEY,
                HttpMethod.GET, null, countryTypeRef))
                .thenReturn(new ResponseEntity(getDefaultCountryList(country), HttpStatus.OK));

        Mockito.when(restTemplate.exchange("https://apiv2.apifootball.com/?action=get_leagues&country_id=" + country.getId()
                + "&APIkey=" + APIKEY, HttpMethod.GET, null, leagueTypeRef))
                .thenReturn(new ResponseEntity(getDefaultLeaugeList(league), HttpStatus.OK));
        Mockito.when(restTemplate.exchange("https://apiv2.apifootball.com/?action=get_standings" + "&league_id=" + league.getId() + "&APIkey="
                + APIKEY, HttpMethod.GET, null, standingRef))
                .thenReturn(new ResponseEntity(getStandingList(standing), HttpStatus.OK));
        assertTrue(footBallService.getFootBallStandingsByNames("England", "Premier League", "Manchester City")
                != null);
        assertEquals(standing, footBallService.getFootBallStandingsByNames("England", "Premier League", "Manchester City"));
    }

    private List<Country> getDefaultCountryList(Country country) {
        List<Country> countryList = new ArrayList<>();
        countryList.add(country);
        return countryList;
    }

    private List<League> getDefaultLeaugeList(League league) {
        List<League> leagueList = new ArrayList<>();
        leagueList.add(league);
        return leagueList;
    }

    private List<Standing> getStandingList(Standing standing) {
        List<Standing> standings = new ArrayList<>();
        standings.add(standing);
        return standings;
    }
}
