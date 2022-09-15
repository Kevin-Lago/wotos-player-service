package com.wotos.wotosplayerservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player_snapshots")
public class PlayerSnapshot {

    @Id
    @Column(name = "player_snapshot_id")
    private Integer playerSnapshotId;
    @Column(name = "account_id", nullable = false)
    private Integer accountId;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "global_rating")
    private Integer globalRating;
    @Column(name = "clan_id")
    private Integer clanId;

}
