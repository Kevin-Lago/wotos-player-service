package com.wotos.wotosplayerservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.wotos.wotosplayerservice.dao.ExpectedStatistics;
import com.wotos.wotosplayerservice.dao.VehicleStatisticsSnapshot;
import com.wotos.wotosplayerservice.util.model.wot.statistics.VehicleStatistics;
import com.wotos.wotosplayerservice.repo.ExpectedStatisticsRepository;
import com.wotos.wotosplayerservice.repo.VehicleStatisticsSnapshotsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StatisticsServiceTest {

    private final Random rng = new Random();

    @Autowired
    @MockBean
    private VehicleStatisticsSnapshotsRepository vehicleStatisticsSnapshotsRepository;
    @Autowired
    @MockBean
    private ExpectedStatisticsRepository expectedStatisticsRepository;

    @InjectMocks
    StatisticsService statisticsService;

    @Value("${env.snapshot_rate}")
    private Integer SNAPSHOT_RATE;

//    @Before
//    public void setUp() {
//        List<VehicleStatisticsSnapshot> statisticsSnapshotList = vehicleStatisticsSnapshotsRepository.findAll();
//        statisticsSnapshotList.forEach(statisticsSnapshot ->
//                vehicleStatisticsSnapshotsRepository.delete(statisticsSnapshot)
//        );
//
//        statisticsService = new StatisticsService(vehicleStatisticsSnapshotsRepository, expectedStatisticsRepository);
//        ReflectionTestUtils.setField(statisticsService, "SNAPSHOT_RATE", SNAPSHOT_RATE);
//    }

//    @Test
//    public void testExpectedStatisticsEndpoint() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Object> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> responseEntity = restTemplate.exchange(EXPECTED_STATISTICS_URL, HttpMethod.GET, entity, String.class);
//        HttpStatus status = responseEntity.getStatusCode();
//        String responseEntityBody = responseEntity.getBody(); // Validate the data structure hasnt changed
//
//        assertThat(status.value()).isEqualTo(200);
//
//        try {
//            JsonNode jsonData = mapper.readTree(responseEntityBody).get("data");
//
//            jsonData.forEach(value -> {
//                try {
//                    ExpectedStatistics expectedStatistic = mapper.treeToValue(value, ExpectedStatistics.class);
//                } catch (JsonProcessingException e) {
//                    System.out.println("Error parsing Expected Statistics with values: " + value.toString() + "\n" + Arrays.toString(e.getStackTrace()));
//                    fail("Failed to parse Json data from Expected Statistics Endpoint");
//                }
//            });
//        } catch(IOException e) {
//            System.out.println(e.getMessage());
//            fail("Missing field \"data\" in Expected Statistics Endpoint response");
//        }
//    }
//
//    @Test
//    public void testWotTankStatisticsEndpoint() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Object> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> responseEntity = restTemplate.exchange(WOT_TANK_STATISTICS_URL + "&account_id=" + TEST_WOT_ACCOUNT_ID + "&tank_id=" + TEST_WOT_TANK_ID, HttpMethod.GET, entity, String.class);
//        HttpStatus status = responseEntity.getStatusCode();
//        String responseEntityBody = responseEntity.getBody(); // Validate the data structure hasn't changed
//
//        assertThat(status.value()).isEqualTo(200);
//
//        try {
//            JsonNode jsonData = mapper.readTree(responseEntityBody).get("data").get(TEST_WOT_ACCOUNT_ID);
//            VehicleStatistics tankStatistics = mapper.treeToValue(jsonData.get(0), VehicleStatistics.class);
//        } catch (JsonProcessingException e) {
//            System.out.println(Arrays.toString(e.getStackTrace()));
//            fail("Failed to parse Json data from WOT API");
//        } catch (IOException e) {
//            System.out.println(Arrays.toString(e.getStackTrace()));
//            fail("Missing field \"accountId\" in Expected Statistics Endpoint response");
//        }
//    }

//    @Test
//    public void testGetPlayerTankStatistics() {
//        statisticsSnapshotsRepository.save(buildRandomStatisticSnapshot(1, 1, 1));
//
//        List<VehicleStatisticsSnapshot> statisticsSnapshots = statisticsService.getPlayerTankStatistics(1, 1);
//        assertThat(statisticsSnapshots).isNotNull();
//        assertThat(statisticsSnapshots.size()).isEqualTo(1);
//    }

//    @Test
//    public void testGetAllPlayerTankStatistics() {
//        statisticsSnapshotsRepository.save(buildRandomStatisticSnapshot(1,1,1));
//        statisticsSnapshotsRepository.save(buildRandomStatisticSnapshot(1,1,1));
//        statisticsSnapshotsRepository.save(buildRandomStatisticSnapshot(1,1,1));
//
//        List<VehicleStatisticsSnapshot> statisticsSnapshots = statisticsService.getAllPlayerTankStatistics(1);
//        assertThat(statisticsSnapshots).isNotNull();
//        assertThat(statisticsSnapshots.size()).isEqualTo(3);
//    }

    private VehicleStatisticsSnapshot buildRandomStatisticSnapshot(Integer playerId, Integer tankId, Integer totalBattles) {
        VehicleStatisticsSnapshot statisticsSnapshot = new VehicleStatisticsSnapshot();

        statisticsSnapshot.setAccountId(playerId);
        statisticsSnapshot.setVehicleId(tankId);
        statisticsSnapshot.setTotalBattles(totalBattles);
        statisticsSnapshot.setSurvivedBattles(rng.nextInt(totalBattles));
        statisticsSnapshot.setKillDeathRatio(rng.nextFloat());
        statisticsSnapshot.setHitMissRatio(rng.nextFloat());
        statisticsSnapshot.setWinLossRatio(rng.nextFloat());
        statisticsSnapshot.setAverageWn8(6000 * rng.nextFloat());
        statisticsSnapshot.setAverageDamage(6000 * rng.nextFloat());
        statisticsSnapshot.setAverageExperience(1000 * rng.nextFloat());
        statisticsSnapshot.setAverageKills(15 * rng.nextFloat());
        statisticsSnapshot.setAverageDamageReceived(500 * rng.nextFloat());
        statisticsSnapshot.setAverageShots(30 * rng.nextFloat());
        statisticsSnapshot.setAverageStunAssistedDamage(1000 * rng.nextFloat());
        statisticsSnapshot.setAverageCapturePoints(100 * rng.nextFloat());
        statisticsSnapshot.setAverageDroppedCapturePoints(100 * rng.nextFloat());

        return statisticsSnapshot;
    }

}