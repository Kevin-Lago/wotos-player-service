package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.dao.PlayerStatisticsSnapshot;
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

    @GetMapping("/vehicles")
    public ResponseEntity<Map<Integer, Map<Integer, Map<String, List<VehicleStatisticsSnapshot>>>>> getPlayerVehicleStatistics(
            @RequestParam("accountIds") List<Integer> accountIds,
            @RequestParam("vehicleIds") List<Integer> vehicleIds
    ) {
        return statisticsService.getPlayerVehicleStatisticsSnapshots(accountIds, vehicleIds);
    }

    @GetMapping("/players")
    public ResponseEntity<Map<Integer, List<PlayerStatisticsSnapshot>>> getPlayerStatistics(
            @RequestParam("accountIds") List<Integer> accountIds
    ) {
        return statisticsService.getPlayerStatisticsSnapshots(accountIds);
    }

}
