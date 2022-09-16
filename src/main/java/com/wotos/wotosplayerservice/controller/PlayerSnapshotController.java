package com.wotos.wotosplayerservice.controller;

import com.wotos.wotosplayerservice.dao.PlayerSnapshot;
import com.wotos.wotosplayerservice.service.PlayerSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/player")
public class PlayerSnapshotController {

    @Autowired
    PlayerSnapshotService playerSnapshotService;

    @GetMapping("/snapshots")
    public Map<Integer, List<PlayerSnapshot>> getPlayerSnapshotsByAccountIds(
            @RequestParam("accountIds") Integer[] accountIds
    ) {
        return playerSnapshotService.getPlayerSnapshotsByAccountIds(accountIds);
    }

    @PostMapping("/snapshots")
    public Map<Integer, PlayerSnapshot> createPlayerSnapshotsByAccountIds(
            @RequestParam("accountIds") Integer[] accountIds
    ) {
        return playerSnapshotService.createPlayerSnapshotByAccountIds(accountIds);
    }

}
