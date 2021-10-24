package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.dto.PlayerDetails;
import com.wotos.wotosplayerservice.jsonao.PlayerJSONAO;
import com.wotos.wotosplayerservice.jsonao.PlayerDetailsJSONAO;
import com.wotos.wotosplayerservice.dto.PlayerStatistics;
import com.wotos.wotosplayerservice.dto.PlayerTankStatistics;
import com.wotos.wotosplayerservice.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerDetails> getPlayerDetailsByNickname(
            @PathParam("nickname") String nickname,
            @PathParam("playerID") Integer playerID
    ) {
        PlayerDetails playerDetails = userService.getPlayerDetails(playerID, nickname);

        return new ResponseEntity<>(playerDetails, HttpStatus.OK);
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<PlayerDetails> getPlayerDetailsByPlayerID(
//            @PathParam("playerID") Integer playerID
//    ) {
//        PlayerDetails playerDetails = userService.getPlayerDetailsByPlayerID(playerID);
//
//        return new ResponseEntity<>(playerDetails, HttpStatus.OK);
//    }

    @GetMapping("/stats/{playerID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerStatistics> getPlayerStatisticsByPlayerID(
            @PathVariable("playerID") Integer playerID
    ) {
        return null;
    }

    @GetMapping("/stats/{playerID}/{tankID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerTankStatistics> getPlayersTankStatisticsByPlayerID(
            @PathVariable("playerID") Integer playerID,
            @PathVariable("tankID") Integer tankID
    ) {
        return null;
    }

}
