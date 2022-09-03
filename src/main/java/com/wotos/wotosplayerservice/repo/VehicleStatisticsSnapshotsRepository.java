package com.wotos.wotosplayerservice.repo;

import com.wotos.wotosplayerservice.dao.VehicleStatisticsSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VehicleStatisticsSnapshotsRepository extends JpaRepository<VehicleStatisticsSnapshot, Integer> {

    Optional<List<VehicleStatisticsSnapshot>> findByPlayerIdAndVehicleIdIn(Integer playerId, List<Integer> vehicleIds);

    @Query(value = "SELECT MAX(total_battles) FROM vehicle_statistics_snapshots WHERE player_id = ?1 AND vehicle_id = ?2", nativeQuery = true)
    Optional<Integer> findHighestTotalBattlesByPlayerAndVehicleId(Integer playerId, Integer vehicleId);

}
