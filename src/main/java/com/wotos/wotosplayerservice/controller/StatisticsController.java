package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.dao.StatisticsSnapshot;
import com.wotos.wotosplayerservice.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/tank")
    public ResponseEntity<List<StatisticsSnapshot>> getTankStatisticsByPlayer(@PathParam("playerId") Integer playerId, @PathParam("tankId") Integer tankId) {
        List<StatisticsSnapshot> statisticsSnapshots = statisticsService.getPlayerTankStatistics(playerId, tankId);

        if (statisticsSnapshots.size() > 0) {
            return new ResponseEntity<>(statisticsSnapshots, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<StatisticsSnapshot>> getAllTankStatisticsByPlayer(@PathParam("playerId") Integer playerId) {
        List<StatisticsSnapshot> statisticsSnapshots = statisticsService.getAllPlayerTankStatistics(playerId);

        if (statisticsSnapshots.size() > 0) {
            return new ResponseEntity<>(statisticsSnapshots, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
