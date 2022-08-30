package com.wotos.wotosplayerservice.repo;

import com.wotos.wotosplayerservice.dao.StatisticsSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StatisticsSnapshotsRepository extends JpaRepository<StatisticsSnapshot, Integer> {

    Optional<List<StatisticsSnapshot>> findAllStatisticsSnapshotsByPlayerId(int playerId);

    Optional<List<StatisticsSnapshot>> findAllStatisticsSnapshotsByPlayerIdAndTankId(int playerId, int tankId);

    @Query(value = "SELECT MAX(total_battles) FROM statistics_snapshots WHERE player_id = ?1 AND tank_id = ?2", nativeQuery = true)
    Optional<Integer> findHighestTotalBattlesByPlayerAndTankId(int playerId, int tankId);

}
