package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.dao.PlayerAchievementsSnapshot;
import com.wotos.wotosplayerservice.service.PlayerAchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/player")
public class PlayerAchievementsController {

    @Autowired
    PlayerAchievementsService playerAchievementsService;

    @GetMapping("/achievements")
    public Map<Integer, List<PlayerAchievementsSnapshot>> getPlayerAchievementsByAccountIds(
            @RequestParam("accountIds") Integer[] accountIds
    ) {
        return playerAchievementsService.getPlayerAchievementsSnapshotsByAccountIds(accountIds);
    }

    @PostMapping("/achievements")
    public Map<Integer, PlayerAchievementsSnapshot> createPlayerAchievementsByAccountIds(
            @RequestParam("accountIds") Integer[] accountIds
    ) {
        return playerAchievementsService.createPlayerAchievementsSnapshotsByAccountIds(accountIds);
    }

}
