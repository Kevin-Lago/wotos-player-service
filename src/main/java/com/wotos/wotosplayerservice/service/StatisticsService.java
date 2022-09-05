package com.wotos.wotosplayerservice.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import com.wotos.wotosplayerservice.dao.ExpectedStatistics;
import com.wotos.wotosplayerservice.dao.PlayerStatisticsSnapshot;
import com.wotos.wotosplayerservice.dao.VehicleStatisticsSnapshot;
import com.wotos.wotosplayerservice.repo.ExpectedStatisticsRepository;
import com.wotos.wotosplayerservice.repo.PlayerStatisticsSnapshotsRepository;
import com.wotos.wotosplayerservice.repo.VehicleStatisticsSnapshotsRepository;
import com.wotos.wotosplayerservice.util.feign.WotAccountsFeignClient;
import com.wotos.wotosplayerservice.util.feign.WotPlayerVehiclesFeignClient;
import com.wotos.wotosplayerservice.util.feign.XvmExpectedStatisticsFeignClient;
import com.wotos.wotosplayerservice.util.model.wot.player.WotPlayerDetails;
import com.wotos.wotosplayerservice.util.model.wot.statistics.WotPlayerStatistics;
import com.wotos.wotosplayerservice.util.model.wot.statistics.WotStatisticsByGameMode;
import com.wotos.wotosplayerservice.util.model.wot.statistics.WotVehicleStatistics;
import com.wotos.wotosplayerservice.util.model.xvm.XvmExpectedStatistics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticsService {

    @Value("${env.app_id}")
    private String APP_ID;
    @Value("${env.snapshot_rate}")
    private Integer SNAPSHOT_RATE;

    private final WotPlayerVehiclesFeignClient wotPlayerVehiclesFeignClient;
    private final WotAccountsFeignClient wotAccountsFeignClient;
    private final XvmExpectedStatisticsFeignClient xvmExpectedStatisticsFeignClient;

    private final VehicleStatisticsSnapshotsRepository vehicleStatisticsSnapshotsRepository;
    private final PlayerStatisticsSnapshotsRepository playerStatisticsSnapshotsRepository;
    private final ExpectedStatisticsRepository expectedStatisticsRepository;

    public StatisticsService(
            WotPlayerVehiclesFeignClient wotPlayerVehiclesFeignClient,
            WotAccountsFeignClient wotAccountsFeignClient,
            XvmExpectedStatisticsFeignClient xvmExpectedStatisticsFeignClient,
            VehicleStatisticsSnapshotsRepository vehicleStatisticsSnapshotsRepository,
            PlayerStatisticsSnapshotsRepository playerStatisticsSnapshotsRepository,
            ExpectedStatisticsRepository expectedStatisticsRepository
    ) {
        this.wotPlayerVehiclesFeignClient = wotPlayerVehiclesFeignClient;
        this.wotAccountsFeignClient = wotAccountsFeignClient;
        this.xvmExpectedStatisticsFeignClient = xvmExpectedStatisticsFeignClient;
        this.vehicleStatisticsSnapshotsRepository = vehicleStatisticsSnapshotsRepository;
        this.playerStatisticsSnapshotsRepository = playerStatisticsSnapshotsRepository;
        this.expectedStatisticsRepository = expectedStatisticsRepository;
    }

//    @PostConstruct
    public void init() {
        List<ExpectedStatistics> expectedStatistics = expectedStatisticsRepository.findAll();

        // ToDo: Validate Expected Statistics is current/hasn't changed
        if (expectedStatistics.size() == 0) {
            initExpectedStatistics();
        }
    }

//    private void getStatisticsByGameMode(String gameMode) {
//        // ToDo: map or figure out way to get game mode statistics before calculating OR Just go ahead and calculate statistics for every game mode.
//    }

    public ResponseEntity<Map<Integer, List<PlayerStatisticsSnapshot>>> getPlayerStatisticsSnapshots(List<Integer> accountIds) {
        // ToDo: Calculate recent wn8
        Map<Integer, List<PlayerStatisticsSnapshot>> playerStatisticsSnapshotsMap = new HashMap<>();

        for (Integer accountId : accountIds) {
            WotPlayerDetails wotPlayerDetails = fetchPlayerDetails(accountId);
            Integer maxBattles = playerStatisticsSnapshotsRepository.findHighestTotalBattlesByAccountId(accountId).orElse(0);

            // ToDo: Maybe implement initial save for players with battles < SNAPSHOT_RATE
            if (wotPlayerDetails.getStatistics().getAll().getBattles() - maxBattles > SNAPSHOT_RATE) {
                playerStatisticsSnapshotsRepository.save(calculatePlayerStatisticsSnapshot(wotPlayerDetails));
            }

            List<PlayerStatisticsSnapshot> playerStatisticsSnapshots = playerStatisticsSnapshotsRepository.findByAccountId(
                    accountId
            ).orElse(new ArrayList<>());
            playerStatisticsSnapshotsMap.put(accountId, playerStatisticsSnapshots);
        }

        return new ResponseEntity<>(playerStatisticsSnapshotsMap, HttpStatus.OK);
    }

    private static Map<String, WotStatisticsByGameMode> generatePlayerStatisticsByGameModeMap(WotPlayerStatistics wotPlayerStatistics) {
        Map<String, WotStatisticsByGameMode> vehicleStatisticsByGameModeMap = new HashMap<>();

        vehicleStatisticsByGameModeMap.put("regular_team", wotPlayerStatistics.getRegularTeam());
        vehicleStatisticsByGameModeMap.put("stronghold_skirmish", wotPlayerStatistics.getStrongholdSkirmish());
        vehicleStatisticsByGameModeMap.put("stronghold_defense", wotPlayerStatistics.getStrongholdDefense());
        vehicleStatisticsByGameModeMap.put("clan", wotPlayerStatistics.getClan());
        vehicleStatisticsByGameModeMap.put("all", wotPlayerStatistics.getAll());
        vehicleStatisticsByGameModeMap.put("company", wotPlayerStatistics.getCompany());
        vehicleStatisticsByGameModeMap.put("historical", wotPlayerStatistics.getHistorical());
//        vehicleStatisticsByGameModeMap.put("global_map", wotPlayerStatistics.getGlobalmap());
        vehicleStatisticsByGameModeMap.put("team", wotPlayerStatistics.getTeam());

        return vehicleStatisticsByGameModeMap;
    }

    private static PlayerStatisticsSnapshot calculatePlayerStatisticsSnapshot(WotPlayerDetails wotPlayerDetails) {
        float wins = wotPlayerDetails.getStatistics().getAll().getWins();
        float battles = wotPlayerDetails.getStatistics().getAll().getBattles();
        float survivedBattles = wotPlayerDetails.getStatistics().getAll().getSurvivedBattles();
        float frags = wotPlayerDetails.getStatistics().getAll().getFrags();
        float spotted = wotPlayerDetails.getStatistics().getAll().getSpotted();
        float damage = wotPlayerDetails.getStatistics().getAll().getDamageDealt();
        float damageTaken = wotPlayerDetails.getStatistics().getAll().getDamageReceived();
        float dropperCapturePoints = wotPlayerDetails.getStatistics().getAll().getDroppedCapturePoints();
        float xp = wotPlayerDetails.getStatistics().getAll().getXp();
        float hits = wotPlayerDetails.getStatistics().getAll().getHits();
        float shots = wotPlayerDetails.getStatistics().getAll().getShots();
        float stunAssistedDamage = wotPlayerDetails.getStatistics().getAll().getStunAssistedDamage();
        float capturePoints = wotPlayerDetails.getStatistics().getAll().getCapturePoints();

        float winLossRatio = wins / battles;
        float deaths = battles - survivedBattles == 0 ? 1 : battles - survivedBattles;
        float hitMissRatio = shots > 0 ? hits / shots : 0;
        float killDeathRatio = frags / deaths;
        float averageKillsPerGame = frags / battles;
        float averageSpottingPerGame = spotted / battles;
        float averageDamagePerGame = damage / battles;
        float averageExperiencePerGame = xp / battles;
        float averageDamageReceivedPerGame = damageTaken / battles;
        float averageShotsPerGame = shots / battles;
        float averageStunAssistedDamage = stunAssistedDamage / battles;
        float averageCapturePointsPerGame = capturePoints / battles;
        float averageDroppedCapturePoints = dropperCapturePoints / battles;

        // ToDo: Either fetch from DB and average most recent snapshots of each tank or calculate from scratch
        float averageWn8 = 0;

        Integer accountId = wotPlayerDetails.getAccountId();

        return buildPlayerStatisticsSnapshot(
                accountId, (int) battles, (int) survivedBattles, killDeathRatio, hitMissRatio,
                winLossRatio, averageWn8, averageExperiencePerGame, averageDamagePerGame,
                averageKillsPerGame, averageDamageReceivedPerGame, averageShotsPerGame,
                averageStunAssistedDamage, averageCapturePointsPerGame,
                averageDroppedCapturePoints, averageSpottingPerGame
        );
    }

    private static PlayerStatisticsSnapshot buildPlayerStatisticsSnapshot(
            Integer accountId, Integer totalBattles, Integer survivedBattles, Float killDeathRatio,
            Float hitMissRatio, Float winLossRatio, Float averageWn8,
            Float averageExperience, Float averageDamage, Float averageKills,
            Float averageDamageReceived, Float averageShots,
            Float averageStunAssistedDamage, Float averageCapturePoints,
            Float averageDroppedCapturePoints, Float averageSpottingPerGame
    ) {
        PlayerStatisticsSnapshot playerStatisticsSnapshot = new PlayerStatisticsSnapshot();

        playerStatisticsSnapshot.setAccountId(accountId);
        playerStatisticsSnapshot.setTotalBattles(totalBattles);
        playerStatisticsSnapshot.setSurvivedBattles(survivedBattles);
        playerStatisticsSnapshot.setKillDeathRatio(killDeathRatio);
        playerStatisticsSnapshot.setHitMissRatio(hitMissRatio);
        playerStatisticsSnapshot.setWinLossRatio(winLossRatio);
        playerStatisticsSnapshot.setAverageWn8(averageWn8);
        playerStatisticsSnapshot.setAverageExperience(averageExperience);
        playerStatisticsSnapshot.setAverageDamage(averageDamage);
        playerStatisticsSnapshot.setAverageKills(averageKills);
        playerStatisticsSnapshot.setAverageDamageReceived(averageDamageReceived);
        playerStatisticsSnapshot.setAverageShots(averageShots);
        playerStatisticsSnapshot.setAverageStunAssistedDamage(averageStunAssistedDamage);
        playerStatisticsSnapshot.setAverageCapturePoints(averageCapturePoints);
        playerStatisticsSnapshot.setAverageDroppedCapturePoints(averageDroppedCapturePoints);
        playerStatisticsSnapshot.setAverageSpotting(averageSpottingPerGame);

        return playerStatisticsSnapshot;
    }

    private WotPlayerDetails fetchPlayerDetails(
            Integer accountId
    ) {
        return Objects.requireNonNull(
                wotAccountsFeignClient.getPlayerDetails(APP_ID, "", "", "", "", accountId).getBody()
        ).getData().get(accountId);
    }

    // ToDo: Simplify
    public ResponseEntity<Map<Integer, Map<Integer, Map<String, List<VehicleStatisticsSnapshot>>>>> getPlayerVehicleStatisticsSnapshots(List<Integer> accountIds, List<Integer> vehicleIds) {
        Map<Integer, Map<Integer, Map<String, List<VehicleStatisticsSnapshot>>>> vehicleStatisticsSnapshotsByPlayer = new HashMap<>();

        for (Integer accountId : accountIds) {
            List<WotVehicleStatistics> wotVehicleStatisticsList = fetchVehicleStatistics(accountId, "", "", "", null, "", vehicleIds);
            Map<Integer, Map<String, List<VehicleStatisticsSnapshot>>> vehicleStatisticsSnapshotsMap = new HashMap<>();

            for (WotVehicleStatistics wotVehicleStatistics : wotVehicleStatisticsList) {
                Map<String, WotStatisticsByGameMode> wotStatisticsByGameModeMap = generateVehicleStatisticsByGameModeMap(wotVehicleStatistics);
                Map<String, List<VehicleStatisticsSnapshot>> vehicleStatisticsSnapshotsByGameModeMap = new HashMap<>();
                Integer vehicleId = wotVehicleStatistics.getVehicleId();

                wotStatisticsByGameModeMap.forEach((gameMode, wotStatisticsByGameMode) -> {
                    Integer maxBattles = vehicleStatisticsSnapshotsRepository.findHighestTotalBattlesByAccountIdAndVehicleId(accountId, vehicleId, gameMode).orElse(0);

                    if (wotStatisticsByGameMode.getBattles() - maxBattles > SNAPSHOT_RATE) {
                        ExpectedStatistics expectedStatistics = expectedStatisticsRepository.findById(vehicleId).get();

                        vehicleStatisticsSnapshotsRepository.save(calculateVehicleStatisticsSnapshot(
                                accountId, vehicleId, gameMode, wotStatisticsByGameMode, expectedStatistics
                        ));
                    }

                    List<VehicleStatisticsSnapshot> vehicleStatisticsSnapshotList = vehicleStatisticsSnapshotsRepository.findByAccountIdAndVehicleIdAndGameMode(accountId, vehicleId, gameMode).orElse(new ArrayList<>());
                    vehicleStatisticsSnapshotsByGameModeMap.put(gameMode, vehicleStatisticsSnapshotList);
                });

                vehicleStatisticsSnapshotsMap.put(vehicleId, vehicleStatisticsSnapshotsByGameModeMap);
            }

            vehicleStatisticsSnapshotsByPlayer.put(accountId, vehicleStatisticsSnapshotsMap);
        }

        return new ResponseEntity<>(vehicleStatisticsSnapshotsByPlayer, HttpStatus.OK);
    }

    private static Map<String, WotStatisticsByGameMode> generateVehicleStatisticsByGameModeMap(WotVehicleStatistics wotVehicleStatistics) {
        Map<String, WotStatisticsByGameMode> vehicleStatisticsByGameModeMap = new HashMap<>();

        vehicleStatisticsByGameModeMap.put("regular_team", wotVehicleStatistics.getRegularTeam());
        vehicleStatisticsByGameModeMap.put("stronghold_skirmish", wotVehicleStatistics.getStrongholdSkirmish());
        vehicleStatisticsByGameModeMap.put("stronghold_defense", wotVehicleStatistics.getStrongholdDefense());
        vehicleStatisticsByGameModeMap.put("clan", wotVehicleStatistics.getClan());
        vehicleStatisticsByGameModeMap.put("all", wotVehicleStatistics.getAll());
        vehicleStatisticsByGameModeMap.put("company", wotVehicleStatistics.getCompany());
//        vehicleStatisticsByGameModeMap.put("historical", wotVehicleStatistics.getHistorical());
        vehicleStatisticsByGameModeMap.put("global_map", wotVehicleStatistics.getGlobalmap());
        vehicleStatisticsByGameModeMap.put("team", wotVehicleStatistics.getTeam());

        return vehicleStatisticsByGameModeMap;
    }

    private static VehicleStatisticsSnapshot calculateVehicleStatisticsSnapshot(
            @NotNull Integer accountId, @NotNull Integer vehicleId, @NotNull String gameMode,
            @NotNull WotStatisticsByGameMode wotStatisticsByGameMode,
            @NotNull ExpectedStatistics expectedStatistics
    ) {
        float wins = wotStatisticsByGameMode.getWins();
        float battles = wotStatisticsByGameMode.getBattles();
        float survivedBattles = wotStatisticsByGameMode.getSurvivedBattles();
        float frags = wotStatisticsByGameMode.getFrags();
        float spotted = wotStatisticsByGameMode.getSpotted();
        float damage = wotStatisticsByGameMode.getDamageDealt();
        float damageTaken = wotStatisticsByGameMode.getDamageReceived();
        float dropperCapturePoints = wotStatisticsByGameMode.getDroppedCapturePoints();
        float xp = wotStatisticsByGameMode.getXp();
        float hits = wotStatisticsByGameMode.getHits();
        float shots = wotStatisticsByGameMode.getShots();
        float stunAssistedDamage = wotStatisticsByGameMode.getStunAssistedDamage();
        float capturePoints = wotStatisticsByGameMode.getCapturePoints();

        float winLossRatio = wins / battles;
        float deaths = battles - survivedBattles == 0 ? 1 : battles - survivedBattles;
        float hitMissRatio = shots > 0 ? hits / shots : 0;
        float killDeathRatio = frags / deaths;
        float averageKillsPerGame = frags / battles;
        float averageSpottingPerGame = spotted / battles;
        float averageDamagePerGame = damage / battles;
        float averageExperiencePerGame = xp / battles;
        float averageDamageReceivedPerGame = damageTaken / battles;
        float averageShotsPerGame = shots / battles;
        float averageStunAssistedDamage = stunAssistedDamage / battles;
        float averageCapturePointsPerGame = capturePoints / battles;
        float averageDroppedCapturePoints = dropperCapturePoints / battles;

        float tune = 10000;

        float DAMAGE = Math.round((averageDamagePerGame / expectedStatistics.getExpectedDamage()) * tune) / tune;
        float SPOT = Math.round((averageSpottingPerGame / expectedStatistics.getExpectedSpot()) * tune) / tune;
        float FRAG = Math.round((averageKillsPerGame / expectedStatistics.getExpectedFrag()) * tune) / tune;
        float DEFENSE = Math.round(((averageDroppedCapturePoints) / expectedStatistics.getExpectedDefense()) * tune) / tune;
        float WIN = Math.round((winLossRatio / expectedStatistics.getExpectedWinRate()) * (tune * 100)) / tune;

        float DAMAGEc = (float) Math.max(0, (DAMAGE - 0.22) / 0.78);
        float SPOTc = (float) Math.max(0, Math.min(DAMAGEc + 0.1, (SPOT - 0.38) / 0.62));
        float FRAGc = (float) Math.max(0, Math.min(DAMAGEc + 0.2, (FRAG - 0.12) / 0.88));
        float DEFENSEc = (float) Math.max(0, Math.min(DAMAGEc + 0.1, (DEFENSE - 0.10) / 0.9));
        float WINc = (float) Math.max(0, (WIN - 0.71) / 0.29);

        float wn8 = (float) ((980 * DAMAGEc) + (210 * DAMAGEc * FRAGc) + (155 * FRAGc * SPOTc) + (75 * DEFENSEc * FRAGc) + (145 * Math.min(1.8, WINc)));

        return buildVehicleStatisticsSnapshot(
                accountId, vehicleId, gameMode, wn8, (int) battles, killDeathRatio, hitMissRatio, winLossRatio,
                averageExperiencePerGame, averageDamagePerGame, averageKillsPerGame,
                averageDamageReceivedPerGame, averageShotsPerGame, averageStunAssistedDamage,
                averageCapturePointsPerGame, averageDroppedCapturePoints, (int) survivedBattles, averageSpottingPerGame
        );
    }

    private static VehicleStatisticsSnapshot buildVehicleStatisticsSnapshot(
            Integer accountId, Integer vehicleId, String gameMode, Float wn8, Integer battles, Float killDeathRatio, Float hitMissRatio, Float winLossRatio,
            Float averageExperiencePerGame, Float averageDamagePerGame, Float averageKillsPerGame,
            Float averageDamageReceivedPerGame, Float averageShotsPerGame, Float averageStunAssistedDamage,
            Float averageCapturePointsPerGame, Float averageDroppedCapturePoints, Integer survivedBattles, Float averageSpottingPerGame
    ) {
        VehicleStatisticsSnapshot vehicleStatisticsSnapshot = new VehicleStatisticsSnapshot();

        vehicleStatisticsSnapshot.setAccountId(accountId);
        vehicleStatisticsSnapshot.setVehicleId(vehicleId);
        vehicleStatisticsSnapshot.setGameMode(gameMode);
        vehicleStatisticsSnapshot.setTotalBattles(battles);
        vehicleStatisticsSnapshot.setCreateDate(new Date());
        vehicleStatisticsSnapshot.setSurvivedBattles(survivedBattles);
        vehicleStatisticsSnapshot.setAverageWn8(wn8);
        vehicleStatisticsSnapshot.setKillDeathRatio(killDeathRatio);
        vehicleStatisticsSnapshot.setHitMissRatio(hitMissRatio);
        vehicleStatisticsSnapshot.setWinLossRatio(winLossRatio);
        vehicleStatisticsSnapshot.setAverageExperience(averageExperiencePerGame);
        vehicleStatisticsSnapshot.setAverageDamage(averageDamagePerGame);
        vehicleStatisticsSnapshot.setAverageKills(averageKillsPerGame);
        vehicleStatisticsSnapshot.setAverageDamageReceived(averageDamageReceivedPerGame);
        vehicleStatisticsSnapshot.setAverageShots(averageShotsPerGame);
        vehicleStatisticsSnapshot.setAverageStunAssistedDamage(averageStunAssistedDamage);
        vehicleStatisticsSnapshot.setAverageCapturePoints(averageCapturePointsPerGame);
        vehicleStatisticsSnapshot.setAverageDroppedCapturePoints(averageDroppedCapturePoints);
        vehicleStatisticsSnapshot.setAverageSpotting(averageSpottingPerGame);

        return vehicleStatisticsSnapshot;
    }

    private List<WotVehicleStatistics> fetchVehicleStatistics(
            Integer accountId, String accessToken, String extra,
            String fields, Integer inGarage, String language, List<Integer> vehicleIds
    ) {
        Integer[] array = new Integer[vehicleIds.size()];
        vehicleIds.toArray(array);

        return Objects.requireNonNull(
                wotPlayerVehiclesFeignClient.getPlayerVehicleStatistics(APP_ID, accountId, accessToken, extra, fields, inGarage, language, array).getBody()
        ).getData().get(accountId);
    }

    private void initExpectedStatistics() {
        List<XvmExpectedStatistics> xvmExpectedStatistics = Objects.requireNonNull(
                xvmExpectedStatisticsFeignClient.getExpectedStatistics().getBody()
        ).getData();
        List<ExpectedStatistics> expectedStatistics = buildExpectedStatistics(xvmExpectedStatistics);

        expectedStatistics.forEach(expectedStatisticsRepository::save);
    }

    private static List<ExpectedStatistics> buildExpectedStatistics(List<XvmExpectedStatistics> xvmExpectedStatistics) {
        List<ExpectedStatistics> expectedStatisticsList = new ArrayList<>();

        xvmExpectedStatistics.forEach(xvm -> {
            ExpectedStatistics expectedStatistics = new ExpectedStatistics();

            expectedStatistics.setVehicleId(xvm.getVehicleId());
            expectedStatistics.setExpectedDefense(xvm.getExpectedDefense());
            expectedStatistics.setExpectedFrag(xvm.getExpectedFrag());
            expectedStatistics.setExpectedSpot(xvm.getExpectedSpot());
            expectedStatistics.setExpectedDamage(xvm.getExpectedDamage());
            expectedStatistics.setExpectedWinRate(xvm.getExpectedWinRate());

            expectedStatisticsList.add(expectedStatistics);
        });

        return expectedStatisticsList;
    }

}
