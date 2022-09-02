package com.wotos.wotosplayerservice.dao;

import javax.persistence.*;

@Entity
@Table(schema = "wotos_player_db", name = "player_statistics_snapshots")
public class PlayerStatisticsSnapshot {
    @Id
    @GeneratedValue
    @Column(name = "player_statistics_snapshot_id")
    private Integer playerStatisticsSnapshot;
    @Column(name = "player_id", nullable = false)
    private Integer playerId;
    @Column(name = "total_battles", nullable = false)
    private Integer totalBattles;
    @Column(name = "survived_battles", nullable = false)
    private Integer survivedBattles;
    @Column(name = "kill_death_ratio", nullable = false)
    private Float killDeathRatio;
    @Column(name = "hit_miss_ratio", nullable = false)
    private Float hitMissRatio;
    @Column(name = "win_loss_ratio", nullable = false)
    private Float winLossRatio;
    @Column(name = "average_wn8", nullable = false)
    private Float averageWn8;
    @Column(name = "average_experience", nullable = false)
    private Float averageExperience;
    @Column(name = "average_damage", nullable = false)
    private Float averageDamage;
    @Column(name = "average_kills", nullable = false)
    private Float averageKills;
    @Column(name = "average_damage_received", nullable = false)
    private Float averageDamageReceived;
    @Column(name = "average_shots", nullable = false)
    private Float averageShots;
    @Column(name = "average_stun_assisted_damage", nullable = false)
    private Float averageStunAssistedDamage;
    @Column(name = "average_capture_points", nullable = false)
    private Float averageCapturePoints;
    @Column(name = "dropped_capture_points", nullable = false)
    private Float averageDroppedCapturePoints;

    public Integer getPlayerStatisticsSnapshot() {
        return playerStatisticsSnapshot;
    }

    public void setPlayerStatisticsSnapshot(Integer playerStatisticsSnapshot) {
        this.playerStatisticsSnapshot = playerStatisticsSnapshot;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getTotalBattles() {
        return totalBattles;
    }

    public void setTotalBattles(Integer totalBattles) {
        this.totalBattles = totalBattles;
    }

    public Integer getSurvivedBattles() {
        return survivedBattles;
    }

    public void setSurvivedBattles(Integer survivedBattles) {
        this.survivedBattles = survivedBattles;
    }

    public Float getKillDeathRatio() {
        return killDeathRatio;
    }

    public void setKillDeathRatio(Float killDeathRatio) {
        this.killDeathRatio = killDeathRatio;
    }

    public Float getHitMissRatio() {
        return hitMissRatio;
    }

    public void setHitMissRatio(Float hitMissRatio) {
        this.hitMissRatio = hitMissRatio;
    }

    public Float getWinLossRatio() {
        return winLossRatio;
    }

    public void setWinLossRatio(Float winLossRatio) {
        this.winLossRatio = winLossRatio;
    }

    public Float getAverageWn8() {
        return averageWn8;
    }

    public void setAverageWn8(Float averageWn8) {
        this.averageWn8 = averageWn8;
    }

    public Float getAverageExperience() {
        return averageExperience;
    }

    public void setAverageExperience(Float averageExperience) {
        this.averageExperience = averageExperience;
    }

    public Float getAverageDamage() {
        return averageDamage;
    }

    public void setAverageDamage(Float averageDamage) {
        this.averageDamage = averageDamage;
    }

    public Float getAverageKills() {
        return averageKills;
    }

    public void setAverageKills(Float averageKills) {
        this.averageKills = averageKills;
    }

    public Float getAverageDamageReceived() {
        return averageDamageReceived;
    }

    public void setAverageDamageReceived(Float averageDamageReceived) {
        this.averageDamageReceived = averageDamageReceived;
    }

    public Float getAverageShots() {
        return averageShots;
    }

    public void setAverageShots(Float averageShots) {
        this.averageShots = averageShots;
    }

    public Float getAverageStunAssistedDamage() {
        return averageStunAssistedDamage;
    }

    public void setAverageStunAssistedDamage(Float averageStunAssistedDamage) {
        this.averageStunAssistedDamage = averageStunAssistedDamage;
    }

    public Float getAverageCapturePoints() {
        return averageCapturePoints;
    }

    public void setAverageCapturePoints(Float averageCapturePoints) {
        this.averageCapturePoints = averageCapturePoints;
    }

    public Float getAverageDroppedCapturePoints() {
        return averageDroppedCapturePoints;
    }

    public void setAverageDroppedCapturePoints(Float averageDroppedCapturePoints) {
        this.averageDroppedCapturePoints = averageDroppedCapturePoints;
    }
}
