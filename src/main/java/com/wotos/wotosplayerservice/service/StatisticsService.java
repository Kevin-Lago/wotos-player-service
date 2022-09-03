package com.wotos.wotosplayerservice.service;

import com.sun.istack.NotNull;
import com.wotos.wotosplayerservice.dao.ExpectedStatistics;
import com.wotos.wotosplayerservice.dao.PlayerStatisticsSnapshot;
import com.wotos.wotosplayerservice.dao.VehicleStatisticsSnapshot;
import com.wotos.wotosplayerservice.repo.ExpectedStatisticsRepository;
import com.wotos.wotosplayerservice.repo.VehicleStatisticsSnapshotsRepository;
import com.wotos.wotosplayerservice.util.feign.WotAccountsFeignClient;
import com.wotos.wotosplayerservice.util.feign.WotPlayerVehiclesFeignClient;
import com.wotos.wotosplayerservice.util.feign.XvmExpectedStatisticsFeignClient;
import com.wotos.wotosplayerservice.util.model.wot.statistics.PlayerStatistics;
import com.wotos.wotosplayerservice.util.model.wot.statistics.VehicleStatistics;
import com.wotos.wotosplayerservice.util.model.xvm.XvmExpectedStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class StatisticsService {

    @Value("${env.app_id}")
    private String APP_ID;
    @Value("${env.snapshot_rate}")
    private Integer SNAPSHOT_RATE;

    @Autowired
    WotPlayerVehiclesFeignClient wotPlayerVehiclesFeignClient;
    @Autowired
    WotAccountsFeignClient wotAccountsFeignClient;
    @Autowired
    XvmExpectedStatisticsFeignClient xvmExpectedStatisticsFeignClient;

    private final VehicleStatisticsSnapshotsRepository vehicleStatisticsSnapshotsRepository;
    private final ExpectedStatisticsRepository expectedStatisticsRepository;

    public StatisticsService(
            VehicleStatisticsSnapshotsRepository vehicleStatisticsSnapshotsRepository,
            ExpectedStatisticsRepository expectedStatisticsRepository
    ) {
        this.vehicleStatisticsSnapshotsRepository = vehicleStatisticsSnapshotsRepository;
        this.expectedStatisticsRepository = expectedStatisticsRepository;
    }

    @PostConstruct
    public void init() {
        List<ExpectedStatistics> expectedStatistics = expectedStatisticsRepository.findAll();

        // ToDo: Validate Expected Statistics is current/hasn't changed
        if (expectedStatistics.size() == 0) {
            saveExpectedStatistics();
        }
    }

    public ResponseEntity<Map<Integer, List<PlayerStatisticsSnapshot>>> getPlayerStatisticsSnapshots(List<Integer> accountIds) {
//        Map<Integer, List<PlayerStatisticsSnapshot>> playerStatisticsSnapshotsMap = new HashMap<>();
//
//        for (Integer accountId : accountIds) {
//            // ToDo: Determine how to return average wn8
//            PlayerStatistics playerStatistics = fetchPlayerStatistics(accountId);
//        }
        return null;
    }

    public ResponseEntity<Map<Integer, List<VehicleStatisticsSnapshot>>> getPlayerVehicleStatisticsSnapshots(List<Integer> accountIds, List<Integer> vehicleIds) {
        Map<Integer, List<VehicleStatisticsSnapshot>> vehicleStatisticsSnapshotsMap = new HashMap<>();

        for (Integer accountId : accountIds) {
            List<VehicleStatistics> vehicleStatistics = fetchVehicleStatistics(accountId, "", "", "", null, "", vehicleIds);

            for (VehicleStatistics vehicle : vehicleStatistics) {
                Integer maxBattles = vehicleStatisticsSnapshotsRepository.findHighestTotalBattlesByPlayerAndVehicleId(accountId, vehicle.getVehicleId()).orElse(0);

                // ToDo: Maybe implement initial save for vehicles with battles < SNAPSHOT_RATE
                if (vehicle.getAll().getBattles() - maxBattles > SNAPSHOT_RATE) {
                    ExpectedStatistics expectedStatistics = expectedStatisticsRepository.findById(vehicle.getVehicleId()).get();
                    vehicleStatisticsSnapshotsRepository.save(calculateVehicleStatisticsSnapshot(vehicle, expectedStatistics));
                }
            }

            List<VehicleStatisticsSnapshot> vehicleStatisticsSnapshots = vehicleStatisticsSnapshotsRepository.findByPlayerIdAndVehicleIdIn(
                    accountId, vehicleIds
            ).orElse(new ArrayList<>());
            vehicleStatisticsSnapshotsMap.put(accountId, vehicleStatisticsSnapshots);
        }

        return new ResponseEntity<>(vehicleStatisticsSnapshotsMap, HttpStatus.OK);
    }

    private VehicleStatisticsSnapshot calculateVehicleStatisticsSnapshot(
            @NotNull VehicleStatistics vehicleStatistics,
            @NotNull ExpectedStatistics expectedStatistics
    ) {
        float wins = vehicleStatistics.getAll().getWins();
        float battles = vehicleStatistics.getAll().getBattles();
        float survivedBattles = vehicleStatistics.getAll().getSurvivedBattles();
        float frags = vehicleStatistics.getAll().getFrags();
        float spotted = vehicleStatistics.getAll().getSpotted();
        float damage = vehicleStatistics.getAll().getDamageDealt();
        float damageTaken = vehicleStatistics.getAll().getDamageReceived();
        float defense = vehicleStatistics.getAll().getDroppedCapturePoints();
        float xp = vehicleStatistics.getAll().getXp();
        float hits = vehicleStatistics.getAll().getHits();
        float shots = vehicleStatistics.getAll().getShots();
        float stunAssistedDamage = vehicleStatistics.getAll().getStunAssistedDamage();
        float capturePoints = vehicleStatistics.getAll().getCapturePoints();
        float dropperCapturePoints = vehicleStatistics.getAll().getDroppedCapturePoints();

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
        float DEFENSE = Math.round(((defense / battles) / expectedStatistics.getExpectedDefense()) * tune) / tune;
        float WIN = Math.round((winLossRatio / expectedStatistics.getExpectedWinRate()) * (tune * 100)) / tune;

        float DAMAGEc = (float) Math.max(0, (DAMAGE - 0.22) / 0.78);
        float SPOTc = (float) Math.max(0, Math.min(DAMAGEc + 0.1, (SPOT - 0.38) / 0.62));
        float FRAGc = (float) Math.max(0, Math.min(DAMAGEc + 0.2, (FRAG - 0.12) / 0.88));
        float DEFENSEc = (float) Math.max(0, Math.min(DAMAGEc + 0.1, (DEFENSE - 0.10) / 0.9));
        float WINc = (float) Math.max(0, (WIN - 0.71) / 0.29);

        float wn8 = (float) ((980 * DAMAGEc) + (210 * DAMAGEc * FRAGc) + (155 * FRAGc * SPOTc) + (75 * DEFENSEc * FRAGc) + (145 * Math.min(1.8, WINc)));

        Integer vehicleId = vehicleStatistics.getVehicleId();
        Integer accountId = vehicleStatistics.getAccountId();

        return buildVehicleStatisticsSnapshot(
                accountId, vehicleId, wn8, (int) battles, killDeathRatio, hitMissRatio, winLossRatio,
                averageExperiencePerGame, averageDamagePerGame, averageKillsPerGame,
                averageDamageReceivedPerGame, averageShotsPerGame, averageStunAssistedDamage,
                averageCapturePointsPerGame, averageDroppedCapturePoints, (int) survivedBattles
        );
    }

    private VehicleStatisticsSnapshot buildVehicleStatisticsSnapshot(
            Integer accountId, Integer vehicleId, Float wn8, Integer battles, Float killDeathRatio, Float hitMissRatio, Float winLossRatio,
            Float averageExperiencePerGame, Float averageDamagePerGame, Float averageKillsPerGame,
            Float averageDamageReceivedPerGame, Float averageShotsPerGame, Float averageStunAssistedDamage,
            Float averageCapturePointsPerGame, Float averageDroppedCapturePoints, Integer survivedBattles
    ) {
        VehicleStatisticsSnapshot statisticsSnapshot = new VehicleStatisticsSnapshot();

        statisticsSnapshot.setPlayerId(accountId);
        statisticsSnapshot.setVehicleId(vehicleId);
        statisticsSnapshot.setTotalBattles(battles);
        statisticsSnapshot.setSurvivedBattles(survivedBattles);
        statisticsSnapshot.setAverageWn8(wn8);
        statisticsSnapshot.setKillDeathRatio(killDeathRatio);
        statisticsSnapshot.setHitMissRatio(hitMissRatio);
        statisticsSnapshot.setWinLossRatio(winLossRatio);
        statisticsSnapshot.setAverageExperience(averageExperiencePerGame);
        statisticsSnapshot.setAverageDamage(averageDamagePerGame);
        statisticsSnapshot.setAverageKills(averageKillsPerGame);
        statisticsSnapshot.setAverageDamageReceived(averageDamageReceivedPerGame);
        statisticsSnapshot.setAverageShots(averageShotsPerGame);
        statisticsSnapshot.setAverageStunAssistedDamage(averageStunAssistedDamage);
        statisticsSnapshot.setAverageCapturePoints(averageCapturePointsPerGame);
        statisticsSnapshot.setAverageDroppedCapturePoints(averageDroppedCapturePoints);

        return statisticsSnapshot;
    }

    private List<XvmExpectedStatistics> fetchExpectedStatistics() {
        return Objects.requireNonNull(
                xvmExpectedStatisticsFeignClient.getExpectedStatistics().getBody()
        ).getData();
    }

    private void saveExpectedStatistics() {
        List<XvmExpectedStatistics> xvmExpectedStatistics = fetchExpectedStatistics();

        xvmExpectedStatistics.forEach(xvm -> {
            ExpectedStatistics expectedStatistics = new ExpectedStatistics();

            expectedStatistics.setVehicleId(xvm.getVehicleId());
            expectedStatistics.setExpectedDefense(xvm.getExpectedDefense());
            expectedStatistics.setExpectedFrag(xvm.getExpectedFrag());
            expectedStatistics.setExpectedSpot(xvm.getExpectedSpot());
            expectedStatistics.setExpectedDamage(xvm.getExpectedDamage());
            expectedStatistics.setExpectedWinRate(xvm.getExpectedWinRate());

            expectedStatisticsRepository.save(expectedStatistics);
        });
    }

    private List<VehicleStatistics> fetchVehicleStatistics(
            Integer accountId, String accessToken, String extra,
            String fields, Integer inGarage, String language, List<Integer> vehicleIds
    ) {
        Integer[] array = new Integer[vehicleIds.size()];
        vehicleIds.toArray(array);

        return Objects.requireNonNull(
                wotPlayerVehiclesFeignClient.getPlayerVehicleStatistics(APP_ID, accountId, accessToken, extra, fields, inGarage, language, array).getBody()
        ).getData().get(accountId);
    }

    private PlayerStatistics fetchPlayerStatistics(
            Integer accountId
    ) {
        return Objects.requireNonNull(
                wotAccountsFeignClient.getPlayerDetails(APP_ID, "", "", "", "", accountId).getBody()
        ).getData().get(accountId).getStatistics();
    }

}
