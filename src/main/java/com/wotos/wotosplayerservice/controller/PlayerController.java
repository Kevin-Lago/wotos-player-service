package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.util.model.Player;
import com.wotos.wotosplayerservice.util.model.PlayerDetails;
import com.wotos.wotosplayerservice.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/list")
    public ResponseEntity<List<Player>> getPlayerListByNickname(
            @PathParam("nickname") String nickname
    ) {
        List<Player> players = playerService.fetchListOfPlayersByNickname(nickname);

        if (players.size() > 0) {
            return new ResponseEntity<>(players, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<PlayerDetails> getPlayerDetailsByPlayerId(
            @PathParam("playerId") String playerId
    ) {
        PlayerDetails playerDetails = playerService.fetchPlayerDetailsById(playerId);

        if (playerDetails != null) {
            return new ResponseEntity<>(playerDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/{nickname}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<PlayerPersonalData> getPlayerPersonalDataByNickname(
//            @PathVariable("nickname") String nickname
//    ) {
//        PlayerPersonalData playerPersonalData = playerService.fetchPlayerIDByNickname(nickname);
//
//        return null;
//    }

}
