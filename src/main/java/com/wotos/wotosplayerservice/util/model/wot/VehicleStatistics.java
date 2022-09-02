package com.wotos.wotosplayerservice.util.model.wot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleStatistics {

    private Integer frags;
    @JsonProperty("tank_id")
    private Integer tankId;
    @JsonProperty("in_garage")
    private Boolean isInGarage;
    @JsonProperty("mark_of_mastery")
    private Integer markOfMastery;
    @JsonProperty("max_frags")
    private Integer maxFrags;
    @JsonProperty("max_xp")
    private Integer maxXp;
    @JsonProperty("account_id")
    private Integer accountId;
    @JsonProperty("regular_team")
    private PlayerStatistics regularTeam;
    @JsonProperty("stronghold_skirmish")
    private PlayerStatistics strongholdSkirmish;
    @JsonProperty("stronghold_defense")
    private PlayerStatistics strongholdDefense;
    private PlayerStatistics clan;
    private PlayerStatistics all;
    private PlayerStatistics company;
    private PlayerStatistics historical;
    private PlayerStatistics globalmap;
    private PlayerStatistics team;

    public Integer getFrags() {
        return frags;
    }

    public void setFrags(Integer frags) {
        this.frags = frags;
    }

    public Integer getTankId() {
        return tankId;
    }

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    public Boolean getInGarage() {
        return isInGarage;
    }

    public void setInGarage(Boolean inGarage) {
        isInGarage = inGarage;
    }

    public Integer getMarkOfMastery() {
        return markOfMastery;
    }

    public void setMarkOfMastery(Integer markOfMastery) {
        this.markOfMastery = markOfMastery;
    }

    public Integer getMaxFrags() {
        return maxFrags;
    }

    public void setMaxFrags(Integer maxFrags) {
        this.maxFrags = maxFrags;
    }

    public Integer getMaxXp() {
        return maxXp;
    }

    public void setMaxXp(Integer maxXp) {
        this.maxXp = maxXp;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public PlayerStatistics getRegularTeam() {
        return regularTeam;
    }

    public void setRegularTeam(PlayerStatistics regularTeam) {
        this.regularTeam = regularTeam;
    }

    public PlayerStatistics getStrongholdSkirmish() {
        return strongholdSkirmish;
    }

    public void setStrongholdSkirmish(PlayerStatistics strongholdSkirmish) {
        this.strongholdSkirmish = strongholdSkirmish;
    }

    public PlayerStatistics getStrongholdDefense() {
        return strongholdDefense;
    }

    public void setStrongholdDefense(PlayerStatistics strongholdDefense) {
        this.strongholdDefense = strongholdDefense;
    }

    public PlayerStatistics getClan() {
        return clan;
    }

    public void setClan(PlayerStatistics clan) {
        this.clan = clan;
    }

    public PlayerStatistics getAll() {
        return all;
    }

    public void setAll(PlayerStatistics all) {
        this.all = all;
    }

    public PlayerStatistics getCompany() {
        return company;
    }

    public void setCompany(PlayerStatistics company) {
        this.company = company;
    }

    public PlayerStatistics getHistorical() {
        return historical;
    }

    public void setHistorical(PlayerStatistics historical) {
        this.historical = historical;
    }

    public PlayerStatistics getGlobalmap() {
        return globalmap;
    }

    public void setGlobalmap(PlayerStatistics globalmap) {
        this.globalmap = globalmap;
    }

    public PlayerStatistics getTeam() {
        return team;
    }

    public void setTeam(PlayerStatistics team) {
        this.team = team;
    }
}
