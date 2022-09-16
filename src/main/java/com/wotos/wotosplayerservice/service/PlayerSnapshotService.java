package com.wotos.wotosplayerservice.service;

import com.wotos.wotosplayerservice.dao.PlayerDetails;
import com.wotos.wotosplayerservice.dao.PlayerSnapshot;
import com.wotos.wotosplayerservice.repo.PlayerDetailsRepository;
import com.wotos.wotosplayerservice.repo.PlayerSnapshotsRepository;
import com.wotos.wotosplayerservice.util.feign.WotAccountsFeignClient;
import com.wotos.wotosplayerservice.util.model.wot.player.WotPlayerDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayerSnapshotService {

    @Value("${env.app_id}")
    private String APP_ID;

    private final WotAccountsFeignClient wotAccountsFeignClient;

    private final PlayerSnapshotsRepository playerSnapshotsRepository;
    private final PlayerDetailsRepository playerDetailsRepository;

    public PlayerSnapshotService(
            WotAccountsFeignClient wotAccountsFeignClient,

            PlayerDetailsRepository playerDetailsRepository,
            PlayerSnapshotsRepository playerSnapshotsRepository
    ) {
        this.wotAccountsFeignClient = wotAccountsFeignClient;

        this.playerDetailsRepository = playerDetailsRepository;
        this.playerSnapshotsRepository = playerSnapshotsRepository;
    }

    public Map<Integer, List<PlayerSnapshot>> getPlayerSnapshotsByAccountIds(Integer[] accountIds) {
        Map<Integer, List<PlayerSnapshot>> playerSnapshotsMap = new HashMap<>();

        for (Integer accountId : accountIds) {
            List<PlayerSnapshot> playerSnapshots = playerSnapshotsRepository.findByAccountId(accountId).orElse(null);

            playerSnapshotsMap.put(accountId, playerSnapshots);
        }

        return playerSnapshotsMap;
    }

    public Map<Integer, PlayerSnapshot> createPlayerSnapshotByAccountIds(Integer[] accountIds) {
        Map<Integer, PlayerSnapshot> playerSnapshotsMap = new HashMap<>();

        for (Integer accountId : accountIds) {
            PlayerDetails playerDetails = playerDetailsRepository.findByAccountId(accountId).get();
            PlayerSnapshot playerSnapshot = buildPlayerSnapshotFromPlayerDetails(playerDetails);

            playerSnapshotsMap.put(accountId, playerSnapshot);
            playerSnapshotsRepository.save(playerSnapshot);
        }

        return playerSnapshotsMap;
    }

    private PlayerSnapshot buildPlayerSnapshotFromPlayerDetails(PlayerDetails playerDetails) {
        PlayerSnapshot playerSnapshot = new PlayerSnapshot();

        playerSnapshot.setAccountId(playerDetails.getAccountId());
        playerSnapshot.setCreateTimestamp(Instant.now().getEpochSecond());
        playerSnapshot.setNickname(playerDetails.getNickname());
        playerSnapshot.setGlobalRating(playerDetails.getGlobalRating());
        playerSnapshot.setClanId(playerDetails.getClanId());

        return playerSnapshot;
    }

}
