package com.wotos.wotosplayerservice.repo;

import com.wotos.wotosplayerservice.dao.PlayerAchievementsSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerAchievementsSnapshotRepository extends JpaRepository<PlayerAchievementsSnapshot, Integer> {

    Optional<List<PlayerAchievementsSnapshot>> findByAccountId(Integer accountId);

//    Optional<PlayerAchievementsSnapshot> findLatestByAccountId(Integer accountId);

}
