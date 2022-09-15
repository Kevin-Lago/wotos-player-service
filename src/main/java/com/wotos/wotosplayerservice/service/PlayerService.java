package com.wotos.wotosplayerservice.service;

import com.wotos.wotosplayerservice.dao.Player;
import com.wotos.wotosplayerservice.dao.PlayerAchievementsSnapshot;
import com.wotos.wotosplayerservice.repo.PlayerAchievementsSnapshotRepository;
import com.wotos.wotosplayerservice.repo.PlayerRepository;
import com.wotos.wotosplayerservice.util.feign.WotAccountsFeignClient;
import com.wotos.wotosplayerservice.util.feign.WotPlayerVehiclesFeignClient;
import com.wotos.wotosplayerservice.util.model.wot.player.WotPlayer;
import com.wotos.wotosplayerservice.util.model.wot.player.WotPlayerDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlayerService {

    @Value("${env.app_id}")
    private String APP_ID;

    private final WotAccountsFeignClient wotAccountsFeignClient;
    private final WotPlayerVehiclesFeignClient playerVehiclesFeignClient;

    private final PlayerAchievementsSnapshotRepository playerAchievementsSnapshotRepository;
    private final PlayerRepository playerRepository;

    public PlayerService(
            WotAccountsFeignClient wotAccountsFeignClient,
            WotPlayerVehiclesFeignClient playerVehiclesFeignClient,

            PlayerAchievementsSnapshotRepository playerAchievementsSnapshotRepository,
            PlayerRepository playerRepository
    ) {
        this.wotAccountsFeignClient = wotAccountsFeignClient;
        this.playerVehiclesFeignClient = playerVehiclesFeignClient;

        this.playerAchievementsSnapshotRepository = playerAchievementsSnapshotRepository;
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayersByNickname(String nickname) {
        List<Player> players = playerRepository.findByNicknameContaining(nickname).orElseGet(null);

        return players;
    }

    public Player getPlayerByNickname(String nickname) {
        WotPlayer wotPlayer = fetchPlayerByNickname(nickname);
        Player player = playerRepository.findByNickname(nickname).orElseGet(() -> {
            Player newPlayer = new Player();

            newPlayer.setAccountId(wotPlayer.getAccountId());
            newPlayer.setNickname(wotPlayer.getNickname());

            return playerRepository.save(newPlayer);
        });

        return player;
    }

    public List<PlayerAchievementsSnapshot> getPlayerAchievementsByAccountId(Integer accountId) {
        List<PlayerAchievementsSnapshot> playerAchievementsSnapshots = playerAchievementsSnapshotRepository.findByAccountId(accountId).orElse(null);

        return playerAchievementsSnapshots;
    }

//    public PlayerAchievementsSnapshot getLatestPlayerAchievementsByAccountId(Integer accountId) {
//        PlayerAchievementsSnapshot playerAchievementsSnapshot = playerAchievementsSnapshotRepository.findLatestByAccountId(accountId).orElse(null);
//
//        return playerAchievementsSnapshot;
//    }

    private WotPlayerDetails fetchPlayerDetailsById(Integer accountId) {
        Map<Integer, WotPlayerDetails> wotPlayerDetails = wotAccountsFeignClient.getPlayerDetails(
                APP_ID, "", "", "-statistics,-private", "", accountId
        ).getBody().getData();

        return null;
    }

    private List<WotPlayer> fetchListOfPlayersByNickname(String nickname) {
        List<WotPlayer> wotPlayers = wotAccountsFeignClient.getPlayersByNickName(
                APP_ID, nickname, "", "100", "startswith"
        ).getBody().getData();

        return null;
    }

    private WotPlayer fetchPlayerByNickname(String nickname) {
        List<WotPlayer> wotPlayer = wotAccountsFeignClient.getPlayersByNickName(
                APP_ID, nickname, "", "1", "exact"
        ).getBody().getData();

        return wotPlayer.get(0);
    }

}
