package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.dto.ExpectedStatistics;
import com.wotos.wotosplayerservice.dto.PlayerStatisticsSnapshot;
import com.wotos.wotosplayerservice.dto.PlayerTankStatisticsSnapshot;
import com.wotos.wotosplayerservice.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/expected")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ExpectedStatistics>> getExpectedStatistics() {
        List<ExpectedStatistics> expectedStatistics = statisticsService.fetchExpectedStatistics();

        return new ResponseEntity<>(expectedStatistics, HttpStatus.OK);
    }

    @GetMapping("/statistics/{playerID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerStatisticsSnapshot> getLatestPlayerStatisticsByPlayerID(
            @PathVariable("playerID") Integer playerID
    ) {
        PlayerStatisticsSnapshot playerStatisticsSnapshot = statisticsService.getLastestPlayerStatisticsSnapshot();

        return new ResponseEntity<>(playerStatisticsSnapshot, HttpStatus.OK);
    }

    @GetMapping("/statistics/{playerID}/{tankID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerTankStatisticsSnapshot> getLatestPlayersTankStatisticsByPlayerID(
            @PathVariable("playerID") Integer playerID,
            @PathVariable("tankID") Integer tankID
    ) {
        return null;
    }

}
