package com.wotos.wotosplayerservice.service;

import com.sun.istack.NotNull;
import com.wotos.wotosplayerservice.dao.ExpectedStatistics;
import com.wotos.wotosplayerservice.dao.VehicleStatisticsSnapshot;
import com.wotos.wotosplayerservice.repo.ExpectedStatisticsRepository;
import com.wotos.wotosplayerservice.repo.StatisticsSnapshotsRepository;
import com.wotos.wotosplayerservice.util.feign.WotPlayerVehiclesFeignClient;
import com.wotos.wotosplayerservice.util.feign.XvmExpectedStatisticsFeignClient;
import com.wotos.wotosplayerservice.util.model.wot.VehicleStatistics;
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
    XvmExpectedStatisticsFeignClient xvmExpectedStatisticsFeignClient;

    private final StatisticsSnapshotsRepository statisticsSnapshotsRepository;
    private final ExpectedStatisticsRepository expectedStatisticsRepository;

    public StatisticsService(
            StatisticsSnapshotsRepository statisticsSnapshotsRepository,
            ExpectedStatisticsRepository expectedStatisticsRepository
    ) {
        this.statisticsSnapshotsRepository = statisticsSnapshotsRepository;
        this.expectedStatisticsRepository = expectedStatisticsRepository;
    }

    @PostConstruct
    public void init() {
        List<ExpectedStatistics> expectedStatistics = expectedStatisticsRepository.findAll();

        if (expectedStatistics.size() == 0) {
            saveExpectedStatistics();
        }
    }

    public ResponseEntity<Map<Integer, List<VehicleStatisticsSnapshot>>> getPlayerTankStatistics(List<Integer> accountIds, List<Integer> tankIds) {
        Map<Integer, List<VehicleStatisticsSnapshot>> statisticsSnapshotsMap = new HashMap<>();

        for (Integer accountId : accountIds) {
            List<VehicleStatistics> vehicleStatistics = fetchTankStatistics(accountId, "", "", "", null, "", tankIds);

            for (VehicleStatistics vehicle : vehicleStatistics) {
                Integer maxBattles = statisticsSnapshotsRepository.findHighestTotalBattlesByPlayerAndTankId(accountId, vehicle.getTankId()).orElse(0);

                if (vehicle.getAll().getBattles() - maxBattles > SNAPSHOT_RATE) {
                    ExpectedStatistics expectedStatistics = expectedStatisticsRepository.getOne(vehicle.getTankId());
                    statisticsSnapshotsRepository.save(calculateTankStatisticsSnapshot(vehicle, expectedStatistics));
                }
            }

            List<VehicleStatisticsSnapshot> vehicleStatisticsSnapshots = statisticsSnapshotsRepository.findByPlayerIdAndTankIdIn(
                    accountId, tankIds
            ).orElse(new ArrayList<>());
            statisticsSnapshotsMap.put(accountId, vehicleStatisticsSnapshots);
        }

        return new ResponseEntity<>(statisticsSnapshotsMap, HttpStatus.OK);
    }

    private VehicleStatisticsSnapshot calculateTankStatisticsSnapshot(
            @NotNull VehicleStatistics tankStatistics,
            @NotNull ExpectedStatistics expectedStatistics
    ) {
        float wins = tankStatistics.getAll().getWins();
        float battles = tankStatistics.getAll().getBattles();
        float survivedBattles = tankStatistics.getAll().getSurvivedBattles();
        float frags = tankStatistics.getAll().getFrags();
        float spotted = tankStatistics.getAll().getSpotted();
        float damage = tankStatistics.getAll().getDamageDealt();
        float damageTaken = tankStatistics.getAll().getDamageReceived();
        float defense = tankStatistics.getAll().getDroppedCapturePoints();
        float xp = tankStatistics.getAll().getXp();
        float hits = tankStatistics.getAll().getHits();
        float shots = tankStatistics.getAll().getShots();
        float stunAssistedDamage = tankStatistics.getAll().getStunAssistedDamage();
        float capturePoints = tankStatistics.getAll().getCapturePoints();
        float dropperCapturePoints = tankStatistics.getAll().getDroppedCapturePoints();

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

        Integer tankId = tankStatistics.getTankId();
        Integer accountId = tankStatistics.getAccountId();

        return buildVehicleStatisticsSnapshot(
                accountId, tankId, wn8, (int) battles, killDeathRatio, hitMissRatio, winLossRatio,
                averageExperiencePerGame, averageDamagePerGame, averageKillsPerGame,
                averageDamageReceivedPerGame, averageShotsPerGame, averageStunAssistedDamage,
                averageCapturePointsPerGame, averageDroppedCapturePoints, (int) survivedBattles
        );
    }

    private VehicleStatisticsSnapshot buildVehicleStatisticsSnapshot(
            Integer accountId, Integer tankId, Float wn8, Integer battles, Float killDeathRatio, Float hitMissRatio, Float winLossRatio,
            Float averageExperiencePerGame, Float averageDamagePerGame, Float averageKillsPerGame,
            Float averageDamageReceivedPerGame, Float averageShotsPerGame, Float averageStunAssistedDamage,
            Float averageCapturePointsPerGame, Float averageDroppedCapturePoints, Integer survivedBattles
    ) {
        VehicleStatisticsSnapshot statisticsSnapshot = new VehicleStatisticsSnapshot();

        statisticsSnapshot.setPlayerId(accountId);
        statisticsSnapshot.setTankId(tankId);
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
        fetchExpectedStatistics().forEach(xvm -> {
            ExpectedStatistics expectedStatistics = new ExpectedStatistics();

            expectedStatistics.setTankId(xvm.getTankId());
            expectedStatistics.setExpectedDefense(xvm.getExpectedDefense());
            expectedStatistics.setExpectedFrag(xvm.getExpectedFrag());
            expectedStatistics.setExpectedSpot(xvm.getExpectedSpot());
            expectedStatistics.setExpectedDamage(xvm.getExpectedDamage());
            expectedStatistics.setExpectedWinRate(xvm.getExpectedWinRate());

            expectedStatisticsRepository.save(expectedStatistics);
        });
    }

    private List<VehicleStatistics> fetchTankStatistics(
            Integer accountId, String accessToken, String extra,
            String fields, Integer inGarage, String language, List<Integer> tankIds
    ) {
        Integer[] array = new Integer[tankIds.size()];
        tankIds.toArray(array);

        return Objects.requireNonNull(
                wotPlayerVehiclesFeignClient.getPlayerTankStatistics(APP_ID, accountId, accessToken, extra, fields, inGarage, language, array).getBody()
        ).getData().get(accountId);
    }

}
