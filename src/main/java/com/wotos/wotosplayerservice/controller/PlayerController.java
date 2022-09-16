package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.dao.PlayerDetails;
import com.wotos.wotosplayerservice.dao.PlayerAchievementsSnapshot;
import com.wotos.wotosplayerservice.service.PlayerService;
import com.wotos.wotosplayerservice.util.model.wot.player.WotPlayer;
import com.wotos.wotosplayerservice.validation.constraints.Language;
import com.wotos.wotosplayerservice.validation.constraints.PlayerSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/player")
@Validated
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/haveUpdated")
    public ResponseEntity<Map<Integer, Boolean>> havePlayersBeenUpdated(
            @RequestParam("accountIds") Integer[] accountIds
    ) {
        return new ResponseEntity<>(playerService.havePlayersBeenUpdated(accountIds), HttpStatus.OK);
    }

    @GetMapping
    public Map<Integer, PlayerDetails> getPlayersMapByAccountIds(
            @RequestParam(value = "accountIds") Integer[] accountIds
    ) {
        return playerService.getPlayersMapByAccountIds(accountIds);
    }

    @PostMapping
    public Map<Integer, PlayerDetails> createPlayersByAccountIds(
            @RequestParam(value = "accountIds") Integer[] accountIds
    ) {
        return playerService.createPlayersByAccountIds(accountIds);
    }

    @PutMapping
    public Map<Integer, PlayerDetails> updatePlayersByAccountIds(
            @RequestParam(value = "accountIds") Integer[] accountIds
    ) {
        return playerService.updatePlayersByAccountId(accountIds);
    }

    @GetMapping("/list")
    @PlayerSearch
    public List<WotPlayer> getPlayersByNickname(
            @RequestParam(value = "nicknames") String[] nicknames,
            @RequestParam(value = "language", required = false, defaultValue = "en") @Language String language,
            @RequestParam(value = "limit", required = false) @Max(100) Integer limit,
            @RequestParam(value = "searchType") String searchType
    ) {
        return playerService.getPlayersByNickname(nicknames, language, limit, searchType);
    }

    @GetMapping("/achievements")
    public Map<Integer, List<PlayerAchievementsSnapshot>> getPlayerAchievementsByAccountIds(
            @PathParam("accountId") Integer[] accountIds
    ) {
        return playerService.getPlayerAchievementsSnapshotsByAccountIds(accountIds);
    }

}
