package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.model.Player;
import com.wotos.wotosplayerservice.model.PlayerDetails;
import com.wotos.wotosplayerservice.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService userService;

    @GetMapping("/list/{nickname}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Player>> getPlayersByNickname(@PathVariable("nickname") String nickname) {
        return userService.getListOfPlayersByNickname(nickname);
    }

    @GetMapping("/{nickname}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerDetails> getPlayerIDByNickname(@PathVariable("nickname") String nickname) {
        return userService.getPlayerIDByNickname(nickname);
    }

//    @GetMapping("/list/{nickname}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Player> getPlayerListByNickname(@PathVariable("nickname") String nickname) {
//        return null;
//    }
//
//    @GetMapping("/{nickname}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Player> getPlayerDetailsByNickname(@PathVariable("nickname") String nickname) {
//        return null;
//    }

}
