package com.demo.FootballMS.services;

import com.demo.FootballMS.models.Country;
import com.demo.FootballMS.models.League;
import com.demo.FootballMS.models.Standing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootBallService {

    RestTemplate restTemplate;

    @Value("${key}")
    private String key;

    @Value("${url}")
    private String url;

    @Autowired
    FootBallService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private List<Country> getCountries() {
        String countryUrl = url + "/?action=get_" + "countries&APIkey=" + key;
        ParameterizedTypeReference<List<Country>> typeRef = new ParameterizedTypeReference<List<Country>>() {
        };
        ResponseEntity<List<Country>> responseEntity = restTemplate.exchange(countryUrl, HttpMethod.GET, null, typeRef);
        return responseEntity.getBody();
    }

    private List<League> getLeagues(final int countryId) {
        String leaugeUrl = url + "/?action=" + "get_leagues&country_id=" + countryId + "&APIkey="
                + key;
        ParameterizedTypeReference<List<League>> typeRef = new ParameterizedTypeReference<List<League>>() {
        };
        ResponseEntity<List<League>> responseEntity = restTemplate.exchange(leaugeUrl, HttpMethod.GET, null, typeRef);
        return responseEntity.getBody();
    }

    private List<Standing> getStandings(final int leagueId) {
        String standingUrl = url + "/?action=get_standings" + "&league_id=" + leagueId + "&APIkey="
                + key;
        ParameterizedTypeReference<List<Standing>> typeRef = new ParameterizedTypeReference<List<Standing>>() {
        };
        ResponseEntity<List<Standing>> responseEntity = restTemplate.exchange(standingUrl, HttpMethod.GET, null, typeRef);
        return responseEntity.getBody();
    }

    public Standing getFootBallStandingsByNames(final String countryName,
                                                final String leagueName,
                                                final String teamName) {
        List<Country> filteredCountryList = getCountries().stream().filter(e -> e.getName().equals(countryName))
                .collect(Collectors.toList());
        if (filteredCountryList.isEmpty()) {
            return null;
        }
        Country country = filteredCountryList.get(0);
        List<League> leagues = getLeagues(country.getId());
        List<League> filteredLeagueList = leagues.stream().filter(e -> e.getName().equals(leagueName))
                .collect(Collectors.toList());
        if (filteredLeagueList.isEmpty()) {
            return null;
        }
        League league = filteredLeagueList.get(0);
        List<Standing> standings = getStandings(league.getId());
        List<Standing> filteredStandingsList = standings.stream().filter(e -> e.getTeamName().equals(teamName))
                .filter(e -> e.getCountryName().equals(countryName)).filter(e -> e.getLeagueName().equals(leagueName))
                .collect(Collectors.toList());
        if (filteredStandingsList.isEmpty()) {
            return null;
        }
        Standing standing = filteredStandingsList.get(0);
        standing.setCountryId(country.getId());
        return standing;
    }

}
