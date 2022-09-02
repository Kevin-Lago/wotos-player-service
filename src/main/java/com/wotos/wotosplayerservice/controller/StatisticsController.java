package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.dao.VehicleStatisticsSnapshot;
import com.wotos.wotosplayerservice.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<Map<Integer, List<VehicleStatisticsSnapshot>>> getPlayerTankStatistics(
            @RequestParam("playerIds") List<Integer> playerIds,
            @RequestParam("tankIds") List<Integer> tankIds
    ) {
        return statisticsService.getPlayerTankStatistics(playerIds, tankIds);
    }

}
