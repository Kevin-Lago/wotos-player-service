package com.wotos.wotosplayerservice.dto;

import javax.persistence.*;

@Entity(name = "player_tank_statistics_snapshot")
@Table(name = "player_tank_statistics_snapshots")
public class PlayerTankStatisticsSnapshot {

    @Id
    @GeneratedValue
    private Integer playerTankStatisticsSnapshotID;
    @Column(name = "player_id", nullable = false)
    private Integer playerID;
    @Column(name = "tank_id", nullable = false)
    private Integer tankID;
    @Column(name = "total_battles", nullable = false)
    private Integer totalBattles;
    @Column(name = "average_wn8", nullable = false)
    private Float averageWn8;
    @Column(name = "hit_ratio", nullable = false)
    private Float hitRatio;
    @Column(name = "average_experience", nullable = false)
    private Float averageExperience;
    @Column(name = "personal_rating", nullable = false)
    private Integer personalRating;
    @Column(name = "average_kill_ratio", nullable = false)
    private Float averageKillRatio;

    public Integer getPlayerTankStatisticsSnapshotID() {
        return playerTankStatisticsSnapshotID;
    }

    public void setPlayerTankStatisticsSnapshotID(Integer playerTankStatisticsSnapshotID) {
        this.playerTankStatisticsSnapshotID = playerTankStatisticsSnapshotID;
    }

    public Integer getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Integer playerID) {
        this.playerID = playerID;
    }

    public Integer getTankID() {
        return tankID;
    }

    public void setTankID(Integer tankID) {
        this.tankID = tankID;
    }

    public Integer getTotalBattles() {
        return totalBattles;
    }

    public void setTotalBattles(Integer totalBattles) {
        this.totalBattles = totalBattles;
    }

    public Float getAverageWn8() {
        return averageWn8;
    }

    public void setAverageWn8(Float averageWn8) {
        this.averageWn8 = averageWn8;
    }

    public Float getHitRatio() {
        return hitRatio;
    }

    public void setHitRatio(Float hitRatio) {
        this.hitRatio = hitRatio;
    }

    public Float getAverageExperience() {
        return averageExperience;
    }

    public void setAverageExperience(Float averageExperience) {
        this.averageExperience = averageExperience;
    }

    public Integer getPersonalRating() {
        return personalRating;
    }

    public void setPersonalRating(Integer personalRating) {
        this.personalRating = personalRating;
    }

    public Float getAverageKillRatio() {
        return averageKillRatio;
    }

    public void setAverageKillRatio(Float averageKillRatio) {
        this.averageKillRatio = averageKillRatio;
    }
}
