package com.wotos.wotosplayerservice.repo;

import com.wotos.wotosplayerservice.dto.PlayerTankStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerTankStatisticsRepository extends JpaRepository<PlayerTankStatistics, Integer> {

    @Query(value = "SELECT MAX(t.battles) FROM tank_statistics t " +
            "WHERE player_id = ?1 AND tank_id = ?2")
    Optional<PlayerTankStatistics> findTankStatisticsByPlayerAndTankID(
            int playerID,
            int tankID
    );

    @Query(value = "SELECT MAX(t.battles) FROM tank_statistics t " +
            "WHERE player_id = ?1 AND tank_id = ?2")
    Optional<Integer> findMaxBattlesByPlayerAndTankID(
            int playerID,
            int tankID
    );

    @Query(value = "SELECT t FROM tank_statistics t WHERE player_id = ?1")
    Optional<List<PlayerTankStatistics>> findAllTankStatisticsByPlayerID(int playerID);

}
