package com.wotos.wotosplayerservice.repo;

import com.wotos.wotosplayerservice.dto.PlayerStatisticsSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerStatisticsSnapshotRepository extends JpaRepository<PlayerStatisticsSnapshot, Integer> {



}
