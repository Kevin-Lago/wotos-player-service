package com.wotos.wotosplayerservice.jsonao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatisticsJSONAO {

    private Integer spotted;
    @JsonProperty("battles_on_stunning_vehicles")
    private Integer battlesOnStunningVehicles;
    @JsonProperty("max_xp")
    private Integer maxXP;
    private Integer xp;
    @JsonProperty("survived_battles")
    private Integer survivedBattles;
    @JsonProperty("dropped_capture_points")
    private Integer droppedCapturePoints;
    @JsonProperty("hits_percents")
    private Integer hitsPercents;
    private Integer draws;
    private Integer battles;
    @JsonProperty("damage_received")
    private Integer damageReceived;
    private Integer frags;
    @JsonProperty("stun_number")
    private Integer stunNumber;
    @JsonProperty("capture_points")
    private Integer capturePoints;
    @JsonProperty("stun_assisted_damage")
    private Integer stunAssistedDamage;
    @JsonProperty("max_damage")
    private Integer maxDamage;
    private Integer hits;
    @JsonProperty("battle_avg_xp")
    private Integer battleAverageXP;
    private Integer wins;
    private Integer losses;
    @JsonProperty("damage_dealt")
    private Integer damageDealt;
    @JsonProperty("max_frags")
    private Integer maxFrags;
    private Integer shots;

    public Integer getSpotted() {
        return spotted;
    }

    public void setSpotted(Integer spotted) {
        this.spotted = spotted;
    }

    public Integer getBattlesOnStunningVehicles() {
        return battlesOnStunningVehicles;
    }

    public void setBattlesOnStunningVehicles(Integer battlesOnStunningVehicles) {
        this.battlesOnStunningVehicles = battlesOnStunningVehicles;
    }

    public Integer getMaxXP() {
        return maxXP;
    }

    public void setMaxXP(Integer maxXP) {
        this.maxXP = maxXP;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getSurvivedBattles() {
        return survivedBattles;
    }

    public void setSurvivedBattles(Integer survivedBattles) {
        this.survivedBattles = survivedBattles;
    }

    public Integer getDroppedCapturePoints() {
        return droppedCapturePoints;
    }

    public void setDroppedCapturePoints(Integer droppedCapturePoints) {
        this.droppedCapturePoints = droppedCapturePoints;
    }

    public Integer getHitsPercents() {
        return hitsPercents;
    }

    public void setHitsPercents(Integer hitsPercents) {
        this.hitsPercents = hitsPercents;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public Integer getBattles() {
        return battles;
    }

    public void setBattles(Integer battles) {
        this.battles = battles;
    }

    public Integer getDamageReceived() {
        return damageReceived;
    }

    public void setDamageReceived(Integer damageReceived) {
        this.damageReceived = damageReceived;
    }

    public Integer getFrags() {
        return frags;
    }

    public void setFrags(Integer frags) {
        this.frags = frags;
    }

    public Integer getStunNumber() {
        return stunNumber;
    }

    public void setStunNumber(Integer stunNumber) {
        this.stunNumber = stunNumber;
    }

    public Integer getCapturePoints() {
        return capturePoints;
    }

    public void setCapturePoints(Integer capturePoints) {
        this.capturePoints = capturePoints;
    }

    public Integer getStunAssistedDamage() {
        return stunAssistedDamage;
    }

    public void setStunAssistedDamage(Integer stunAssistedDamage) {
        this.stunAssistedDamage = stunAssistedDamage;
    }

    public Integer getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(Integer maxDamage) {
        this.maxDamage = maxDamage;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getBattleAverageXP() {
        return battleAverageXP;
    }

    public void setBattleAverageXP(Integer battleAverageXP) {
        this.battleAverageXP = battleAverageXP;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getDamageDealt() {
        return damageDealt;
    }

    public void setDamageDealt(Integer damageDealt) {
        this.damageDealt = damageDealt;
    }

    public Integer getMaxFrags() {
        return maxFrags;
    }

    public void setMaxFrags(Integer maxFrags) {
        this.maxFrags = maxFrags;
    }

    public Integer getShots() {
        return shots;
    }

    public void setShots(Integer shots) {
        this.shots = shots;
    }
}
