package com.wotos.wotosplayerservice.jsonao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TankStatisticsJSONAO {

    @JsonProperty("tank_id")
    private Integer tankID;
    @JsonProperty("in_garage")
    private Boolean inGarage;
    private Integer frags;
    @JsonProperty("mark_of_mastery")
    private Integer marksOfMastery;
    @JsonProperty("max_frags")
    private Integer maxFrags;
    @JsonProperty("max_xp")
    private Integer maxXP;
    @JsonProperty("account_id")
    private Integer accountID;
    private StatisticsByModeJSONAO statistics;

    public Integer getTankID() {
        return tankID;
    }

    public void setTankID(Integer tankID) {
        this.tankID = tankID;
    }

    public Boolean getInGarage() {
        return inGarage;
    }

    public void setInGarage(Boolean inGarage) {
        this.inGarage = inGarage;
    }

    public Integer getFrags() {
        return frags;
    }

    public void setFrags(Integer frags) {
        this.frags = frags;
    }

    public Integer getMarksOfMastery() {
        return marksOfMastery;
    }

    public void setMarksOfMastery(Integer marksOfMastery) {
        this.marksOfMastery = marksOfMastery;
    }

    public Integer getMaxFrags() {
        return maxFrags;
    }

    public void setMaxFrags(Integer maxFrags) {
        this.maxFrags = maxFrags;
    }

    public Integer getMaxXP() {
        return maxXP;
    }

    public void setMaxXP(Integer maxXP) {
        this.maxXP = maxXP;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public StatisticsByModeJSONAO getStatistics() {
        return statistics;
    }

    public void setStatistics(StatisticsByModeJSONAO statistics) {
        this.statistics = statistics;
    }
}
