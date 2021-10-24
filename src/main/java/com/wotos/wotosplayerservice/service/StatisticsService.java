package com.wotos.wotosplayerservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wotos.wotosplayerservice.dto.ExpectedStatistics;
import com.wotos.wotosplayerservice.dto.PlayerStatisticsSnapshot;
import com.wotos.wotosplayerservice.repo.ExpectedStatisticsRepository;
import com.wotos.wotosplayerservice.repo.PlayerStatisticsSnapshotRepository;
import com.wotos.wotosplayerservice.repo.PlayerTankStatisticsSnapshotRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    private final PlayerStatisticsSnapshotRepository playerStatisticsRepository;
    private final PlayerTankStatisticsSnapshotRepository playerTankStatisticsRepository;
    private final ExpectedStatisticsRepository expectedStatisticsRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Value("${url.expected_statistics}")
    private String EXPECTED_STATISTICS_URI;

    public StatisticsService(
            PlayerStatisticsSnapshotRepository playerStatisticsRepository,
            PlayerTankStatisticsSnapshotRepository playerTankStatisticsRepository,
            ExpectedStatisticsRepository expectedStatisticsRepository
    ) {
        this.playerStatisticsRepository = playerStatisticsRepository;
        this.playerTankStatisticsRepository = playerTankStatisticsRepository;
        this.expectedStatisticsRepository = expectedStatisticsRepository;
        this.restTemplate = new RestTemplate();
        this.mapper = new ObjectMapper();
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false
        );
    }

    public PlayerStatisticsSnapshot getLastestPlayerStatisticsSnapshot() {
//        PlayerStatisticsSnapshot statisticsSnapshot = playerStatisticsRepository.findOne(); // Find by largest battles value

        return null;
    }

    private PlayerStatisticsSnapshot buildStatisticsSnapshot() {
        PlayerStatisticsSnapshot statisticsSnapshot = new PlayerStatisticsSnapshot();



        return null;
    }

    public List<ExpectedStatistics> fetchExpectedStatistics() {
//        ToDo: Just return expected statistics
        String result = this.restTemplate.getForObject(EXPECTED_STATISTICS_URI, String.class);
        List<ExpectedStatistics> expectedStatistics = new ArrayList<>();

        try {
            JsonNode data = mapper.readTree(result).get("data");

            data.forEach(value -> {
                try {
                    ExpectedStatistics expectedStatistic = mapper.treeToValue(value, ExpectedStatistics.class);
                    expectedStatistics.add(expectedStatistic);

//                    if (expectedStatisticsRepository.existsById(expectedStatistic.getTankID())) {
//                        System.out.println("exists");
//                    }
                } catch (JsonProcessingException e) {
                    System.out.println("Error fetching Expected Statistics values: " + e.getMessage());
                }
            });

            if (expectedStatisticsRepository.findAll().size() == 0) {
                expectedStatisticsRepository.saveAll(expectedStatistics);
            }

            return expectedStatistics;
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
