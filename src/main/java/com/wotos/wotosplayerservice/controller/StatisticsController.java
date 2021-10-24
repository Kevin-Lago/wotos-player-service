package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.dto.ExpectedStatistics;
import com.wotos.wotosplayerservice.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/expected")
    public ResponseEntity<List<ExpectedStatistics>> getExpectedStatistics() {
        List<ExpectedStatistics> expectedStatistics = statisticsService.fetchExpectedStatistics();

        return new ResponseEntity<>(expectedStatistics, HttpStatus.OK);
    }
}
