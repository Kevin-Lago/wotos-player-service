package com.wotos.wotosplayerservice.repo;

import com.wotos.wotosplayerservice.dao.VehicleStatisticsSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StatisticsSnapshotsRepository extends JpaRepository<VehicleStatisticsSnapshot, Integer> {

    Optional<List<VehicleStatisticsSnapshot>> findByPlayerIdAndTankIdIn(Integer playerId, List<Integer> tankIds);

    @Query(value = "SELECT MAX(total_battles) FROM statistics_snapshots WHERE player_id = ?1 AND tank_id = ?2", nativeQuery = true)
    Optional<Integer> findHighestTotalBattlesByPlayerAndTankId(int playerId, int tankId);

}
