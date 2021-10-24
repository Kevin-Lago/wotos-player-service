package com.wotos.wotosplayerservice.jsonao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatisticsByModeJSONAO {

    private StatisticsJSONAO clan;
    private StatisticsJSONAO all;
    @JsonProperty("regular_team")
    private StatisticsJSONAO regularTeam;
    private StatisticsJSONAO company;
    @JsonProperty("stronghold_skirmish")
    private StatisticsJSONAO strongholdSkirmish;
    @JsonProperty("stronghold_defense")
    private StatisticsJSONAO strongholdDefense;
    private StatisticsJSONAO historical;
    private StatisticsJSONAO team;
    @JsonProperty("trees_cut")
    private Integer treesCut;
    private Integer frags;

    public StatisticsJSONAO getClan() {
        return clan;
    }

    public void setClan(StatisticsJSONAO clan) {
        this.clan = clan;
    }

    public StatisticsJSONAO getAll() {
        return all;
    }

    public void setAll(StatisticsJSONAO all) {
        this.all = all;
    }

    public StatisticsJSONAO getRegularTeam() {
        return regularTeam;
    }

    public void setRegularTeam(StatisticsJSONAO regularTeam) {
        this.regularTeam = regularTeam;
    }

    public StatisticsJSONAO getCompany() {
        return company;
    }

    public void setCompany(StatisticsJSONAO company) {
        this.company = company;
    }

    public StatisticsJSONAO getStrongholdSkirmish() {
        return strongholdSkirmish;
    }

    public void setStrongholdSkirmish(StatisticsJSONAO strongholdSkirmish) {
        this.strongholdSkirmish = strongholdSkirmish;
    }

    public StatisticsJSONAO getStrongholdDefense() {
        return strongholdDefense;
    }

    public void setStrongholdDefense(StatisticsJSONAO strongholdDefense) {
        this.strongholdDefense = strongholdDefense;
    }

    public StatisticsJSONAO getHistorical() {
        return historical;
    }

    public void setHistorical(StatisticsJSONAO historical) {
        this.historical = historical;
    }

    public StatisticsJSONAO getTeam() {
        return team;
    }

    public void setTeam(StatisticsJSONAO team) {
        this.team = team;
    }

    public Integer getTreesCut() {
        return treesCut;
    }

    public void setTreesCut(Integer treesCut) {
        this.treesCut = treesCut;
    }

    public Integer getFrags() {
        return frags;
    }

    public void setFrags(Integer frags) {
        this.frags = frags;
    }
}
