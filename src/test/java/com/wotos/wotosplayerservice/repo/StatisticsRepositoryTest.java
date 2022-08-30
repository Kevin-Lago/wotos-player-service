package com.wotos.wotosplayerservice.repo;

import com.wotos.wotosplayerservice.dao.StatisticsSnapshot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StatisticsRepositoryTest {

    @Autowired
    StatisticsSnapshotsRepository statisticsSnapshotsRepository;

    @Before
    public void setUp() {

        List<StatisticsSnapshot> statisticsSnapshotList = statisticsSnapshotsRepository.findAll();
        statisticsSnapshotList.forEach(statisticsSnapshot -> statisticsSnapshotsRepository.delete(statisticsSnapshot));

    }

    @Test
    public void saveGetAndDeleteStatisticSnapshotTest() {
        StatisticsSnapshot statisticsSnapshot = buildRandomStatisticSnapshot(1, 1, 100);

        statisticsSnapshotsRepository.save(statisticsSnapshot);
        Optional<List<StatisticsSnapshot>> optionalStatisticsSnapshots = statisticsSnapshotsRepository.findAllStatisticsSnapshotsByPlayerId(statisticsSnapshot.getPlayerId());
        StatisticsSnapshot statisticsSnapshotFromRepo = optionalStatisticsSnapshots.orElse(new ArrayList<>()).get(0);

        assertThat(statisticsSnapshotFromRepo).isEqualToComparingFieldByField(statisticsSnapshot);

        statisticsSnapshotsRepository.delete(statisticsSnapshot);

        StatisticsSnapshot deletedStatisticsSnapshot = statisticsSnapshotsRepository.findById(statisticsSnapshotFromRepo.getStatisticsSnapshotId()).orElse(null);

        assertNull(deletedStatisticsSnapshot);
    }

    @Test
    public void testFindAllStatisticsSnapshotsByPlayerId() {
        StatisticsSnapshot statisticsSnapshot = buildRandomStatisticSnapshot(1, 1, 1);
        statisticsSnapshotsRepository.save(statisticsSnapshot);
        statisticsSnapshot = buildRandomStatisticSnapshot(1, 1, 1);
        statisticsSnapshotsRepository.save(statisticsSnapshot);
        statisticsSnapshot = buildRandomStatisticSnapshot(2, 1, 1);
        statisticsSnapshotsRepository.save(statisticsSnapshot);

        Optional<List<StatisticsSnapshot>> optionalStatisticsSnapshots = statisticsSnapshotsRepository.findAllStatisticsSnapshotsByPlayerId(1);
        List<StatisticsSnapshot> statisticsSnapshots = optionalStatisticsSnapshots.orElse(new ArrayList<>());

        assertThat(statisticsSnapshots.size()).isEqualTo(2);
    }

    @Test
    public void testFindAllStatisticsSnapshotsByPlayerIdAndTankId() {
        StatisticsSnapshot statisticsSnapshot = buildRandomStatisticSnapshot(1, 1, 1);
        statisticsSnapshotsRepository.save(statisticsSnapshot);
        statisticsSnapshot = buildRandomStatisticSnapshot(1, 1, 1);
        statisticsSnapshotsRepository.save(statisticsSnapshot);
        statisticsSnapshot = buildRandomStatisticSnapshot(1, 2, 1);
        statisticsSnapshotsRepository.save(statisticsSnapshot);

        Optional<List<StatisticsSnapshot>> optionalStatisticsSnapshots = statisticsSnapshotsRepository.findAllStatisticsSnapshotsByPlayerIdAndTankId(1, 1);
        List<StatisticsSnapshot> statisticsSnapshots = optionalStatisticsSnapshots.orElse(new ArrayList<>());

        assertThat(statisticsSnapshots.size()).isEqualTo(2);
    }

    @Test
    public void testFindHighestTotalBattlesByPlayerAndTankId() {
        StatisticsSnapshot statisticsSnapshot = buildRandomStatisticSnapshot(1, 1, 1);
        statisticsSnapshotsRepository.save(statisticsSnapshot);
        statisticsSnapshot = buildRandomStatisticSnapshot(1, 1, 2);
        statisticsSnapshotsRepository.save(statisticsSnapshot);
        statisticsSnapshot = buildRandomStatisticSnapshot(1, 1, 3);
        statisticsSnapshotsRepository.save(statisticsSnapshot);

        Optional<Integer> optionalStatisticsSnapshots = statisticsSnapshotsRepository.findHighestTotalBattlesByPlayerAndTankId(1, 1);
        Integer highestBattles = optionalStatisticsSnapshots.orElse(0);

        assertThat(highestBattles).isEqualTo(3);
    }

    private StatisticsSnapshot buildRandomStatisticSnapshot(Integer playerId, Integer tankId, Integer totalBattles) {
        StatisticsSnapshot statisticsSnapshot = new StatisticsSnapshot();
        Random rng = new Random();

        statisticsSnapshot.setPlayerId(playerId);
        statisticsSnapshot.setTankId(tankId);
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
