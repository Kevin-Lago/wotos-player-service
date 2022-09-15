package com.wotos.wotosplayerservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player_achievements_snapshots")
public class PlayerAchievementsSnapshot {

    @Id
    @Column(name = "player_achievements_snapshot_id")
    private Integer playerAchievementsSnapshotId;
    @Column(name = "account_id")
    private Integer accountId;

}
