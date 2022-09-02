package com.wotos.wotosplayerservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import com.wotos.wotosplayerservice.dao.ExpectedStatistics;
import com.wotos.wotosplayerservice.dao.VehicleStatisticsSnapshot;
import com.wotos.wotosplayerservice.util.model.VehicleStatistics;
import com.wotos.wotosplayerservice.repo.ExpectedStatisticsRepository;
import com.wotos.wotosplayerservice.repo.StatisticsSnapshotsRepository;
import com.wotos.wotosplayerservice.util.feign.WotPlayerVehiclesFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${env.app_id}")
    private String APP_ID;
    @Value("${env.snapshot_rate}")
    private Integer SNAPSHOT_RATE;
    @Value("${env.urls.expected_statistics}")
    private String EXPECTED_STATISTICS_URL;
    @Value("${env.urls.wot_tank_statistics}")
    private String WOT_TANK_STATISTICS_URL;

    @Autowired
    WotPlayerVehiclesFeignClient wotPlayerVehiclesFeignClient;

    private final StatisticsSnapshotsRepository statisticsSnapshotsRepository;
    private final ExpectedStatisticsRepository expectedStatisticsRepository;

    public StatisticsService(
            StatisticsSnapshotsRepository statisticsSnapshotsRepository,
            ExpectedStatisticsRepository expectedStatisticsRepository
            ) {
        this.statisticsSnapshotsRepository = statisticsSnapshotsRepository;
        this.expectedStatisticsRepository = expectedStatisticsRepository;
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true
        );
    }

    @PostConstruct
    public void init() {
        List<ExpectedStatistics> expectedStatistics = expectedStatisticsRepository.findAll();

        if (expectedStatistics.size() == 0) {
            expectedStatistics = fetchExpectedStatistics();

            for (ExpectedStatistics expectedStatistic : expectedStatistics) {
                expectedStatisticsRepository.save(expectedStatistic);
            }
        }
    }

    private void generatePlayerTankStatistics(Integer accountId, Integer tankId) {
        VehicleStatistics tankStatistics = fetchTankStatisticsByPlayerAndTankId(accountId, tankId);
        assert tankStatistics != null;

        Optional<Integer> optionalTotalBattles = statisticsSnapshotsRepository.findHighestTotalBattlesByPlayerAndTankId(accountId, tankId);
        ExpectedStatistics expectedStatistics = expectedStatisticsRepository.findById(tankStatistics.getTankId()).orElse(null);
        assert expectedStatistics != null;

        VehicleStatisticsSnapshot statisticsSnapshot = calculateTankStatisticsSnapshot(tankStatistics, expectedStatistics);

        Integer totalBattles = optionalTotalBattles.orElseGet(() -> {
            statisticsSnapshotsRepository.save(statisticsSnapshot);

            return statisticsSnapshot.getTotalBattles();
        });

        if (tankStatistics.getAll().getBattles() - totalBattles >= SNAPSHOT_RATE) {
            statisticsSnapshotsRepository.save(statisticsSnapshot);
        }
    }

    public List<VehicleStatisticsSnapshot> getPlayerTankStatistics(Integer accountId, Integer tankId) {
        Optional<List<VehicleStatisticsSnapshot>> statisticsSnapshots = statisticsSnapshotsRepository.findAllStatisticsSnapshotsByPlayerIdAndTankId(accountId, tankId);

        return statisticsSnapshots.orElseGet(() -> {
            this.generatePlayerTankStatistics(accountId, tankId);

            return statisticsSnapshotsRepository.findAllStatisticsSnapshotsByPlayerId(accountId).orElse(null);
        });
    }

    private void generateAllPlayerTankStatistics(Integer accountId) {
        List<VehicleStatistics> tanksStatistics = fetchAllTankStatisticsByPlayerID(accountId.toString());

        tanksStatistics.forEach(tankStatistics -> {
            Optional<Integer> optionalTotalBattles = statisticsSnapshotsRepository.findHighestTotalBattlesByPlayerAndTankId(accountId, tankStatistics.getTankId());
            ExpectedStatistics expectedStatistics = expectedStatisticsRepository.findById(tankStatistics.getTankId()).orElse(null);
            assert expectedStatistics != null;

            VehicleStatisticsSnapshot statisticsSnapshot = calculateTankStatisticsSnapshot(tankStatistics, expectedStatistics);

            Integer totalBattles = optionalTotalBattles.orElseGet(() -> {
                statisticsSnapshotsRepository.save(statisticsSnapshot);

                return statisticsSnapshot.getTotalBattles();
            });

            if (tankStatistics.getAll().getBattles() - totalBattles >= SNAPSHOT_RATE) {
                statisticsSnapshotsRepository.save(statisticsSnapshot);
            }
        });
    }

    public List<VehicleStatisticsSnapshot> getAllPlayerTankStatistics(Integer accountId) {
        Optional<List<VehicleStatisticsSnapshot>> statisticsSnapshots = statisticsSnapshotsRepository.findAllStatisticsSnapshotsByPlayerId(accountId);

        // I'll need to calculate battle difference here
        return statisticsSnapshots.orElseGet(() -> {
            this.generateAllPlayerTankStatistics(accountId);

            return statisticsSnapshotsRepository.findAllStatisticsSnapshotsByPlayerId(accountId).orElse(null);
        });
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

        float DAMAGE = Math.round((averageDamagePerGame / expectedStatistics.getExpected_damage()) * tune) / tune;
        float SPOT = Math.round((averageSpottingPerGame / expectedStatistics.getExpected_spot()) * tune) / tune;
        float FRAG = Math.round((averageKillsPerGame / expectedStatistics.getExpected_frag()) * tune) / tune;
        float DEFENSE = Math.round(((defense / battles) / expectedStatistics.getExpected_defense()) * tune) / tune;
        float WIN = Math.round((winLossRatio / expectedStatistics.getExpected_win_rate()) * (tune * 100)) / tune;

        float DAMAGEc = (float) Math.max(0, (DAMAGE - 0.22) / 0.78);
        float SPOTc = (float) Math.max(0, Math.min(DAMAGEc + 0.1, (SPOT - 0.38) / 0.62));
        float FRAGc = (float) Math.max(0, Math.min(DAMAGEc + 0.2, (FRAG - 0.12) / 0.88));
        float DEFENSEc = (float) Math.max(0, Math.min(DAMAGEc + 0.1, (DEFENSE - 0.10) / 0.9));
        float WINc = (float) Math.max(0, (WIN - 0.71) / 0.29);

        float wn8 = (float) ((980 * DAMAGEc) + (210 * DAMAGEc * FRAGc) + (155 * FRAGc * SPOTc) + (75 * DEFENSEc * FRAGc) + (145 * Math.min(1.8, WINc)));

        Integer tankId = tankStatistics.getTankId();
        Integer accountId = tankStatistics.getAccountId();

        return buildTankStatisticsSnapshot(
                accountId, tankId, wn8, (int) battles, killDeathRatio, hitMissRatio, winLossRatio,
                averageExperiencePerGame, averageDamagePerGame, averageKillsPerGame,
                averageDamageReceivedPerGame, averageShotsPerGame, averageStunAssistedDamage,
                averageCapturePointsPerGame, averageDroppedCapturePoints, (int) survivedBattles
        );
    }

    private VehicleStatisticsSnapshot buildTankStatisticsSnapshot(
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

    private List<ExpectedStatistics> fetchExpectedStatistics() {
        String result = restTemplate.getForObject(EXPECTED_STATISTICS_URL, String.class);
        List<ExpectedStatistics> expectedStatistics = new ArrayList<>();

        try {
            JsonNode data = mapper.readTree(result).get("data");

            data.forEach(value -> {
                try {
                    ExpectedStatistics expectedStatistic = mapper.treeToValue(value, ExpectedStatistics.class);
                    expectedStatistics.add(expectedStatistic);
                } catch (JsonProcessingException e) {
                    System.out.println("Error parsing Expected Statistics with values: " + value.toString() + "\n" + Arrays.toString(e.getStackTrace()));
                }
            });

            return expectedStatistics;
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private List<VehicleStatistics> fetchAllTankStatisticsByPlayerID(String accountId) {
        String result = restTemplate.getForObject(WOT_TANK_STATISTICS_URL, String.class, APP_ID, "en", accountId, "");
//        String result = wotPlayerVehiclesFeignClient.getPlayerTankStatistics(
//                APP_ID, accountId, "", "", "", "", "en", ""
//        ).getBody();
        List<VehicleStatistics> tankStats = new ArrayList<>();

        try {
            JsonNode data = mapper.readTree(result).get("data").get(String.valueOf(accountId));

            data.forEach(value -> {
                try {
                    tankStats.add(mapper.treeToValue(value, VehicleStatistics.class));
                } catch (JsonProcessingException e) {
                    System.out.println("Error processing tank statistics JSON: " + e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.println("Error parsing fetched player data: " + e.getMessage());
        }

        return tankStats;
    }

    private VehicleStatistics fetchTankStatisticsByPlayerAndTankId(int accountId, int tankId) {
        String result = restTemplate.getForObject(WOT_TANK_STATISTICS_URL, String.class, APP_ID, "en", accountId, tankId);

        try {
            JsonNode data = mapper.readTree(result).get("data");
            JsonNode tankData = data.get(String.valueOf(accountId)).get(0);

            return mapper.treeToValue(tankData, VehicleStatistics.class);
        } catch (IOException e) {
            System.out.println("Error processing tank statistics with tankId: " + tankId + " and accountId: " + accountId + " " + e.getMessage());

            return null;
        } catch (NullPointerException e) {
            System.out.println("Tank data with tank id " + tankId + " does not exist for account id " + accountId);

            return null;
        }

    }

}
