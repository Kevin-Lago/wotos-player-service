package com.wotos.wotosplayerservice.service;

import com.wotos.wotosplayerservice.dao.PlayerAchievementsSnapshot;
import com.wotos.wotosplayerservice.repo.PlayerAchievementsSnapshotRepository;
import com.wotos.wotosplayerservice.repo.PlayerDetailsRepository;
import com.wotos.wotosplayerservice.util.feign.WotAccountsFeignClient;
import com.wotos.wotosplayerservice.util.feign.WotPlayerVehiclesFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayerAchievementsService {

    @Value("${env.app_id}")
    private String APP_ID;

    private final WotAccountsFeignClient wotAccountsFeignClient;

    private final PlayerAchievementsSnapshotRepository playerAchievementsSnapshotRepository;

    public PlayerAchievementsService(
            WotAccountsFeignClient wotAccountsFeignClient,

            PlayerAchievementsSnapshotRepository playerAchievementsSnapshotRepository
    ) {
        this.wotAccountsFeignClient = wotAccountsFeignClient;

        this.playerAchievementsSnapshotRepository = playerAchievementsSnapshotRepository;
    }

    public Map<Integer, List<PlayerAchievementsSnapshot>> getPlayerAchievementsSnapshotsByAccountIds(Integer[] accountIds) {
        Map<Integer, List<PlayerAchievementsSnapshot>> playerAchievementsSnapshotsMap = new HashMap<>();

        for (Integer accountId : accountIds) {
            List<PlayerAchievementsSnapshot> playerAchievementsSnapshots = playerAchievementsSnapshotRepository.findByAccountId(accountId).orElse(null);

            playerAchievementsSnapshotsMap.put(accountId, playerAchievementsSnapshots);
        }

        return playerAchievementsSnapshotsMap;
    }

    // ToDo: This
    public Map<Integer, PlayerAchievementsSnapshot> createPlayerAchievementsSnapshotsByAccountIds(Integer[] accountIds) {
        Map<Integer, PlayerAchievementsSnapshot> playerAchievementsSnapshotsMap = new HashMap<>();

        for (Integer accountId : accountIds) {

        }

        return playerAchievementsSnapshotsMap;
    }

}
