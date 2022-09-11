package com.wotos.wotosplayerservice.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player_achievements_snapshots")
public class PlayerAchievementsSnapshot {

    @Id
    private Integer id;
    private Integer accountId;

}
