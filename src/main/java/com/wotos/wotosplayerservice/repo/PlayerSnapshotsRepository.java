package com.wotos.wotosplayerservice.repo;

import com.wotos.wotosplayerservice.dao.PlayerSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerSnapshotsRepository extends JpaRepository<PlayerSnapshot, Integer> {

    Optional<List<PlayerSnapshot>> findByAccountId(Integer accountId);

    @Query(value = "select * from player_snapshots", nativeQuery = true)
    Optional<PlayerSnapshot> findMostRecentByAccountId(Integer accountId);

}
