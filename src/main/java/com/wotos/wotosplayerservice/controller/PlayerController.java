package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.dto.Player;
import com.wotos.wotosplayerservice.dto.PlayerPersonalData;
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
    PlayerService playerService;

    @GetMapping("/list")
    public ResponseEntity<List<Player>> getPlayerListByNickname(
            @PathParam("nickname") String nickname
    ) {
        List<Player> players = playerService.fetchListOfPlayersByNickname(nickname);

        return new ResponseEntity<>(players, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<PlayerPersonalData> getPlayerPersonalDataByPlayerId(
            @PathParam("playerID") Integer playerID
    ) {
        PlayerPersonalData playerPersonalData = playerService.fetchPlayerDetailsById(playerID);

        return new ResponseEntity<>(playerPersonalData, HttpStatus.OK);
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
