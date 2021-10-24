package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.dto.PlayerDetails;
import com.wotos.wotosplayerservice.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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

}
