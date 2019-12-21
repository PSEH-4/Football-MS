package com.demo.FootballMS.controllers;

import com.demo.FootballMS.models.Standing;
import com.demo.FootballMS.services.FootBallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FootBallController {

    FootBallService footBallService;
    @Autowired
    FootBallController(final FootBallService footBallService){
        this.footBallService = footBallService;
    }
    @GetMapping("/standings/{countryName}/{leagueName}/{teamName}")
    public ResponseEntity getFootballStandings(@PathVariable("countryName") String countryName,
                                                         @PathVariable("leagueName") String leagueName,
                                                         @PathVariable("teamName") String teamName){
        Standing standing = footBallService.getFootBallStandingsByNames(countryName, leagueName, teamName);
        if(standing == null) {
            return new ResponseEntity("No Data Exists", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(standing, HttpStatus.OK);

    }
}
