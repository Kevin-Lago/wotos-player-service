package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.dao.Player;
import com.wotos.wotosplayerservice.dao.PlayerAchievementsSnapshot;
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

    @GetMapping
    public ResponseEntity<Player> getPlayerByNickname(
            @RequestParam("nickname") String nickname
    ) {
        return new ResponseEntity<>(playerService.getPlayerByNickname(nickname), HttpStatus.OK);
    }

    @GetMapping("/players")
    public List<Player> getPlayersByNickname(
            @RequestParam("nickname") String nickname
    ) {
        return playerService.getPlayersByNickname(nickname);
    }

    @GetMapping("/achievements")
    public List<PlayerAchievementsSnapshot> getPlayerAchievementsByAccountId(
            @PathParam("accountId") Integer accountId
    ) {
        return playerService.getPlayerAchievementsByAccountId(accountId);
    }

}
