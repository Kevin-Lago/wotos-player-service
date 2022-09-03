package com.wotos.wotosplayerservice.util.model.wot.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleStatistics {

    private final Integer frags;
    @JsonProperty("tank_id")
    private final Integer vehicleId;
    @JsonProperty("in_garage")
    private final Boolean isInGarage;
    @JsonProperty("mark_of_mastery")
    private final Integer markOfMastery;
    @JsonProperty("max_frags")
    private final Integer maxFrags;
    @JsonProperty("max_xp")
    private final Integer maxXp;
    @JsonProperty("account_id")
    private final Integer accountId;
    @JsonProperty("regular_team")
    private final StatisticsByGameMode regularTeam;
    @JsonProperty("stronghold_skirmish")
    private final StatisticsByGameMode strongholdSkirmish;
    @JsonProperty("stronghold_defense")
    private final StatisticsByGameMode strongholdDefense;
    private final StatisticsByGameMode clan;
    private final StatisticsByGameMode all;
    private final StatisticsByGameMode company;
    private final StatisticsByGameMode historical;
    private final StatisticsByGameMode globalmap;
    private final StatisticsByGameMode team;

    public VehicleStatistics(
            Integer frags, Integer vehicleId, Boolean isInGarage, Integer markOfMastery,
            Integer maxFrags, Integer maxXp, Integer accountId, StatisticsByGameMode regularTeam,
            StatisticsByGameMode strongholdSkirmish, StatisticsByGameMode strongholdDefense,
            StatisticsByGameMode clan, StatisticsByGameMode all, StatisticsByGameMode company,
            StatisticsByGameMode historical, StatisticsByGameMode globalmap, StatisticsByGameMode team
    ) {
        this.frags = frags;
        this.vehicleId = vehicleId;
        this.isInGarage = isInGarage;
        this.markOfMastery = markOfMastery;
        this.maxFrags = maxFrags;
        this.maxXp = maxXp;
        this.accountId = accountId;
        this.regularTeam = regularTeam;
        this.strongholdSkirmish = strongholdSkirmish;
        this.strongholdDefense = strongholdDefense;
        this.clan = clan;
        this.all = all;
        this.company = company;
        this.historical = historical;
        this.globalmap = globalmap;
        this.team = team;
    }

    public Integer getFrags() {
        return frags;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public Boolean getInGarage() {
        return isInGarage;
    }

    public Integer getMarkOfMastery() {
        return markOfMastery;
    }

    public Integer getMaxFrags() {
        return maxFrags;
    }

    public Integer getMaxXp() {
        return maxXp;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public StatisticsByGameMode getRegularTeam() {
        return regularTeam;
    }

    public StatisticsByGameMode getStrongholdSkirmish() {
        return strongholdSkirmish;
    }

    public StatisticsByGameMode getStrongholdDefense() {
        return strongholdDefense;
    }

    public StatisticsByGameMode getClan() {
        return clan;
    }

    public StatisticsByGameMode getAll() {
        return all;
    }

    public StatisticsByGameMode getCompany() {
        return company;
    }

    public StatisticsByGameMode getHistorical() {
        return historical;
    }

    public StatisticsByGameMode getGlobalmap() {
        return globalmap;
    }

    public StatisticsByGameMode getTeam() {
        return team;
    }
}
