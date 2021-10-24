package com.wotos.wotosplayerservice.jsonao;

public class StatisticsByModeJSONAO {

    private StatisticsJSONAO clan;
    private StatisticsJSONAO all;
    private StatisticsJSONAO regular_team;
    private StatisticsJSONAO company;
    private StatisticsJSONAO stronghold_skirmish;
    private StatisticsJSONAO stronghold_defense;
    private StatisticsJSONAO historical;
    private StatisticsJSONAO team;
    private Integer trees_cut;
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

    public StatisticsJSONAO getRegular_team() {
        return regular_team;
    }

    public void setRegular_team(StatisticsJSONAO regular_team) {
        this.regular_team = regular_team;
    }

    public Integer getTrees_cut() {
        return trees_cut;
    }

    public void setTrees_cut(Integer trees_cut) {
        this.trees_cut = trees_cut;
    }

    public StatisticsJSONAO getCompany() {
        return company;
    }

    public void setCompany(StatisticsJSONAO company) {
        this.company = company;
    }

    public StatisticsJSONAO getStronghold_skirmish() {
        return stronghold_skirmish;
    }

    public void setStronghold_skirmish(StatisticsJSONAO stronghold_skirmish) {
        this.stronghold_skirmish = stronghold_skirmish;
    }

    public StatisticsJSONAO getStronghold_defense() {
        return stronghold_defense;
    }

    public void setStronghold_defense(StatisticsJSONAO stronghold_defense) {
        this.stronghold_defense = stronghold_defense;
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

    public Integer getFrags() {
        return frags;
    }

    public void setFrags(Integer frags) {
        this.frags = frags;
    }
}
