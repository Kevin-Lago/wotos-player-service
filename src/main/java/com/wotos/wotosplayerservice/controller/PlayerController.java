package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

//    @GetMapping("achievements")
//    public List<T> getAcheivementsByAccountId(
//            @PathParam("accountId") Integer accountId
//    ) {
//        playerService.getPlayerAcheivements(accountId);
//    }

}
