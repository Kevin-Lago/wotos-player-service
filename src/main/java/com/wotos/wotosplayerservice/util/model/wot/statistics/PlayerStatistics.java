package com.wotos.wotosplayerservice.util.model.wot.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerStatistics {

    private final StatisticsByGameMode clan;
    private final StatisticsByGameMode all;
    @JsonProperty("regular_team")
    private final StatisticsByGameMode regularTeam;
    @JsonProperty("trees_cut")
    private final Integer treesCut;
    private final StatisticsByGameMode company;
    @JsonProperty("stronghold_skirmish")
    private final StatisticsByGameMode strongholdSkirmish;
    @JsonProperty("stronghold_defense")
    private final StatisticsByGameMode strongholdDefense;
    private final StatisticsByGameMode historical;
    private final StatisticsByGameMode team;
    private final Integer frags;

    public PlayerStatistics(
            StatisticsByGameMode clan, StatisticsByGameMode all, StatisticsByGameMode regularTeam,
            Integer treesCut, StatisticsByGameMode company, StatisticsByGameMode strongholdSkirmish,
            StatisticsByGameMode strongholdDefense, StatisticsByGameMode historical,
            StatisticsByGameMode team, Integer frags
    ) {
        this.clan = clan;
        this.all = all;
        this.regularTeam = regularTeam;
        this.treesCut = treesCut;
        this.company = company;
        this.strongholdSkirmish = strongholdSkirmish;
        this.strongholdDefense = strongholdDefense;
        this.historical = historical;
        this.team = team;
        this.frags = frags;
    }

    public StatisticsByGameMode getClan() {
        return clan;
    }

    public StatisticsByGameMode getAll() {
        return all;
    }

    public StatisticsByGameMode getRegularTeam() {
        return regularTeam;
    }

    public Integer getTreesCut() {
        return treesCut;
    }

    public StatisticsByGameMode getCompany() {
        return company;
    }

    public StatisticsByGameMode getStrongholdSkirmish() {
        return strongholdSkirmish;
    }

    public StatisticsByGameMode getStrongholdDefense() {
        return strongholdDefense;
    }

    public StatisticsByGameMode getHistorical() {
        return historical;
    }

    public StatisticsByGameMode getTeam() {
        return team;
    }

    public Integer getFrags() {
        return frags;
    }
}
