package com.wotos.wotosplayerservice.repo;

import com.wotos.wotosplayerservice.dto.PlayerTankStatisticsSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerTankStatisticsSnapshotRepository extends JpaRepository<PlayerTankStatisticsSnapshot, Integer> {

//    @Query(value = "SELECT MAX(t.battles) FROM tank_statistics t " +
//            "WHERE player_id = ?1 AND tank_id = ?2")
//    Optional<PlayerTankStatisticsSnapshot> findTankStatisticsByPlayerAndTankID(
//            Integer playerID,
//            Integer tankID
//    );
//
//    @Query(value = "SELECT MAX(t.battles) FROM tank_statistics t " +
//            "WHERE player_id = ?1 AND tank_id = ?2")
//    Optional<Integer> findMaxBattlesByPlayerAndTankID(
//            Integer playerID,
//            Integer tankID
//    );

//    @Query(value = "SELECT t FROM tank_statistics t WHERE player_id = ?1")
//    Optional<List<PlayerTankStatisticsSnapshot>> findAllTankStatisticsByPlayerID(Integer playerID);

}
