package com.wotos.wotosplayerservice.dto;

import javax.persistence.*;

@Entity(name = "player_statistics_snapshot")
@Table(name = "player_statistics_snapshots")
public class PlayerStatisticsSnapshot {

    @Id
    @GeneratedValue
    private Integer playerStatisticsSnapshotID;
    @Column(name = "player_id", nullable = false)
    private Integer playerID;
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

    public Integer getPlayerStatisticsSnapshotID() {
        return playerStatisticsSnapshotID;
    }

    public void setPlayerStatisticsSnapshotID(Integer playerStatisticsSnapshotID) {
        this.playerStatisticsSnapshotID = playerStatisticsSnapshotID;
    }

    public Integer getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Integer playerID) {
        this.playerID = playerID;
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
}
