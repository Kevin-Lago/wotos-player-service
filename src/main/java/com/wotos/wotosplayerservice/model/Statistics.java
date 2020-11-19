package com.wotos.wotosplayerservice.model;

public class Statistics {

    private Object frags;
    private int trees_cut;
    private StatisticsByMode all;
    private StatisticsByMode clan;
    private StatisticsByMode regular_team;
    private StatisticsByMode company;
    private StatisticsByMode stronghold_skirmish;
    private StatisticsByMode stronghold_defence;
    private StatisticsByMode historical;
    private StatisticsByMode team;

    public Object getFrags() {
        return frags;
    }

    public void setFrags(Object frags) {
        this.frags = frags;
    }

    public int getTrees_cut() {
        return trees_cut;
    }

    public void setTrees_cut(int trees_cut) {
        this.trees_cut = trees_cut;
    }

    public StatisticsByMode getAll() {
        return all;
    }

    public void setAll(StatisticsByMode all) {
        this.all = all;
    }

    public StatisticsByMode getClan() {
        return clan;
    }

    public void setClan(StatisticsByMode clan) {
        this.clan = clan;
    }

    public StatisticsByMode getRegular_team() {
        return regular_team;
    }

    public void setRegular_team(StatisticsByMode regular_team) {
        this.regular_team = regular_team;
    }

    public StatisticsByMode getCompany() {
        return company;
    }

    public void setCompany(StatisticsByMode company) {
        this.company = company;
    }

    public StatisticsByMode getStronghold_skirmish() {
        return stronghold_skirmish;
    }

    public void setStronghold_skirmish(StatisticsByMode stronghold_skirmish) {
        this.stronghold_skirmish = stronghold_skirmish;
    }

    public StatisticsByMode getStronghold_defence() {
        return stronghold_defence;
    }

    public void setStronghold_defence(StatisticsByMode stronghold_defence) {
        this.stronghold_defence = stronghold_defence;
    }

    public StatisticsByMode getHistorical() {
        return historical;
    }

    public void setHistorical(StatisticsByMode historical) {
        this.historical = historical;
    }

    public StatisticsByMode getTeam() {
        return team;
    }

    public void setTeam(StatisticsByMode team) {
        this.team = team;
    }
}
