package com.wotos.wotosplayerservice.service;

import com.wotos.wotosplayerservice.dao.ExpectedStatistics;
import com.wotos.wotosplayerservice.dao.PlayerStatisticsSnapshot;
import com.wotos.wotosplayerservice.dao.VehicleStatisticsSnapshot;
import com.wotos.wotosplayerservice.repo.ExpectedStatisticsRepository;
import com.wotos.wotosplayerservice.repo.PlayerStatisticsSnapshotsRepository;
import com.wotos.wotosplayerservice.repo.VehicleStatisticsSnapshotsRepository;
import com.wotos.wotosplayerservice.util.feign.WotAccountsFeignClient;
import com.wotos.wotosplayerservice.util.feign.WotPlayerVehiclesFeignClient;
import com.wotos.wotosplayerservice.util.feign.XvmExpectedStatisticsFeignClient;
import com.wotos.wotosplayerservice.util.model.wot.WotApiResponse;
import com.wotos.wotosplayerservice.util.model.wot.player.WotPlayerDetails;
import com.wotos.wotosplayerservice.util.model.wot.statistics.WotPlayerStatistics;
import com.wotos.wotosplayerservice.util.model.wot.statistics.WotStatisticsByGameMode;
import com.wotos.wotosplayerservice.util.model.wot.statistics.WotVehicleStatistics;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StatisticsServiceTest {

    private final Random rng = new Random();
    @Autowired
    @InjectMocks
    StatisticsService statisticsService;
    @Autowired
    @MockBean
    private VehicleStatisticsSnapshotsRepository vehicleStatisticsSnapshotsRepository;
    @Autowired
    @MockBean
    private PlayerStatisticsSnapshotsRepository playerStatisticsSnapshotsRepository;
    @Autowired
    @MockBean
    private ExpectedStatisticsRepository expectedStatisticsRepository;
    @Autowired
    @MockBean
    private WotAccountsFeignClient wotAccountsFeignClient;
    @Autowired
    @MockBean
    private WotPlayerVehiclesFeignClient wotPlayerVehiclesFeignClient;
    @Autowired
    @MockBean
    private XvmExpectedStatisticsFeignClient xvmExpectedStatisticsFeignClient;

    // ToDo: Make env variables better
    @Value("${env.snapshot_rate}")
    private Integer SNAPSHOT_RATE;
    @Value("${env.app_id}")
    private String APP_ID;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(statisticsService, "SNAPSHOT_RATE", SNAPSHOT_RATE);
        ReflectionTestUtils.setField(statisticsService, "APP_ID", APP_ID);
    }

    @Test
    public void getPlayerStatisticsSnapshotsTest() {
//        // ToDo: Assure calculations are correct rather than building with random values
//        Map<Integer, WotPlayerDetails> playerDetailsMap = new HashMap<>();
//        playerDetailsMap.put(1, buildRandomPlayerDetails(1, 11));
//
//        WotApiResponse<Map<Integer, WotPlayerDetails>> wotApiResponse = new WotApiResponse("", "", "", playerDetailsMap);
//
//        ResponseEntity<WotApiResponse<Map<Integer, WotPlayerDetails>>> wotResponseEntity = new ResponseEntity(wotApiResponse, HttpStatus.FOUND);
//        when(wotAccountsFeignClient.getPlayerDetails(
//                "", "", "", "", "", 1)
//        ).thenReturn(wotResponseEntity);
//
//        Optional<Integer> maxBattles = Optional.of(0);
//        when(playerStatisticsSnapshotsRepository.findHighestTotalBattlesByAccountId(1)).thenReturn(maxBattles);
//
//        List<PlayerStatisticsSnapshot> playerStatisticsSnapshotsList = new ArrayList<>();
//        PlayerStatisticsSnapshot playerStatisticsSnapshot = buildRandomPlayerStatisticsSnapshot(1);
//        playerStatisticsSnapshotsList.add(playerStatisticsSnapshot);
//        when(playerStatisticsSnapshotsRepository.save(any(PlayerStatisticsSnapshot.class))).thenReturn(playerStatisticsSnapshot);
//
//        when(playerStatisticsSnapshotsRepository.findByAccountId(1)).thenReturn(Optional.of(playerStatisticsSnapshotsList));
//
//        List<ExpectedStatistics> expectedStatistics = new ArrayList<>();
//        expectedStatistics.add(new ExpectedStatistics());
//        when(expectedStatisticsRepository.findAll()).thenReturn(expectedStatistics);
//
//        List<Integer> accountIds = new ArrayList<>();
//        accountIds.add(1);
//        statisticsService.getPlayerStatisticsSnapshots(accountIds);
//
//        verify(playerStatisticsSnapshotsRepository, times(1)).findHighestTotalBattlesByAccountId(1);
//        verify(playerStatisticsSnapshotsRepository, times(1)).save(any(PlayerStatisticsSnapshot.class));
//        verify(playerStatisticsSnapshotsRepository, times(1)).findByAccountId(1);
    }

    @Test
    public void getPlayerVehicleStatisticsSnapshotsTest() {
        // ToDo: Assure calculations are correct rather than building with random values
        Map<Integer, List<WotVehicleStatistics>> vehicleStatisticsMap = new HashMap<>();
        List<WotVehicleStatistics> wotVehicleStatistics = new ArrayList<>();
        wotVehicleStatistics.add(buildRandomVehicleStatistics(1, 11));
        wotVehicleStatistics.add(buildRandomVehicleStatistics(2, 11));
        wotVehicleStatistics.add(buildRandomVehicleStatistics(3, 11));
        vehicleStatisticsMap.put(1, wotVehicleStatistics);
        WotApiResponse<Map<Integer, List<WotVehicleStatistics>>> wotApiResponse = new WotApiResponse("", "", "", vehicleStatisticsMap);
        ResponseEntity<WotApiResponse<Map<Integer, List<WotVehicleStatistics>>>> wotResponseEntity = new ResponseEntity(wotApiResponse, HttpStatus.FOUND);
        Integer[] tankIdArray = {1, 2, 3};
        when(wotPlayerVehiclesFeignClient.getPlayerVehicleStatistics(
                "", 1, "", "", "", null, "", tankIdArray
                )).thenReturn(wotResponseEntity);

        Optional<Integer> maxBattles = Optional.of(0);
//        when(vehicleStatisticsSnapshotsRepository.findHighestTotalBattlesByAccountIdAndVehicleId(1, 1)).thenReturn(maxBattles);

        when(expectedStatisticsRepository.findById(1)).thenReturn(
                Optional.of(buildExpectedStatistics(1, .5f, .5f, .5f, .5f, .5f))
        );
        when(expectedStatisticsRepository.findById(2)).thenReturn(
                Optional.of(buildExpectedStatistics(2, .5f, .5f, .5f, .5f, .5f))
        );
        when(expectedStatisticsRepository.findById(3)).thenReturn(
                Optional.of(buildExpectedStatistics(3, .5f, .5f, .5f, .5f, .5f))
        );

        List<VehicleStatisticsSnapshot> vehicleStatisticsSnapshotList = new ArrayList<>();
        List<Integer> tankIdList = new ArrayList<>();
        tankIdList.add(1);
        tankIdList.add(2);
        tankIdList.add(3);
        VehicleStatisticsSnapshot vehicleStatisticsSnapshot1 = buildVehicleStatisticSnapshot(
                1, 1, 11,1,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f
        );
        VehicleStatisticsSnapshot vehicleStatisticsSnapshot2 = buildVehicleStatisticSnapshot(
                1, 2, 11,1,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f
        );
        VehicleStatisticsSnapshot vehicleStatisticsSnapshot3 = buildVehicleStatisticSnapshot(
                1, 3, 11,1,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f
        );
        vehicleStatisticsSnapshotList.add(vehicleStatisticsSnapshot1);
        vehicleStatisticsSnapshotList.add(vehicleStatisticsSnapshot2);
        vehicleStatisticsSnapshotList.add(vehicleStatisticsSnapshot3);

        List<Integer> accountIds = new ArrayList<>();
        accountIds.add(1);
        statisticsService.getPlayerVehicleStatisticsSnapshots(accountIds, tankIdList);

        when(vehicleStatisticsSnapshotsRepository.save(vehicleStatisticsSnapshot1)).thenReturn(vehicleStatisticsSnapshot1);
        when(vehicleStatisticsSnapshotsRepository.save(vehicleStatisticsSnapshot2)).thenReturn(vehicleStatisticsSnapshot2);
        when(vehicleStatisticsSnapshotsRepository.save(vehicleStatisticsSnapshot3)).thenReturn(vehicleStatisticsSnapshot3);

        when(vehicleStatisticsSnapshotsRepository.findByAccountIdAndVehicleIdIn(1, tankIdList)).thenReturn(Optional.of(vehicleStatisticsSnapshotList));

//        verify(vehicleStatisticsSnapshotsRepository, times(1)).findHighestTotalBattlesByAccountIdAndVehicleId(1, 1);
//        verify(vehicleStatisticsSnapshotsRepository, times(1)).findHighestTotalBattlesByAccountIdAndVehicleId(1, 2);
//        verify(vehicleStatisticsSnapshotsRepository, times(1)).findHighestTotalBattlesByAccountIdAndVehicleId(1, 3);
//        verify(vehicleStatisticsSnapshotsRepository, times(3)).save(any(VehicleStatisticsSnapshot.class));
    }

    private ExpectedStatistics buildExpectedStatistics(
            Integer vehicleId, Float expectedDefense, Float expectedFrag,
            Float expectedSpot, Float expectedDamage, Float expectedWinRate
    ) {
        ExpectedStatistics expectedStatistics = new ExpectedStatistics();

        expectedStatistics.setVehicleId(vehicleId);
        expectedStatistics.setExpectedDefense(expectedDefense);
        expectedStatistics.setExpectedFrag(expectedFrag);
        expectedStatistics.setExpectedSpot(expectedSpot);
        expectedStatistics.setExpectedDamage(expectedDamage);
        expectedStatistics.setExpectedWinRate(expectedWinRate);

        return expectedStatistics;
    }

    private WotVehicleStatistics buildRandomVehicleStatistics(Integer vehicleId, Integer battles) {
        return new WotVehicleStatistics(
                0,vehicleId,false,0,
                0,0,0,null,
                null,null,null,buildRandomStatisticsByGameMode(battles),
                null,null,null,null
        );
    }

    private WotPlayerDetails buildRandomPlayerDetails(Integer accountId, Integer battles) {
        return new WotPlayerDetails(
                "", 0, 0,0,
                0,false,0,0,
                buildRandomPlayerStatistics(battles), "", 0
        );
    }

    private WotPlayerStatistics buildRandomPlayerStatistics(Integer battles) {
        return new WotPlayerStatistics(
                null,
                buildRandomStatisticsByGameMode(battles),
                null,
                0,
                null,
                null,
                null,
                null,
                null,
                0
        );
    }

    private WotStatisticsByGameMode buildRandomStatisticsByGameMode(Integer battles) {
        return new WotStatisticsByGameMode(
                0,battles, 0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,
                0f,0f,0f,0,0,0,0,0f,0
        );
    }

    private VehicleStatisticsSnapshot buildVehicleStatisticSnapshot(
            Integer playerId, Integer tankId, Integer totalBattles,
            Integer survivedBattles, Float killDeathRatio, Float hitMissRatio,
            Float winLossRatio, Float averageWn8, Float averageDamage,
            Float averageExperience, Float averageKills, Float averageDamageReceived,
            Float averageShots, Float averageStunAssistedDamage, Float averageCapturePoints,
            Float averageDroppedCapturePoints
    ) {
        VehicleStatisticsSnapshot vehicleStatisticsSnapshot = new VehicleStatisticsSnapshot();

        vehicleStatisticsSnapshot.setAccountId(playerId);
        vehicleStatisticsSnapshot.setVehicleId(tankId);
        vehicleStatisticsSnapshot.setTotalBattles(totalBattles);
        vehicleStatisticsSnapshot.setSurvivedBattles(survivedBattles);
        vehicleStatisticsSnapshot.setKillDeathRatio(killDeathRatio);
        vehicleStatisticsSnapshot.setHitMissRatio(hitMissRatio);
        vehicleStatisticsSnapshot.setWinLossRatio(winLossRatio);
        vehicleStatisticsSnapshot.setAverageWn8(averageWn8);
        vehicleStatisticsSnapshot.setAverageDamage(averageDamage);
        vehicleStatisticsSnapshot.setAverageExperience(averageExperience);
        vehicleStatisticsSnapshot.setAverageKills(averageKills);
        vehicleStatisticsSnapshot.setAverageDamageReceived(averageDamageReceived);
        vehicleStatisticsSnapshot.setAverageShots(averageShots);
        vehicleStatisticsSnapshot.setAverageStunAssistedDamage(averageStunAssistedDamage);
        vehicleStatisticsSnapshot.setAverageCapturePoints(averageCapturePoints);
        vehicleStatisticsSnapshot.setAverageDroppedCapturePoints(averageDroppedCapturePoints);

        return vehicleStatisticsSnapshot;
    }

    private PlayerStatisticsSnapshot buildRandomPlayerStatisticsSnapshot(Integer accountId) {
        PlayerStatisticsSnapshot playerStatisticsSnapshot = new PlayerStatisticsSnapshot();

        playerStatisticsSnapshot.setPlayerStatisticsSnapshotId(0);
        playerStatisticsSnapshot.setAccountId(accountId);
        playerStatisticsSnapshot.setTotalBattles(0);
        playerStatisticsSnapshot.setSurvivedBattles(0);
        playerStatisticsSnapshot.setKillDeathRatio(0.0f);
        playerStatisticsSnapshot.setHitMissRatio(0.0f);
        playerStatisticsSnapshot.setWinLossRatio(0.0f);
        playerStatisticsSnapshot.setTotalAverageWn8(0.0f);
        playerStatisticsSnapshot.setAverageExperience(0.0f);
        playerStatisticsSnapshot.setAverageDamage(0.0f);
        playerStatisticsSnapshot.setAverageKills(0.0f);
        playerStatisticsSnapshot.setAverageDamageReceived(0.0f);
        playerStatisticsSnapshot.setAverageShots(0.0f);
        playerStatisticsSnapshot.setAverageStunAssistedDamage(0.0f);
        playerStatisticsSnapshot.setAverageCapturePoints(0.0f);
        playerStatisticsSnapshot.setAverageDroppedCapturePoints(0.0f);
        playerStatisticsSnapshot.setAverageSpotting(0.0f);

        return playerStatisticsSnapshot;
    }

}